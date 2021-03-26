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
public class ScoreDaily {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int scoreDailyId;
	
	private String scoreDailyUserNickname;
	private String scoreDailyYearMonthDay;
	private int scoreDailyTime;
	private int scoreDailyAction;//0 : 집중, 1 : 비집중
}								 //세분화시  0 : 집중, 1 : 폰,
								 //2 : 자리비움, 3 : 멍때림
