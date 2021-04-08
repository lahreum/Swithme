package com.studywithme.config;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.studywithme.entity.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	private String salt="SWITHME";
	private Long expireTime=24*60*60*1000L;

	public String create(final UserInfo user) {
		final JwtBuilder builder=Jwts.builder();
		builder.setHeaderParam("typ", "JWT");

		user.setUserPassword(null);
		user.setUserProfileImg(null);
		
		builder.setSubject("swithmeToken")
			.setExpiration(new Date(System.currentTimeMillis()+expireTime))
			.claim("User",user);
		
		builder.signWith(SignatureAlgorithm.HS256,salt.getBytes());
		
		final String jwt=builder.compact();
		return jwt;
	}
	
	public void checkValid(final String jwt) {
		Jwts.parser().setSigningKey(salt.getBytes()).parse(jwt);
	}
	
	public Map<String,Object> get(final String jwt){
		Jws<Claims> claims=null;
		try {
			claims=Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		}catch(Exception e) {
			throw new RuntimeException();
		}
		
		return claims.getBody();
	}

}
