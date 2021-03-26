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
public class Reply {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int replyId;
	
	private int replyBoardId;
	private String replyWriter;
	private String replyContent;
	@Column(insertable=false,updatable=false)
	private String replyDate;
}
