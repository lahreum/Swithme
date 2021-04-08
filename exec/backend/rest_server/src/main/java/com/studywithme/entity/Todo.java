package com.studywithme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int todoId;
	
	private String todoUserNickname;
	private String todoContent;
	@Column(insertable=false)
	private boolean todoFinish;
	@Column(updatable=false)
	private String todoDate;
}
