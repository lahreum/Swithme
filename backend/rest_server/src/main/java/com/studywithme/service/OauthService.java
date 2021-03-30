package com.studywithme.service;

import org.springframework.stereotype.Service;

import com.studywithme.oauth.GoogleOauth;
import com.studywithme.oauth.Token;
import com.studywithme.oauth.TokenInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {
	private final GoogleOauth googleOauth;
	
	public String requestAuth(String type) {
		String redirectUri;
		
		switch (type) {
		case "google":
			redirectUri = googleOauth.requestAuth();
			break;
		default:
			throw new IllegalArgumentException("오류가 발생하였습니다.");
		}
		
		return redirectUri;
	}
	
	public Token requestToken(String type, String code) {
		if(code==null) throw new NullPointerException("오류가 발생하였습니다.");
		
		switch (type) {
		case "google":
			return googleOauth.requestToken(code);
		default:
			throw new IllegalArgumentException("오류가 발생하였습니다.");
		}
	}
	
	public TokenInfo getTokenInfo(String type, Token token) {
		switch (type) {
		case "google":
			return googleOauth.getTokenInfo(token);
		default:
			throw new IllegalArgumentException("오류가 발생하였습니다.");
		}
	}
	
	public String checkUserAccount(String type, TokenInfo tokenInfo) {
		switch (type) {
		case "google":
			return googleOauth.checkUserAccount(tokenInfo);
		default:
			throw new IllegalArgumentException("오류가 발생하였습니다.");
		}
	}
}
