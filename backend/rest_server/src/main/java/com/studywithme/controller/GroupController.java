package com.studywithme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.config.CommonMethods;
import com.studywithme.entity.Group;
import com.studywithme.entity.User;
import com.studywithme.repository.GroupMemberRepository;
import com.studywithme.repository.GroupRepository;
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
	
	
	@PostMapping("")
	@ApiOperation(value="그룹 생성",notes="바디로 받은 group으로 그룹생성에 성공하면 true 반환")
	public Object makeGroup(@RequestBody Group group,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			group.setGroupMasterNickname(nickname);
			groupRepository.save(group);
			
			result.clear();
			result.put("success",true);			
		}
		return result;
	}
	
	@DeleteMapping("")
	@ApiOperation(value="그룹 삭제",notes="파라미터로 그룹번호를 받아 삭제하고 성공하면 true 반환")
	public Object deleteGroup(@RequestParam int groupId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<Group> group=groupRepository.findById(groupId);
			if(group.isPresent()&&group.get().getGroupMasterNickname().equals(nickname)) {
				groupRepository.delete(group.get());
				
				result.clear();
				result.put("success",true);
			}			
		}
		return result;
	}
	
	@GetMapping("")
	@ApiOperation(value="모든 그룹리스트 받기",notes="모든 그룹리스트를 받아온다")
	public Object getGroupList(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("groupList",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			List<Group> groupList=groupRepository.findAll();
			if(groupList!=null) {
				result.clear();
				result.put("groupList",groupList);
			}
		}		
		return result;
	}
	
	@GetMapping("/{groupId}")
	@ApiOperation(value="그룹 상세정보 보기",notes="경로로 받은 그룹번호에 맞는 그룹 반환")
	public Object getGroupInfo(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		
		return result;
	}
	
}
