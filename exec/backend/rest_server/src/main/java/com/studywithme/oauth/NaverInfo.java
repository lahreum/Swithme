package com.studywithme.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NaverInfo {
	@JsonProperty("email")
	private String naverEmail;
	
	@JsonProperty("name")
	private String naverName;
	
	@JsonProperty("profile_image")
	private String naverPicture;
}
