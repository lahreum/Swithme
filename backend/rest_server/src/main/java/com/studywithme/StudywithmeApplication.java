package com.studywithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.studywithme.config.SSHConnection;

@SpringBootApplication
public class StudywithmeApplication {
	private static SSHConnection sshConnection;
	
	public static void main(String[] args) {
		sshConnection = new SSHConnection();
		SpringApplication.run(StudywithmeApplication.class, args);
	}

}
