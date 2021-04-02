package com.studywithme.oauth;

public interface SocialOauth {
	public String requestAuth();
	public Token requestToken(String code);
	public TokenInfo getTokenInfo(Token token);
	public String checkUserAccount(TokenInfo tokenInfo);
}
