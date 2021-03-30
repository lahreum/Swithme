package com.studywithme.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studywithme.oauth.Token;
import com.studywithme.oauth.TokenInfo;
import com.studywithme.service.OauthService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
	private final OauthService oauthService;
	
	@GetMapping("/{type}")
	@ApiOperation(value="인증 코드 요청", notes="Google에 인증 코드를 요청한다.")
	public void requestAuth(@PathVariable("type") String type, HttpServletResponse response) {
		String redirectUri = oauthService.requestAuth(type);
		
		try {
			response.sendRedirect(redirectUri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/{type}/callback")
	@ApiOperation(value="토큰 요청 및 사용자 계정 유무 확인", notes="Google에 토큰을 요청한다.\n반환된 토큰을 통해 사용자 계정이 DB에 존재하는지 확인하고 로그인 작업을 처리한다.")
	public String callback(@PathVariable("type") String type, String code, HttpServletRequest request) {
		Token token = oauthService.requestToken(type, code);
		TokenInfo tokenInfo = oauthService.getTokenInfo(type, token);
		String jwtToken = oauthService.checkUserAccount(type, tokenInfo);
		
		if(jwtToken==null) return "setNickname";
		
		request.setAttribute("token", jwtToken);
		
		return "socialLoginSuccess";
	}
}
