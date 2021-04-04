package com.studywithme.controller;

import java.text.ParseException;
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
import com.studywithme.entity.Attendance;
import com.studywithme.entity.TimeDaily;
import com.studywithme.entity.TimeHourly;
import com.studywithme.entity.TimeMonthly;
import com.studywithme.entity.UserInfo;
import com.studywithme.repository.AttendanceRepository;
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
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	
	@GetMapping("/today")
	@ApiOperation(value="공부시간 불러오기",notes="파라미터로 받은 날짜(오늘)에 맞는 공부시간을 불러온다")
	public Object getTodayStudyTime(@RequestParam("datetime") String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("todayStudyTime",null);
		
		datetime=datetime.substring(2,10);
		datetime=datetime.replaceAll("-", "");
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		Optional<TimeDaily> daily=timeDailyRepository
				.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime,0);
		if(user.isPresent()&&daily.isPresent()) {
			result.clear();
			result.put("todayStudyTime",daily.get().getTimeDailyTime());
		}
		
		return result;
	}
	
	@GetMapping("/hourly/{range}")//개인 통계에 필요한거
	@ApiOperation(value="시간대별 공부시간 불러오기",notes="url에 day, week, month 중 어떤것이 오냐에 따라 각각의 시간대별 평균 공부시간 반환")
	public Object averageStudyTimeForEachHour(@PathVariable("range") String range,@RequestParam("datetimeOrigin") String datetimeOrigin, HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		int[] sum=new int[24];
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		String datetime=datetimeOrigin.substring(2,13);//년월일시
		datetime=datetime.replaceAll("-", "");
		datetime=datetime.replaceAll(" ","");
		
		result.put("eachTimeAverage",null);
		List<TimeHourly> list=null;
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if (user.isPresent()) {
			switch (range) {
			case "day":
				datetime=datetime.substring(0,datetime.length()-2);
				list = getHourlyStudyTimeForEachDay(datetime, nickname);
				if(list!=null)
					sum=sumOfTimes(sum,list);
				break;
			case "week":// commonMethods에 있는거 써서 7일간 받기?
				int daysLength=0;
				String[] dates=null;
				
				try {
					daysLength=commonMethods.whatDay(datetimeOrigin);
					dates=commonMethods.getDays(datetimeOrigin);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				for(String s:dates) {
					Optional<List<TimeHourly>> thWeekly=timeHourlyRepository.findByTimeHourlyUserNicknameAndTimeHourlyYearMonthDayHourStartingWith(nickname, s);
					if(thWeekly.isPresent()) {
						sum=sumOfTimes(sum,thWeekly.get());
						for(int i=0;i<24;i++)
							sum[i]/=daysLength;
					}
				}
				break;
			case "month":
				int days=Integer.parseInt(datetime.substring(datetime.length()-4,datetime.length()-2));
				datetime=datetime.substring(0,datetime.length()-4);
				list=getHourlyStudyTimeForEachDay(datetime,nickname);
				if(list!=null) {
					sum = sumOfTimes(sum, list);
					for (int i = 0; i < 24; i++)
						sum[i]/=days;
				}
			}
			result.clear();
			result.put("eachTimeAverage",sum);
		}
		return result;
	}
	
	@PostMapping("/study-time")
	@ApiOperation(value="시간당 공부시간 저장",notes="파라미터로 datetime, 공부시간을 받아 매 시간당 공부 시간을 저장")
	public Object addStudyTimeForEachHour(@RequestParam("datetime") String datetime,@RequestParam("studyTime") int studyTime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			datetime=datetime.substring(2,13);
			datetime=datetime.replaceAll("-", "");
			datetime=datetime.replaceAll(" ", "");
			Optional<TimeHourly> timeHourly=timeHourlyRepository.findByTimeHourlyUserNicknameAndTimeHourlyYearMonthDayHour(nickname, datetime);
			
			if(timeHourly.isPresent()) {//hourly에 저장하고 daily,monthly에도 반영
				timeHourly.get().setTimeHourlyTime(timeHourly.get().getTimeHourlyTime()+studyTime);
				timeHourlyRepository.save(timeHourly.get());
			}
			else {
				TimeHourly newTimeHourly=new TimeHourly();
				newTimeHourly.setTimeHourlyTime(studyTime);
				newTimeHourly.setTimeHourlyUserNickname(nickname);
				newTimeHourly.setTimeHourlyYearMonthDayHour(datetime);
				timeHourlyRepository.save(newTimeHourly);
			}

			datetime=datetime.substring(0,datetime.length()-2);
			Optional<TimeDaily> timeDaily=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime, 0);
			if(timeDaily.isPresent()) {
				timeDaily.get().setTimeDailyTime(timeDaily.get().getTimeDailyTime()+studyTime);
				timeDailyRepository.save(timeDaily.get());
			}
			else {
				TimeDaily newTimeDaily=new TimeDaily();
				newTimeDaily.setTimeDailyAction(0);
				newTimeDaily.setTimeDailyTime(studyTime);
				newTimeDaily.setTimeDailyUserNickname(nickname);
				newTimeDaily.setTimeDailyYearMonthDay(datetime);
				timeDailyRepository.save(newTimeDaily);
				
				Optional<Attendance> attendance=attendanceRepository.findByAttendanceUserNicknameAndAttendanceDate(nickname, datetime);
				if(!attendance.isPresent()) {
					Attendance newAttendance=new Attendance();
					newAttendance.setAttendanceDate(datetime);
					newAttendance.setAttendanceUserNickname(nickname);
					attendanceRepository.save(newAttendance);
				}
			}
			datetime=datetime.substring(0,datetime.length()-2);
			Optional<TimeMonthly> timeMonthly=timeMonthlyRepository.findByTimeMonthlyUserNicknameAndTimeMonthlyYearMonth(nickname, datetime);
			if(timeMonthly.isPresent()) {
				timeMonthly.get().setTimeMonthlyTime(timeMonthly.get().getTimeMonthlyTime()+studyTime);
				timeMonthlyRepository.save(timeMonthly.get());
			}
			else {
				TimeMonthly newTimeMonthly=new TimeMonthly();
				newTimeMonthly.setTimeMonthlyTime(studyTime);
				newTimeMonthly.setTimeMonthlyUserNickname(nickname);
				newTimeMonthly.setTimeMonthlyYearMonth(datetime);
				timeMonthlyRepository.save(newTimeMonthly);
			}
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@PostMapping("/not-study-time")
	@ApiOperation(value="방해요소별 시간 저장",notes="파라미터로 datetime,비집중한 시간, 행동번호를 받아 db에 저장 성공하면 true 반환")
	public Object addNotStudyTime(@RequestParam("datetime") String datetime,@RequestParam("notStudyTime") int notStudyTime,@RequestParam("action") int action,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		datetime=datetime.substring(2,10);
		datetime=datetime.replaceAll("-", "");
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<TimeDaily> timeDaily=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(nickname, datetime, action);
			if(timeDaily.isPresent()) {
				timeDaily.get().setTimeDailyTime(timeDaily.get().getTimeDailyTime()+notStudyTime);
				timeDailyRepository.save(timeDaily.get());
			}
			else {
				TimeDaily newTimeDaily=new TimeDaily();
				newTimeDaily.setTimeDailyAction(action);
				newTimeDaily.setTimeDailyTime(notStudyTime);
				newTimeDaily.setTimeDailyUserNickname(nickname);
				newTimeDaily.setTimeDailyYearMonthDay(datetime);
				timeDailyRepository.save(newTimeDaily);
			}
		}
		return result;
	}
	
	@GetMapping("/hourly")//찬찬규용
	@ApiOperation(value="현재시간의 공부시간 받아오기(hourly)",notes="파라미터로 넘어온 현재 시간을 기준으로 db에 저장된 정보가 있으면 반환\n찬찬규 공부 시작버튼 누를때 사용할용도")
	public Object getNowHourlyStudyTime(@RequestParam("datetime") String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("curStudyTime",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			datetime=datetime.substring(2,13);
			datetime=datetime.replaceAll("-","");
			datetime=datetime.replaceAll(" ", "");
			Optional<TimeHourly> timeHourly=timeHourlyRepository.findByTimeHourlyUserNicknameAndTimeHourlyYearMonthDayHour(nickname, datetime);
			if(timeHourly.isPresent()) {
				result.clear();
				result.put("curStudyTime",timeHourly.get().getTimeHourlyTime());
			}
		}
		return result;
	}
	
	@GetMapping("/not-study")
	@ApiOperation(value="일간 방해용인 목록",notes="파라미터로 날짜를 받아 해당 날짜의 일간 방해요인 반환")
	public Object getNotStudyTimeAndCause(@RequestParam("datetime") String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("disturbingCause",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		datetime=datetime.substring(2,10);
		datetime=datetime.replaceAll("-", "");
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<List<TimeDaily>> list=timeDailyRepository.findByTimeDailyUserNicknameAndTimeDailyYearMonthDay(nickname, datetime);
			if(list.isPresent()) {
				result.clear();
				result.put("disturbingCause",list.get());
			}			
		}
		
		return result;
	}
	
	@GetMapping("/attendance")
	@ApiOperation(value="출석 불러오기",notes="해당 사용자의 출석 일자들을 반환")
	public Object getAttendanceDates(HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("attendanceDateList",null);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Optional<List<Attendance>> attendanceList=attendanceRepository.findByAttendanceUserNickname(nickname);
			if(attendanceList.isPresent()) {
				result.clear();
				result.put("attendanceDateList",attendanceList.get());
			}			
		}
		return result;
	}	
	
	//--------------------------------------------------------
	
	public List<TimeHourly> getHourlyStudyTimeForEachDay(String datetime, String nickname){
		Optional<List<TimeHourly>> list=timeHourlyRepository.findByTimeHourlyUserNicknameAndTimeHourlyYearMonthDayHourStartingWith(nickname, datetime);
		if(list.isPresent())
			return list.get();
		return null;
	}
	
	public int[] sumOfTimes(int[] arrParam,List<TimeHourly> list) {
		for(TimeHourly th:list) {
			String curDate=th.getTimeHourlyYearMonthDayHour();
			curDate=curDate.substring(curDate.length()-2,curDate.length());
			arrParam[Integer.parseInt(curDate)]+=th.getTimeHourlyTime();
		}
		return arrParam;
	}
}
