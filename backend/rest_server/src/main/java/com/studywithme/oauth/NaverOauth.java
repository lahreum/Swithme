package com.studywithme.oauth;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.studywithme.config.JwtService;
import com.studywithme.entity.UserInfo;
import com.studywithme.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NaverOauth implements SocialOauth {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	private final String clientId = "Lo8etmLBsy1kZMSWYz0x";
	private final String clientSecret = "lszt2PW8oK";
	private final String authUri = "https://nid.naver.com/oauth2.0/authorize";
//	private final String redirectUri = "https://j4b103.p.ssafy.io/service/oauth/naver/callback";
	private final String redirectUri = "http://localhost:9999/oauth/naver/callback";
	private final String tokenUri = "https://nid.naver.com/oauth2.0/token";
	private final String tokenInfoUri = "https://openapi.naver.com/v1/nid/me";
	
	@Override
	public String requestAuth() {
		SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    
	    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(authUri)
				.queryParam("client_id", clientId)
				.queryParam("redirect_uri", redirectUri)
				.queryParam("response_type", "code")
				.queryParam("state", state)
			    .queryParam("auth_type", "reauthenticate");
		
		return uriBuilder.toUriString();
	}

	@Override
	public Token requestToken(String code, String state) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(tokenUri)
				.queryParam("client_id", clientId)
				.queryParam("client_secret", clientSecret)
				.queryParam("code", code)
				.queryParam("grant_type", "authorization_code")
				.queryParam("state", state);
		
		RestTemplate restTemplate = new RestTemplate();
		Token token = restTemplate.getForObject(uriBuilder.toUriString(), Token.class);
		
		return token;
	}

	@Override
	public TokenInfo getTokenInfo(Token token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token.getAccessToken());
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TokenInfo> tokenInfo = restTemplate.exchange(tokenInfoUri, HttpMethod.GET, new HttpEntity<>(headers), TokenInfo.class);
		
		return tokenInfo.getBody();
	}

	@Override
	public String checkUserAccount(String type, TokenInfo tokenInfo) {
		Optional<UserInfo> optUserAccount = userRepository.findById(tokenInfo.getNaver().getNaverEmail());
		String jwtToken, redirectUri;
		
		if(optUserAccount.isPresent()) {	// 계정이 이미 존재할 경우
			String userType = optUserAccount.get().getUserType();
			
//			if(userType==null) return "https://j4b103.p.ssafy.io/no-access/invalid_account";	// 사이트 계정이 있을 경우
			if(userType==null) return "http://localhost:8080/no-access/invalid_account";	// 사이트 계정이 있을 경우
			else if(userType.equals(type)) {	// Naver로 가입한 계정일 경우
				jwtToken = jwtService.create(optUserAccount.get());
//				redirectUri = "https://j4b103.p.ssafy.io/token?is-user=true&jwt-auth-token=" + jwtToken;
				redirectUri = "http://localhost:8080/token?is-user=true&jwt-auth-token=" + jwtToken;
				return redirectUri;
			}
//			else return "https://j4b103.p.ssafy.io/no-access/invalid_account";	// 다른 소셜 로그인 계정이 있을 경우
			else return "http://localhost:8080/no-access/invalid_account";	// 다른 소셜 로그인 계정이 있을 경우
		}
		
		// 계정이 존재하지 않을 경우
		UserInfo user = new UserInfo();
		user.setUserId(tokenInfo.getNaver().getNaverEmail());
		user.setUserType(type);
		
		jwtToken = jwtService.create(user);
		redirectUri = "http://localhost:8080/token?is-user=false&jwt-auth-token=" + jwtToken;
//		redirectUri = "https://j4b103.p.ssafy.io/token?is-user=false&jwt-auth-token=" + jwtToken;
		
		return redirectUri;
	}
}
