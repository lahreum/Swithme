package com.studywithme.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class MainController {
	@PostMapping("/predict")
	public byte[] predict(MultipartFile data) {
		byte[] response = null;
		RestTemplate template = new RestTemplate();
		
		try {
			// byte 단위의 이미지 불러오기
			byte[] imageBytes = data.getBytes();
			
			// 인공지능 서버로 집중 여부 판단 요청
			response = template.postForObject("http://localhost:8000/predict", imageBytes, byte[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
