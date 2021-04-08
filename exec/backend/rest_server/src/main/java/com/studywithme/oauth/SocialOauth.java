package com.studywithme.oauth;

public interface SocialOauth {
	public String requestAuth();
	public Token requestToken(String code, String state);
	public TokenInfo getTokenInfo(Token token);
	public String checkUserAccount(String type, TokenInfo tokenInfo);
}
