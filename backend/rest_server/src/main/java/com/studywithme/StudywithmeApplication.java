package com.studywithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.studywithme.config.SSHConnection;

@SpringBootApplication
public class StudywithmeApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudywithmeApplication.class, args);
	}

}
