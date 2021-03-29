package com.studywithme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.TimeDaily;

public interface TimeDailyRepository extends JpaRepository<TimeDaily,Integer> {
	Optional<TimeDaily> findByTimeDailyUserNicknameAndTimeDailyYearMonthDayAndTimeDailyAction(String timeDailyUserNickname,String timeDailyYearMonthDay,int timeDailyAction);
}
