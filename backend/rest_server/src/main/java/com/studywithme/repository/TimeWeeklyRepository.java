package com.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.TimeWeekly;

public interface TimeWeeklyRepository extends JpaRepository<TimeWeekly,Integer>{
	
}
