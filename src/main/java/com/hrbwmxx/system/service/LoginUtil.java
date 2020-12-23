package com.hrbwmxx.system.service;

import javax.servlet.http.HttpSession;

import com.hrbwmxx.system.model.Login;

public class LoginUtil {
	
	/**
	 * 返回登录用户信息
	 * @param session
	 * @return Login
	 */
	public static Login getUser(HttpSession session) {
		Login user = (Login) session.getAttribute("LoginUserSession");
		if(user!=null) {
			user.getUserId();
			user.getUserName();
			user.getUserNick();
			user.getUserPassword();
			return user;
		}else{
			return null;
		}
	}
	
	/**
	 * 返回登录用户名
	 * @param session
	 * @return UserNick
	 */
	public static String getUserNick(HttpSession session) {
		Login user = (Login) session.getAttribute("LoginUserSession");
		if(user!=null) {
			return user.getUserNick();
		}else{
			return "";
		}
	}
	
	/**
	 * 返回登录用户名ID
	 * @param session
	 * @return UserId
	 */
	public static String getUserId(HttpSession session) {
		Login user = (Login) session.getAttribute("LoginUserSession");
		if(user!=null) {
			return user.getUserId();
		}else{
			return "";
		}
	}
	
	/**
	 * 返回登录用户Token
	 * @param session
	 * @return AccessToken
	 */
	public static String getAccessToken(HttpSession session) {
		Login user = (Login) session.getAttribute("LoginUserSession");
		if(user!=null) {
			return user.getAccessToken();
		}else{
			return "";
		}
	}
	
	

}
