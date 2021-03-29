package com.studywithme.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GoogleOauth implements SocialOauth {
	private final String clientId = "454862811715-f1k2ft42rkh2r02idn4q3mqdd8jmi6j9.apps.googleusercontent.com";
	private final String clientSecret = "EQxkCbPjG_8TM4guXxgIsX6G";
	private final String authUri = "https://accounts.google.com/o/oauth2/v2/auth";
	private final String redirectUri = "http://localhost:9999/oauth/google/callback";
	private final String tokenUri = "https://oauth2.googleapis.com/token";
	private final String tokenInfoUri = "https://oauth2.googleapis.com/tokeninfo";
	
	@Override
	public String getOauthRedirectUri() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(authUri)
				.queryParam("client_id", clientId)
				.queryParam("redirect_uri", redirectUri)
				.queryParam("response_type", "code")
				.queryParam("scope", "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile")
				.queryParam("access_type", "offline");
		
		return uriBuilder.toUriString();
	}

	@Override
	public Token requestToken(String code) {
		Map<String, String> params = new HashMap<>();
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		params.put("redirect_uri", redirectUri);
		
		RestTemplate restTemplate = new RestTemplate();
		Token response = restTemplate.postForObject(tokenUri, params, Token.class);
		
		return response;
	}

	@Override
	public String getTokenInfo(String type, Token token) {
		Map<String, String> params = new HashMap<>();
		params.put("id_token", token.getIdToken());
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(tokenInfoUri, params, String.class);
				
		return response;
	}
}
