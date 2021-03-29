package com.studywithme.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
	JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handler) {
		String token=req.getHeader("jwt-auth-token");
		if(token!=null&&token.length()>0) {
			jwtService.checkValid(token);
			return true;
		}
		else {
			throw new RuntimeException("유효하지 않은 토큰이거나 인증 토큰이 없습니다.");
		}
	}
}
