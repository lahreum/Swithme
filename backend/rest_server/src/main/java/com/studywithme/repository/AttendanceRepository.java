package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
	Optional<List<Attendance>> findByAttendanceUserNickname(String attendanceUserNickname);
	Optional<Attendance> findByAttendanceUserNicknameAndAttendanceDate(String attendanceUserNickname,String attendanceDate);
}
