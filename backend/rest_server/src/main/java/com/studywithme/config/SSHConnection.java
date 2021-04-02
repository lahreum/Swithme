package com.studywithme.config;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnection {
	private final static String HOST = "j4b103.p.ssafy.io";
	private final static Integer PORT = 22;
	private final static String SSH_USER = "ubuntu";
	private final static String S_PATH_FILE_PRIVATE_KEY = "/home/ubuntu/PrivateKey/J4B103T.ppk";
	
	private Session session;
	
	public void closeSSH() {
		session.disconnect();
	}
	
	public SSHConnection() {
		try {
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);
			session = jsch.getSession(SSH_USER, HOST, PORT);
			session.setConfig(config);
			session.connect();
			session.setPortForwardingL(3333, "127.0.0.1", 3306);
		} catch (JSchException e) {
			e.printStackTrace();
		}		
	}

}
