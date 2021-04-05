package com.studywithme.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.studywithme.oauth.GoogleOauth;
import com.studywithme.oauth.NaverOauth;
import com.studywithme.oauth.Token;
import com.studywithme.oauth.TokenInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {
	private final GoogleOauth googleOauth;
	private final NaverOauth naverOauth;
	private final HttpServletResponse response;
	
	public void requestAuth(String type) {
		String redirectUri;
		
		switch (type) {
		case "google":
			redirectUri = googleOauth.requestAuth();
			break;
		case "naver":
			redirectUri = naverOauth.requestAuth();
			break;
		default:
			throw new IllegalArgumentException("오류가 발생하였습니다.");
		}
		
		try {
			response.sendRedirect(redirectUri);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void checkUserAccount(String type, TokenInfo tokenInfo) {
		String redirectUri;
		
		switch (type) {
		case "google":
			redirectUri = googleOauth.checkUserAccount(tokenInfo);
			break;
		default:
			throw new IllegalArgumentException("오류가 발생하였습니다.");
		}
		
		try {
			response.sendRedirect(redirectUri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
