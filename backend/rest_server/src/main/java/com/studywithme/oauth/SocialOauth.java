package com.studywithme.oauth;

public interface SocialOauth {
	public String getOauthRedirectUri();
	public Token requestToken(String code);
	public String getTokenInfo(String type, Token token);
}
