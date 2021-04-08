package com.studywithme.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Token {
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private int expiresIn;
	
	@JsonProperty("id_token")
	private String idToken;
	
	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("token_type")
	private String tokenType;
}
