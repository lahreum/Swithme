package com.studywithme.config;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonMethods {
	
	@Autowired
	JwtService jwtService;
	
	public String getUserId(String jwt){
		Map<String,Object> map=(Map)jwtService.get(jwt).get("User");
		
		return (String)map.get("userId");
	}
	
	public String getUserNickname(String jwt){
		Map<String,Object> map=(Map)jwtService.get(jwt).get("User");
		
		return (String)map.get("userNickname");
	}
	
	public String getHashed(String input) {
		MessageDigest digest=null;
		String output=null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hash=null;
		try {
			hash = digest.digest(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		StringBuffer hexString=new StringBuffer();
		for(int i=0;i<hash.length;i++) {
			String hex=Integer.toHexString(0xff & hash[i]);
			if(hex.length()==1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
