package com.studywithme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.oauth.Token;
import com.studywithme.service.OauthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
	private final OauthService oauthService;
	
	@GetMapping("/{type}")
	public void requestAuth(@PathVariable("type") String type) {
		oauthService.requestAuth(type);
	}
	
	@GetMapping("/{type}/callback")
	public void requestToken(@PathVariable("type") String type, String code) {
		Token token = oauthService.requestToken(type, code);
		String tokenInfo = oauthService.getTokenInfo(type, token);
		System.out.println(tokenInfo);
	}
}
