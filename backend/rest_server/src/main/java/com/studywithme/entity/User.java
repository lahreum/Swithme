package com.studywithme.entity;

import java.sql.Blob;

import javax.persistence.Entity;
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
public class User {

	@Id
	private String userId;
	
	private String userNickname;
	private String userPassword;
	private String userMessage;
	private Blob userProfileImg;
}
