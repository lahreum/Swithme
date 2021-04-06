package com.studywithme.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.studywithme.DtoOnlyReturn.GroupProfileDto;
import com.studywithme.DtoOnlyReturn.UserDto;
import com.studywithme.config.CommonMethods;
import com.studywithme.entity.Category;
import com.studywithme.entity.GroupInfo;
import com.studywithme.entity.GroupMember;
import com.studywithme.entity.TimeDaily;
import com.studywithme.entity.TimeMonthly;
import com.studywithme.entity.UserInfo;
import com.studywithme.repository.DefaultProfileImgRepository;
import com.studywithme.repository.GroupMemberRepository;
import com.studywithme.repository.GroupRepository;
import com.studywithme.repository.TimeDailyRepository;
import com.studywithme.repository.TimeMonthlyRepository;
import com.studywithme.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	CommonMethods commonMethods;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	GroupMemberRepository groupMemberRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TimeDailyRepository timeDailyRepository;
	
	@Autowired
	TimeMonthlyRepository timeMonthlyRepository;
	
	@Autowired
	DefaultProfileImgRepository defaultProfileImgRepository;
	
	
	@PostMapping("")
	@ApiOperation(value="그룹 생성",notes="바디로 받은 group으로 그룹생성에 성공하면 true 반환")
	public Object makeGroup(@RequestBody GroupInfo group,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			group.setGroupMasterNickname(nickname);
			group.setGroupProfileImg(defaultProfileImgRepository.findById(2).get().getDefaultProfileImgData());
			GroupInfo savedGroup=groupRepository.save(group);
			
			Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, savedGroup.getGroupId());
			if(!groupMember.isPresent()) {
				GroupMember newGroupMember=new GroupMember();
				newGroupMember.setGroupMemberGroupId(savedGroup.getGroupId());
				newGroupMember.setGroupMemberIsMaster(true);
				newGroupMember.setGroupMemberUserNickname(nickname);
				groupMemberRepository.save(newGroupMember);
			}
			else {
				groupMember.get().setGroupMemberIsMaster(true);
				groupMemberRepository.save(groupMember.get());
			}
			result.clear();
			result.put("success",true);		
			result.put("createdGroupId",savedGroup.getGroupId());
		}
		return result;
	}
	
	@DeleteMapping("")
	@ApiOperation(value="그룹 삭제",notes="파라미터로 그룹번호를 받아 삭제하고 성공하면 true 반환")
	public Object deleteGroup(@RequestParam int groupId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> group=groupRepository.findById(groupId);
			if(group.isPresent()&&group.get().getGroupMasterNickname().equals(nickname)) {
				Optional<List<GroupMember>> groupMemberList=groupMemberRepository.findByGroupMemberGroupId(groupId);
				if(groupMemberList.isPresent()) {
					for(GroupMember gm:groupMemberList.get()) 
						groupMemberRepository.delete(gm);
					
					groupRepository.delete(group.get());
					result.clear();
					result.put("success",true);					
				}
			}			
		}
		return result;
	}
	

	@GetMapping("/search")
	@ApiOperation(value="특정 카테고리 번호의 그룹들 받기",notes="db에서 해당 카테고리 번호들에 해당하는 카테고리의 그룹들을 받는다")
	public Object getCategoryNames(@RequestParam("category-list") String categoryList) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("searchedGroupList",null);

		
		categoryList=categoryList.replaceAll("\\[", "");
		categoryList=categoryList.replaceAll("\\]", "");
		categoryList=categoryList.replace(" ", "");
	
		String[] categoryArr=categoryList.split(",");

		List<Integer> categoryListInt=new ArrayList<>();
		for(String s:categoryArr)
			categoryListInt.add(Integer.parseInt(s));
		
		Optional<List<GroupInfo>> list=groupRepository.findAllByGroupCategoryIn(categoryListInt);
		if(list.isPresent()) {
			List<GroupProfileDto> profileList=new ArrayList<>();
			
			for(int i=0;i<list.get().size();i++) {
				GroupProfileDto gpd=new GroupProfileDto();
				gpd.setGroupId(list.get().get(i).getGroupId());
				try {
					gpd.setGroupProfileImg(list.get().get(i).getGroupProfileImg().getBytes(1l, (int)list.get().get(i).getGroupProfileImg().length()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				profileList.add(gpd);
				list.get().get(i).setGroupProfileImg(null);
			}
			
			result.clear();
			result.put("searchedGroupList",list);
			result.put("groupProfileList",profileList);
		}
		
		return result;
	}
	
	
	@GetMapping("")
	@ApiOperation(value="모든 그룹리스트 받기",notes="모든 그룹리스트를 받아온다")
	public Object getGroupList(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("groupList",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			List<GroupInfo> groupList=groupRepository.findAll();
			if(groupList!=null) {
				result.clear();
				List<GroupProfileDto> groupProfileList=new ArrayList<>();
				
				for(int i=0;i<groupList.size();i++) {
					try {
						GroupProfileDto gpd=new GroupProfileDto();
						gpd.setGroupId(groupList.get(i).getGroupId());
						gpd.setGroupProfileImg(groupList.get(i).getGroupProfileImg().getBytes(1l, (int)groupList.get(i).getGroupProfileImg().length()));
						groupProfileList.add(gpd);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					groupList.get(i).setGroupProfileImg(null);
				}
				
				result.put("groupList",groupList);
				result.put("groupProfileList",groupProfileList);
			}
		}		
		return result;
	}
	
	@GetMapping("/{groupId}")
	@ApiOperation(value="그룹 상세정보 보기",notes="경로로 받은 그룹번호에 맞는 그룹, 파라미터로 받은 날짜의 그룹원들 공부시간 반환")
	public Object getGroupInfo(@PathVariable("groupId") int groupId,@RequestParam("datetime") String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("groupInfo",null);
		result.put("groupMemberList",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> group=groupRepository.findById(groupId);
			if(group.isPresent()) {
				Optional<List<GroupMember>> groupMembers=groupMemberRepository.findByGroupMemberGroupId(groupId);
				List<UserDto> userList=new ArrayList<>();

				datetime=datetime.substring(2,10);
				datetime=datetime.replaceAll("-", "");
				
				for(GroupMember gm:groupMembers.get()) {
					Optional<UserInfo> curUserOpt=userRepository.findByUserNickname(gm.getGroupMemberUserNickname());
					Optional<TimeDaily> curTimeDaily=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime,0);
							
					UserDto curUser=new UserDto();
					curUser.setNickname(curUserOpt.get().getUserNickname());
					try {
						curUser.setProfileImg(curUserOpt.get().getUserProfileImg().getBytes(1l, (int)curUserOpt.get().getUserProfileImg().length()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					curUser.setStudying(curUserOpt.get().isUserIsStudying());
					if(curTimeDaily.isPresent())
						curUser.setTodayStudyTime(curTimeDaily.get().getTimeDailyTime());
					else
						curUser.setTodayStudyTime(0);
						
					userList.add(curUser);
				}
				
				result.clear();
				result.put("groupMemberList",userList);
				try {
					result.put("groupProfileImg",group.get().getGroupProfileImg().getBytes(1l, (int)group.get().getGroupProfileImg().length()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				group.get().setGroupProfileImg(null);
				result.put("groupInfo",group.get());
			}
			
		}
		return result;
	}
	
	@GetMapping("/ranking")
	@ApiOperation(value="그룹별 랭킹",notes="파라미터로 그룹번호, datetime, 기간을 받아 그룹별 개인 랭킹목록 반환")
	public Object getGroupRanking(@RequestParam("groupId") int groupId,@RequestParam("datetime") String datetime, @RequestParam("range") String range,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("rankingList",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		Optional<GroupInfo> group=groupRepository.findById(groupId);
		Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, groupId);
		if(user.isPresent()&&group.isPresent()&&groupMember.isPresent()) {
			List<UserDto> userList=new ArrayList<>();
			
			switch(range) {
			case "day":
				datetime=datetime.substring(2,10);
				datetime=datetime.replaceAll("-", "");	
				
				Optional<List<GroupMember>> groupMemberList=groupMemberRepository.findByGroupMemberGroupId(groupId);
				for(GroupMember gm:groupMemberList.get()) {
					Optional<UserInfo> curUser=userRepository.findByUserNickname(gm.getGroupMemberUserNickname());
					Optional<TimeDaily> curTimeDaily=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime,0);
					
					UserDto userDto=new UserDto();
					userDto.setNickname(curUser.get().getUserNickname());
					try {
						userDto.setProfileImg(curUser.get().getUserProfileImg().getBytes(1l, (int)curUser.get().getUserProfileImg().length()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(curTimeDaily.isPresent()) 
						userDto.setTodayStudyTime(curTimeDaily.get().getTimeDailyTime());
					else
						userDto.setTodayStudyTime(0);
					userList.add(userDto);
				}
				break;
			case "week":
				String[] dates=null;
				try {
					dates = commonMethods.getDays(datetime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Optional<List<GroupMember>> groupMemberWeekly=groupMemberRepository.findByGroupMemberGroupId(groupId);
				for(GroupMember gm:groupMemberWeekly.get()) {
					Optional<UserInfo> curUser=userRepository.findByUserNickname(gm.getGroupMemberUserNickname());
					if(curUser.isPresent()) {
						UserDto userDto=new UserDto();
						userDto.setNickname(curUser.get().getUserNickname());
						try {
							userDto.setProfileImg(curUser.get().getUserProfileImg().getBytes(1l, (int)curUser.get().getUserProfileImg().length()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int sum=0;
						for(String s: dates) {
							Optional<TimeDaily> timeDailyEach=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, s,0);
							if(timeDailyEach.isPresent()) 
								sum+=timeDailyEach.get().getTimeDailyTime();
						}						
						userDto.setTodayStudyTime(sum);
						userList.add(userDto);
					}
				}
				break;
			case "month":
				datetime=datetime.substring(2,7);
				datetime=datetime.replaceAll("-", "");
				
				Optional<List<GroupMember>> groupMemberListMonthly=groupMemberRepository.findByGroupMemberGroupId(groupId);
				for(GroupMember gm: groupMemberListMonthly.get()) {
					Optional<TimeMonthly> monthlyList=timeMonthlyRepository.findByTimeMonthlyUserNicknameAndTimeMonthlyYearMonth(gm.getGroupMemberUserNickname(), datetime);
					Optional<UserInfo> curUser=userRepository.findByUserNickname(gm.getGroupMemberUserNickname());
					
					UserDto userDto=new UserDto();
					userDto.setNickname(curUser.get().getUserNickname());
					try {
						userDto.setProfileImg(curUser.get().getUserProfileImg().getBytes(1l, (int)curUser.get().getUserProfileImg().length()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(monthlyList.isPresent()) 
						userDto.setTodayStudyTime(monthlyList.get().getTimeMonthlyTime());
					else
						userDto.setTodayStudyTime(0);
					userList.add(userDto);
				}
			}		
			Collections.sort(userList);
			result.clear();
			result.put("rankingList",userList);
		}
		return result;
	}
	
	@GetMapping("/attendance")
	@ApiOperation(value="출석부",notes="파라미터로 그룹번호, 날짜를 받아 출석부 반환")
	public Object getAttendance(@RequestParam("groupId") int groupId, @RequestParam("datetime") String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> group=groupRepository.findById(groupId);
			if(group.isPresent()) {
				Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, groupId);
				if(groupMember.isPresent()) {
					Optional<List<GroupMember>> groupMemberList=groupMemberRepository.findByGroupMemberGroupId(groupId);
					String[] dates=null;
					try {
						dates = commonMethods.getDays(datetime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					result.clear();
					for(GroupMember gm:groupMemberList.get()) {
						List<TimeDaily> eachTimeDaily=new ArrayList<>();
						for(String s:dates) {
							Optional<TimeDaily> td=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, s, 0);
							if(td.isPresent()) 
								eachTimeDaily.add(td.get());
						}
						result.put(gm.getGroupMemberUserNickname(),eachTimeDaily);
					}
					result.put("success",true);
				}
			}
		}
		return result;
	}
	
	@GetMapping("/that-i-am")
	@ApiOperation(value="내가 속한 그룹 리스트",notes="내가 속한 그룹 리스트를 반환")
	public Object getGroupListThatIAm(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("groupListThatIAm",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<List<GroupMember>> groupMemberList=groupMemberRepository.findByGroupMemberUserNickname(nickname);
			List<GroupInfo> groupList=new ArrayList<>();
			List<GroupProfileDto> groupProfileList=new ArrayList<>();
			for(GroupMember gm : groupMemberList.get()) {
				Optional<GroupInfo> group=groupRepository.findById(gm.getGroupMemberGroupId());
				if(group.isPresent()) {
					try {
						GroupProfileDto gpd=new GroupProfileDto();
						gpd.setGroupId(group.get().getGroupId());
						gpd.setGroupProfileImg(group.get().getGroupProfileImg().getBytes(1l, (int)group.get().getGroupProfileImg().length()));
						groupProfileList.add(gpd);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					group.get().setGroupProfileImg(null);
					groupList.add(group.get());
				}
			}
			result.clear();
			result.put("groupListThatIAm",groupList);
			result.put("groupProfileList",groupProfileList);
		}
		return result;
	}
	
	@PostMapping("/{groupId}")
	@ApiOperation(value="그룹가입",notes="url로 그룹번호, 파라미터로 비밀번호를 받고, 그룹가입에 성공하면 true 반환")
	public Object joinGroup(@PathVariable("groupId") int groupId,@RequestParam(value="password",required=false) String password,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> group=groupRepository.findById(groupId);
			if(group.isPresent()&&group.get().getGroupCurMemberCount()<group.get().getGroupMaxMemberCount()) {
				if(group.get().getGroupPassword()==null||group.get().getGroupPassword().equals(commonMethods.getHashed(password))) {					
					Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, groupId);
					if(!groupMember.isPresent()) {
						GroupMember newGroupMember=new GroupMember();
						newGroupMember.setGroupMemberGroupId(groupId);
						newGroupMember.setGroupMemberUserNickname(nickname);
						groupMemberRepository.save(newGroupMember);
						
						group.get().setGroupCurMemberCount(group.get().getGroupCurMemberCount()+1);
						groupRepository.save(group.get());
						
						result.clear();
						result.put("success",true);
					}
				}
			}
		}
		return result;
	}
	
	@DeleteMapping("/{groupId}")
	@ApiOperation(value="그룹 탈퇴",notes="url로 받은 그룹 번호를 사용해서 그룹 탈퇴에 성공하면 true 반환")
	public Object outGroup(@PathVariable("groupId") int groupId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> group=groupRepository.findById(groupId);
			if(group.isPresent()&&!group.get().getGroupMasterNickname().equals(nickname)) {
				Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, groupId);
				if(groupMember.isPresent()&&!groupMember.get().isGroupMemberIsMaster()) {
					groupMemberRepository.delete(groupMember.get());
					
					group.get().setGroupCurMemberCount(group.get().getGroupCurMemberCount()-1);
					groupRepository.save(group.get());
					
					result.clear();
					result.put("success",true);
				}
			}
		}
		return result;
	}
	
	@PutMapping("/group-master")
	@ApiOperation(value="그룹장 위임",notes="파라미터로 받은 그룹번호와 위임할 사용자의 닉네임을 통해 변경에 성공하면 true 반환")
	public Object changeGroupMaster(@RequestParam("groupId") int groupId, @RequestParam("toChangeNickname") String toChangeNickname,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<UserInfo> userToChange=userRepository.findByUserNickname(toChangeNickname);
			if(userToChange.isPresent()) {
				Optional<GroupInfo> group=groupRepository.findById(groupId);
				if(group.isPresent()&&group.get().getGroupMasterNickname().equals(nickname)) {
					Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, groupId);
					Optional<GroupMember> groupMemberToChange=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(toChangeNickname, groupId);
					if(groupMember.isPresent()&&groupMember.get().isGroupMemberIsMaster()&&
							groupMemberToChange.isPresent()&&!groupMemberToChange.get().isGroupMemberIsMaster()) {
						group.get().setGroupMasterNickname(toChangeNickname);
						groupRepository.save(group.get());
						
						groupMember.get().setGroupMemberIsMaster(false);
						groupMemberToChange.get().setGroupMemberIsMaster(true);
						groupMemberRepository.save(groupMember.get());
						groupMemberRepository.save(groupMemberToChange.get());
						
						result.clear();
						result.put("success",true);
					}
				}
			}
		}
		return result;
	}
	
	@PutMapping("")
	@ApiOperation(value="그룹 정보 수정",notes="바디로 받은 그룹정보로 수정하는데 성공하면 true 반환")
	public Object changeGroupInfo(@RequestBody GroupInfo group,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> groupBefore=groupRepository.findById(group.getGroupId());
			if(groupBefore.isPresent()&&groupBefore.get().getGroupMasterNickname().equals(nickname)&&
					(group.getGroupMaxMemberCount()==0||groupBefore.get().getGroupCurMemberCount()<group.getGroupMaxMemberCount())) {
				Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, group.getGroupId());
				if(groupMember.isPresent()&&groupMember.get().isGroupMemberIsMaster()) {
					
					if(group.getGroupMaxMemberCount()!=0)
						groupBefore.get().setGroupMaxMemberCount(group.getGroupMaxMemberCount());
					if(group.getGroupPassword()!=null)
						groupBefore.get().setGroupPassword(commonMethods.getHashed(group.getGroupPassword()));
					else
						groupBefore.get().setGroupPassword(null);
					groupBefore.get().setGroupNotice(group.getGroupNotice());
					groupBefore.get().setGroupGoalDate(group.getGroupGoalDate());
					groupBefore.get().setGroupGoalTitle(group.getGroupGoalTitle());
					groupRepository.save(groupBefore.get());
					
					result.clear();
					result.put("success",true);
				}
			}
		}
		return result;
	}
	
	@PutMapping("/profile-img")
	@ApiOperation(value="그룹 프사 변경",notes="파라미터로 그룹 번호와 그룹 프사를 받아 변경에 성공하면 true")
	public Object changeProfileImg(@RequestParam("file") MultipartFile file,@RequestParam("groupId") int groupId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<GroupInfo> group=groupRepository.findById(groupId);
			if(group.isPresent()&&group.get().getGroupMasterNickname().equals(nickname)) {
				Optional<GroupMember> groupMember=groupMemberRepository.findByGroupMemberUserNicknameAndGroupMemberGroupId(nickname, groupId);
				if(groupMember.isPresent()&&groupMember.get().isGroupMemberIsMaster()) {
					byte[] bytes;
					try {
						bytes = file.getBytes();
						try {
							Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

							group.get().setGroupProfileImg(blob);
							groupRepository.save(group.get());
							result.clear();
							result.put("result", true);
						} catch (SerialException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
}
