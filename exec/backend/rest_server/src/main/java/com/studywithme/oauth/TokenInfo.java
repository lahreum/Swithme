package com.studywithme.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenInfo {
	// Google
	@JsonProperty("email")
	private String googleEmail;
	
	@JsonProperty("name")
	private String googleName;
	
	@JsonProperty("picture")
	private String googlePicture;
	
	// Naver
	@JsonProperty("response")
	private NaverInfo naver;
}
