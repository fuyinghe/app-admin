package com.hrbwmxx.system.model;

import java.io.Serializable;

public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String userName;
	
	private String userNick;
	
	private String userPassword;
	
	private String signature;
	
	private String accessToken;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", userName=" + userName + ", userNick=" + userNick + ", userPassword="
				+ userPassword + ", signature=" + signature + ", accessToken=" + accessToken + "]";
	}
	
	
	
}
