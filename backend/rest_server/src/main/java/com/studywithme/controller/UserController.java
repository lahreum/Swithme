package com.studywithme.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studywithme.DtoOnlyReturn.UserDto;
import com.studywithme.config.CommonMethods;
import com.studywithme.config.JwtService;
import com.studywithme.entity.TimeMonthly;
import com.studywithme.entity.UserInfo;
import com.studywithme.repository.DefaultProfileImgRepository;
import com.studywithme.repository.TimeMonthlyRepository;
import com.studywithme.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	CommonMethods commonMethods;
	
	@Autowired
	DefaultProfileImgRepository defaultProfileImgRepository;
	
	@Autowired
	TimeMonthlyRepository timeMonthlyRepository;
	
	@PostMapping("/signup")
	@ApiOperation(value="회원가입",notes="User 정보를 body로 받아 db에 저장\n인터셉터에서 제외")
	public Object createUser(@RequestBody UserInfo user) {
		Map<String,Object> result=new HashMap<>();
		
		if(!userRepository.findByUserNickname(user.getUserNickname()).isPresent()) {
			String hashed=commonMethods.getHashed(user.getUserPassword());
			if(hashed!=null) {
				user.setUserPassword(hashed);
				user.setUserProfileImg(defaultProfileImgRepository.findById(1).get().getDefaultProfileImgData());
				userRepository.save(user);
				result.put("success",true);
			}
			else
				result.put("success",false);
		}
		else 
			result.put("success",false);
		
		return result;
	}
	
	@PostMapping("/signup-social")
	@ApiOperation(value="회원가입(소셜)", notes="소셜 로그인으로 처음 접속했을 때 자동 회원가입\n닉네임과 이메일을 담은 토큰 불러와 처리")
	public Object createUserSocial(@RequestBody Map<String, String> map) {
		Map<String, Object> result = new HashMap<>();
		
		String nickname = map.get("nickname");
		String token = map.get("token");
		
		UserInfo user = new ObjectMapper().convertValue(jwtService.get(token).get("User"), UserInfo.class);
		user.setUserNickname(nickname);
		String jwtToken = jwtService.create(user);
		
		userRepository.save(user);
		result.put("success", true);
		result.put("token", jwtToken);
		
		return result;
	}
	
	@PostMapping("/login")
	@ApiOperation(value="로그인",notes="id와 password를 파라미터로 받아 헤더에 jwt 반환\n인터셉터에서 제외")
	public Object loginUser(@RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword, HttpServletResponse resp) {

		Map<String,Object> result=new HashMap<>();
		
		String hashed=commonMethods.getHashed(userPassword);
		Optional<UserInfo> user=userRepository.findByUserIdAndUserPassword(userId, hashed);
		if(user.isPresent()) {
			String token=jwtService.create(user.get());
			resp.setHeader("jwt-auth-token",token);
			result.put("success",true);
		}
		else
			result.put("success",false);
		
		return result;
	}
	
	@GetMapping("/id")
	@ApiOperation(value="id 중복체크",notes="id를 파라미터로 받아 중복체크\n인터셉터에서 제외")
	public Object checkIdDuplicated(@RequestParam("userId") String userId) {
		Map<String,Object> result=new HashMap<>();
		if(userRepository.findById(userId).isPresent())
			result.put("isPresent",true);
		else
			result.put("isPresent",false);
		
		return result;
	}
	
	@GetMapping("/nickname")
	@ApiOperation(value="닉네임 중복체크",notes="닉네임을 파라미터로 받아 중복체크\n인터셉터에서 제외")
	public Object checkNicknameDuplicated(String userNickname) {
		Map<String,Object> result=new HashMap<>();
		if(userRepository.findByUserNickname(userNickname).isPresent())
			result.put("isPresent",true);
		else
			result.put("isPresent",false);
		
		return result;
	}
	
	@GetMapping("/email")
	@ApiOperation(value="회원가입시 이메일 인증",notes="이메일을 파라미터로 받아 메일로 링크 발송하고 response로 반환\n인터셉터에서 제외")
	public Object emailVaild(@RequestParam("userEmail") String userEmail) throws AddressException, MessagingException {
		Map<String,Object> result=new HashMap<>();
		String host="smtp.naver.com";
		final String username="swithmedev";
		final String password="swithme103";
		int port=465;
		
		Optional<UserInfo> user=userRepository.findById(userEmail);
		if(!user.isPresent()) {
			String subject="회원가입 인증번호입니다.";
					
			int validNum=(int)(Math.random()*10000);
			String body="다음의 인증번호를 입력해주세요!\n"+Integer.toString(validNum);
			
			Properties props=System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port",port);
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.ssl.enable","true");
			props.put("mail.smtp.ssl.trust",host);
			
			Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				String un=username;
				String pw=password;
				protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
					return new javax.mail.PasswordAuthentication(un,pw);
				}
			});
			session.setDebug(true);
			Message mimeMessage=new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("swithmedev@naver.com"));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			
			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);
			Transport.send(mimeMessage);
			
			result.put("success",true);
			result.put("validNum",validNum);
		}
		else
			result.put("success",false);
		return result;
	}
	
	@PostMapping("/password")
	@ApiOperation(value="비밀번호 분실",notes="이메일을 파라미터로 받아 메일로 링크 발송\n인터셉터에서 제외")
	public Object sendEmail(@RequestParam("userEmail") String userEmail) throws AddressException, MessagingException {
		Map<String,Object> result=new HashMap<>();
		String host="smtp.naver.com";
		final String username="swithmedev";
		final String password="swithme103";
		int port=465;
		
		Optional<UserInfo> user=userRepository.findById(userEmail);
		if(user.isPresent()) {
			String subject="비밀번호 변경 링크입니다.";
					
			String link="여기에 링크써줘";
			String body="안녕하세요 "+user.get().getUserNickname()+"님!\n"+
					"다음 링크로 이동하시면 비밀번호를 바꾸실 수 있습니다.\n"+
					link;
			
			Properties props=System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port",port);
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.ssl.enable","true");
			props.put("mail.smtp.ssl.trust",host);
			
			Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				String un=username;
				String pw=password;
				protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
					return new javax.mail.PasswordAuthentication(un,pw);
				}
			});
			session.setDebug(true);
			Message mimeMessage=new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("swithmedev@naver.com"));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			
			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);
			Transport.send(mimeMessage);
			
			result.put("success",true);
		}
		else
			result.put("success",false);
		return result;
	}
	
	@PutMapping("/password")//파라미터가 애매함 -> 링크로 들어간경우와 직접 들어간경우 나눠서 해야하나?
	@ApiOperation(value="비밀번호 변경",notes="id와 바꿀 password를 파라미터로 받아 db 업데이트\n인터셉터에서 제외해야하나?")
	public Object changePassword(@RequestParam("userId") String userId,@RequestParam("newPassword") String newPassword) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		Optional<UserInfo> user=userRepository.findById(userId);
		if(user.isPresent()) {
			user.get().setUserPassword(commonMethods.getHashed(newPassword));
			userRepository.save(user.get());
			
			result.clear();
			result.put("success",true);
		}
		
		return result;
	}
	
	@PutMapping("/nickname")
	@ApiOperation(value="닉네임 변경",notes="파라미터로 변경할 닉네임을 받아 수정하고 jwt토큰 반환")
	public Object changeNickname(@RequestParam String newNickname,HttpServletRequest req,HttpServletResponse resp) {
		Map<String,Object> result=new HashMap<>();
		result.put("success",false);
		
		String oldNickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(oldNickname);
		if(user.isPresent()) {
			user.get().setUserNickname(newNickname);
			userRepository.save(user.get());
			String jwt=jwtService.create(user.get());
			
			resp.setHeader("jwt-auth-token", jwt);
			result.clear();
			result.put("success",true);
		}
		
		return result;
	}

	@GetMapping("")
	@ApiOperation(value="본인정보 받아오기",notes="헤더의 jwt를 기반으로 사용자 정보 반환")
	public Object findUser(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();

		String id=commonMethods.getUserId(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findById(id);
		if(user.isPresent()) {
			user.get().setUserPassword(null);
//			user.setProfileImg(user.get().getUserProfileImg().getBytes(1l, (int)user.get().getUserProfileImg().length()));
			try {
				result.put("profileImg",user.get().getUserProfileImg().getBytes(1l, (int)user.get().getUserProfileImg().length()));
				user.get().setUserProfileImg(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result.put("data",user.get());
		}
		else
			result.put("data",null);
		
		return result;
	}
	
	@PostMapping("/mypage")
	@ApiOperation(value="마이페이지에 접근하기 위해 비밀번호 입력",notes="비밀번호가 올바르다면 true 반환")
	public Object showMypage(@RequestParam("userPassword") String userPassword,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		String id=commonMethods.getUserId(req.getHeader("jwt-auth-token"));
		String password=commonMethods.getHashed(userPassword);
		
		Optional<UserInfo> user=userRepository.findByUserIdAndUserPassword(id, password);
		if(user.isPresent()) 
			result.put("isCorrect",true);
		else
			result.put("isCorrect",false);
		
		return result;
	}
	
	@PutMapping("/profile-img")
	@ApiOperation(value="프로필 이미지 변경",notes="프로필 이미지를 바꾸는 데 성공했다면 true 반환")
	public Object changeProfileImg(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		String id=commonMethods.getUserId(req.getHeader("jwt-auth-token"));
		
		byte[] bytes;

		result.put("result", false);
		Optional<UserInfo> user = userRepository.findById(id);
		if (user.isPresent()) {
			try {
				bytes = file.getBytes();
				try {
					Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

					user.get().setUserProfileImg(blob);
					userRepository.save(user.get());
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
		return result;
	}
	
	@PutMapping("/message")
	@ApiOperation(value="상태메세지 변경",notes="상태메세지를 바꾸는 데 성공했다면 true 반환")
	public Object changeMessage(@RequestParam("message") String message,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		String id=commonMethods.getUserId(req.getHeader("jwt-auth-token"));
		Optional<UserInfo> user=userRepository.findById(id);
		if(user.isPresent()) {
			user.get().setUserMessage(message);
			userRepository.save(user.get());
			result.put("success",true);
		}
		else
			result.put("success",false);
		
		return result;
	}
	
	@PutMapping("/start")
	@ApiOperation(value="공부시작 설정",notes="사용자의 상태를 공부중으로 바꿈")
	public Object startStudy(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		result.put("success",false);
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			user.get().setUserIsStudying(true);
			userRepository.save(user.get());
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@PutMapping("/end")
	@ApiOperation(value="공부종료 설정",notes="사용자의 상태를 공부중이 아님으로 바꿈")
	public Object endStudy(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		result.put("success",false);
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			user.get().setUserIsStudying(false);
			userRepository.save(user.get());
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
		
	@GetMapping("/ranking")
	@ApiOperation(value="랭킹 리스트",notes="파라미터로 받은 datetime의 해당 월의 월간 순위 반환")
	public Object getAllRanking(@RequestParam("datetime") String datetime) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("allRankingList",null);
		
		datetime=datetime.substring(2,7);
		datetime=datetime.replaceAll("-", "");
		
		Optional<List<TimeMonthly>> timeMonthlyList=timeMonthlyRepository.findByTimeMonthlyYearMonth(datetime, Sort.by("timeMonthlyTime").descending());
		if(timeMonthlyList.isPresent()) {
			List<UserDto> userList=new ArrayList<>();
			for(TimeMonthly tm:timeMonthlyList.get()) {
				Optional<UserInfo> user=userRepository.findByUserNickname(tm.getTimeMonthlyUserNickname());
				if(user.isPresent()) {
					UserDto userDto=new UserDto();
					userDto.setNickname(user.get().getUserNickname());
					try {
						userDto.setProfileImg(user.get().getUserProfileImg().getBytes(1l, (int)user.get().getUserProfileImg().length()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					userDto.setTodayStudyTime(tm.getTimeMonthlyTime());
					
					userList.add(userDto);
				}
			}
			result.clear();
			result.put("allRankingList",userList);
		}
		return result;
	}
}
