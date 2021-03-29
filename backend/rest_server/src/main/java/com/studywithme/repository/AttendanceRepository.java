package com.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

}
