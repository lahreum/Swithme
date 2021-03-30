package com.studywithme.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer{

	@Autowired
	JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
			.excludePathPatterns(Arrays.asList("/user/signup","/user/login","/user/id",
					"/user/nickname","/user/password",
					"/swagger-ui.html","/swagger-resources/**","/v2/api-docs","/webjars/**"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.allowedHeaders("*")
			.exposedHeaders("jwt-auth-token");
	}
	
}
