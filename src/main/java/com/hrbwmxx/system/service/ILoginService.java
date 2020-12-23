package com.hrbwmxx.system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.model.Login;

public interface ILoginService {
	
	/**
	 * 登录校验
	 * @param request
	 * @param login
	 * @return
	 */
	public Result login(HttpServletRequest request,Login login);
	/**
	 * 微信登录
	 * @param userName
	 * @return
	 */
	public boolean loginWx(String openId);
	
	public Result queryUserId(Login login);
	
	public Result loginCheck(HttpServletRequest request,HttpServletResponse response);
	
	public String logout(HttpServletRequest request,HttpServletResponse response);
}
