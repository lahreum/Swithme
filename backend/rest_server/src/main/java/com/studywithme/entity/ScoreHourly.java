package com.studywithme.entity;

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
public class ScoreHourly {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int scoreHourlyId;
	
	private String scoreHourlyUserNickname;
	private String scoreHourlyYearMonthDayHour;
	private int scoreHourlyTime;
}
