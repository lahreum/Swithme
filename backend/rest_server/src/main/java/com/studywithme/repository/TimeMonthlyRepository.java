package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.TimeMonthly;

public interface TimeMonthlyRepository extends JpaRepository<TimeMonthly,Integer>{
	Optional<TimeMonthly> findByTimeMonthlyUserNicknameAndTimeMonthlyYearMonth(String timeMonthlyUserNickname,String TimeMonthlyYearMonth);
	Optional<List<TimeMonthly>> findByTimeMonthlyYearMonth(String timeMonthlyYearMonth,Sort sort);
}
