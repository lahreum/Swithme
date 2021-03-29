package com.studywithme;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.studywithme.config.JwtInterceptor;
import com.studywithme.config.SSHConnection;

@SpringBootApplication
public class StudywithmeApplication {
	private static SSHConnection sshConnection;

	public static void main(String[] args) {
		sshConnection = new SSHConnection();
		SpringApplication.run(StudywithmeApplication.class, args);
	}

}
