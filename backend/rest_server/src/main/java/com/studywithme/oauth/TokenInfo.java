package com.studywithme.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenInfo {
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("picture")
	private String picture;
}
