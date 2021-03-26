package com.studywithme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attendance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int attendanceId;
	
	private String attendanceUserNickname;
	@Column(insertable=false, updatable=false)
	private String attendanceDate;
}
