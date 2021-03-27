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
public class TimeMonthly {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int timeMonthlyId;
	
	private String timeMonthlyUserNickname;
	private String timeMonthlyYearMonth;
	private int timeMonthlyTime;
}
