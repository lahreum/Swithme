package com.studywithme.config;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;

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
	
	public int whatDay(String datetime) throws ParseException {
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=fm.parse(datetime);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public String[] getDays(String datetime) throws ParseException {
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=fm.parse(datetime);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		String[] dates=new String[7];
		int whatDay=cal.get(Calendar.DAY_OF_WEEK);
		
		cal.add(Calendar.DATE, 1-whatDay);

		for(int i=0;i<7;i++) {
			int year=cal.get(Calendar.YEAR)%100;
			int month=cal.get(Calendar.MONTH)+1;
			int day=cal.get(Calendar.DATE);
			
			dates[i]=Integer.toString(year);
			if(month<10)
				dates[i]=dates[i]+"0";
			dates[i]=dates[i]+Integer.toString(month);
			if(day<10)
				dates[i]=dates[i]+"0";
			dates[i]=dates[i]+Integer.toString(day);
			cal.add(Calendar.DATE, 1);
		}
		return dates;
	}
	
	public byte[] resize(byte[] originImg) throws IOException {
		BufferedImage origin=ImageIO.read(new ByteArrayInputStream(originImg));
		
		int imageWidth=origin.getWidth();
		int imageHeight=origin.getHeight();
		
		double ratio=Math.sqrt((double)90000/(double)(imageHeight*imageWidth));
		
		if(ratio>1)
			return originImg;
		
		int newWidth=(int)(imageWidth*ratio);
		int newHeight=(int)(imageHeight*ratio);
		
		Image resizeImage=origin.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		
		BufferedImage newImage=new BufferedImage(newWidth,newHeight,origin.getType());
		Graphics g=newImage.getGraphics();
		g.drawImage(resizeImage,0,0,null);
		g.dispose();
		
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		ImageIO.write(newImage,"png",output);
	
		return output.toByteArray();
	}
}
