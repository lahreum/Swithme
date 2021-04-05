package com.studywithme.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.studywithme.config.JwtService;
import com.studywithme.entity.UserInfo;
import com.studywithme.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	private final String clientId = "454862811715-f1k2ft42rkh2r02idn4q3mqdd8jmi6j9.apps.googleusercontent.com";
	private final String clientSecret = "EQxkCbPjG_8TM4guXxgIsX6G";
	private final String authUri = "https://accounts.google.com/o/oauth2/v2/auth";
//	private final String redirectUri = "https://j4b103.p.ssafy.io/service/oauth/google/callback";
	private final String redirectUri = "http://localhost:9999/oauth/google/callback";
	private final String tokenUri = "https://oauth2.googleapis.com/token";
	private final String tokenInfoUri = "https://oauth2.googleapis.com/tokeninfo";
	
	@Override
	public String requestAuth() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(authUri)
				.queryParam("client_id", clientId)
				.queryParam("redirect_uri", redirectUri)
				.queryParam("response_type", "code")
				.queryParam("scope", "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile")
				.queryParam("access_type", "offline")
				.queryParam("prompt", "select_account");
		
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
		Token token = restTemplate.postForObject(tokenUri, params, Token.class);
		
		return token;
	}

	@Override
	public TokenInfo getTokenInfo(Token token) {
		Map<String, String> params = new HashMap<>();
		params.put("id_token", token.getIdToken());
		
		RestTemplate restTemplate = new RestTemplate();
		TokenInfo tokenInfo = restTemplate.postForObject(tokenInfoUri, params, TokenInfo.class);
				
		return tokenInfo;
	}

	@Override
	public String checkUserAccount(TokenInfo tokenInfo) {
		Optional<UserInfo> optUserAccount = userRepository.findById(tokenInfo.getEmail());
		String jwtToken, redirectUri;
		
		if(optUserAccount.isPresent()) {	// 계정이 이미 존재할 경우
			if(optUserAccount.get().getUserType().equals("google")) {	// Google로 가입한 계정일 경우
				jwtToken = jwtService.create(optUserAccount.get());
//				redirectUri = "https://j4b103.p.ssafy.io/token?is-user=true&jwt-auth-token=" + jwtToken;
				redirectUri = "http://localhost:8080/token?is-user=true&jwt-auth-token=" + jwtToken;
				return redirectUri;
			}
//			else return "https://j4b103.p.ssafy.io/no-access/invalid_account";	// Google로 가입한 계정이 아닐 경우
			else return "http://localhost:8080/no-access/invalid_account";	// Google로 가입한 계정이 아닐 경우
		}
		
		// 계정이 존재하지 않을 경우
		UserInfo user = new UserInfo();
		user.setUserId(tokenInfo.getEmail());
		user.setUserType("google");
		
		jwtToken = jwtService.create(user);
		redirectUri = "http://localhost:8080/token?is-user=false&jwt-auth-token=" + jwtToken;
//		redirectUri = "https://j4b103.p.ssafy.io/token?is-user=false&jwt-auth-token=" + jwtToken;
		
		return redirectUri;
	}
}
