package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.TimeHourly;

public interface TimeHourlyRepository extends JpaRepository<TimeHourly,Integer> {
	Optional<List<TimeHourly>> findByTimeHourlyUserNicknameAndTimeHourlyYearMonthDayHourStartingWith(String timeHourlyUserNickname,String timeHourlyYearMonthDay);
	Optional<TimeHourly> findByTimeHourlyUserNicknameAndTimeHourlyYearMonthDayHour(String timeHourlyUserNickname,String timeHourlyYearMonthDayHour);
}
