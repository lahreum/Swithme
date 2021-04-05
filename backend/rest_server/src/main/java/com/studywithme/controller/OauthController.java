package com.studywithme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.oauth.Token;
import com.studywithme.oauth.TokenInfo;
import com.studywithme.service.OauthService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
	private final OauthService oauthService;
	
	@GetMapping("/{type}")
	@ApiOperation(value="인증 코드 요청", notes="소셜 로그인 Api에 인증 코드를 요청한다.")
	public void requestAuth(@PathVariable("type") String type) {
		oauthService.requestAuth(type);
	}
	
	@GetMapping("/{type}/callback")
	@ApiOperation(value="토큰 요청 및 사용자 계정 유무 확인", notes="소셜 로그인 Api에 토큰을 요청한다.\n반환된 토큰을 통해 사용자 계정이 DB에 존재하는지 확인하고 로그인 작업을 처리한다.")
	public void callback(@PathVariable("type") String type, String code, String state) {
		System.out.println(code);
		System.out.println(state);
		Token token = oauthService.requestToken(type, code);
		TokenInfo tokenInfo = oauthService.getTokenInfo(type, token);
		oauthService.checkUserAccount(type, tokenInfo);
	}
}
