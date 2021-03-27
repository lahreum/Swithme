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
public class TimeDaily {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int timeDailyId;
	
	private String timeDailyUserNickname;
	private String timeDailyYearMonthDay;
	private int timeDailyTime;
	private int timeDailyAction;//0 : 집중, 1 : 비집중
}								 //세분화시  0 : 집중, 1 : 폰,
								 //2 : 자리비움, 3 : 멍때림
