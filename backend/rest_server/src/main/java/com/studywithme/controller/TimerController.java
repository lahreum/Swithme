package com.studywithme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.config.CommonMethods;
import com.studywithme.entity.TimeDaily;
import com.studywithme.entity.TimeHourly;
import com.studywithme.entity.User;
import com.studywithme.repository.TimeDailyRepository;
import com.studywithme.repository.TimeHourlyRepository;
import com.studywithme.repository.TimeMonthlyRepository;
import com.studywithme.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/timer")
public class TimerController {
	
	@Autowired
	CommonMethods commonMethods;
	
	@Autowired
	TimeHourlyRepository timeHourlyRepository;
	
	@Autowired
	TimeDailyRepository timeDailyRepository;
	
	@Autowired
	TimeMonthlyRepository timeMonthlyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/today")
	@ApiOperation(value="공부시간 불러오기",notes="파라미터로 받은 날짜(오늘)에 맞는 공부시간을 불러온다")
	public Object getTodayStudyTime(@RequestParam String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("todayStudyTime",null);
		
		datetime=datetime.substring(2,10);
		datetime=datetime.replaceAll("-", "");
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		Optional<TimeDaily> daily=timeDailyRepository
				.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime,0);
		if(user.isPresent()&&daily.isPresent()) {
			result.clear();
			result.put("todayStudyTime",daily.get().getTimeDailyTime());
		}
		
		return result;
	}
	
	@PostMapping("/today")
	@ApiOperation(value="공부시간 저장하기",notes="파라미터로 받은 공부시간, 날짜, 행동으로 db에 저장하거나 수정이 성공한다면 true 반환")
	public Object saveTodayStudyTime(@RequestParam int studyTime,@RequestParam String datetime,@RequestParam int action,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		datetime=datetime.substring(2,10);
		datetime=datetime.replaceAll("-", "");
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		Optional<TimeDaily> daily=timeDailyRepository
				.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime,action);
		
		if(user.isPresent()) {
			if(daily.isPresent()) {
				daily.get().setTimeDailyTime(studyTime);
				timeDailyRepository.save(daily.get());
			}
			else {
				TimeDaily timeDaily=new TimeDaily();
				timeDaily.setTimeDailyAction(action);
				timeDaily.setTimeDailyTime(studyTime);
				timeDaily.setTimeDailyUserNickname(nickname);
				timeDaily.setTimeDailyYearMonthDay(datetime);
				timeDailyRepository.save(timeDaily);
			}
			result.put("success",true);
		}
		return result;
	}
	
	@GetMapping("/{range}")
	@ApiOperation(value="시간대별 공부시간 불러오기",notes="url에 day, week, month 중 어떤것이 오냐에 따라 각각의 시간대별 공부시간 반환")
	public Object averageStudyTimeForEachHour(@PathVariable String range,@RequestParam String datetime, HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		int[] average=new int[24];
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		datetime=datetime.substring(2,13);
		
		switch(range) {
		case "day":
			
			break;
		case "week":
			
			break;
		case "month":
			
		}
		
		return result;
	}
	
	//--------------------------------------------------------
	public List<TimeHourly> getHourlyStudyTimeForEachDay(String datetime, String nickname){
		Optional<List<TimeHourly>> list=timeHourlyRepository.findByTimeHourlyYearMonthDayHourAndTimeHourlyUserNickname(datetime, nickname);
		if(list.isPresent())
			return list.get();
		return null;
	}
}
