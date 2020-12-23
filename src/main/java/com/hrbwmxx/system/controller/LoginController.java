package com.hrbwmxx.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.model.Login;
import com.hrbwmxx.system.service.ILoginService;

/**
 * 登录验证
 * @author GRMa
 */
@Controller
@RequestMapping("Login")
public class LoginController {
	
	@Qualifier("LoginServiceImpl")
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("login")
	@ResponseBody
	public Result login(HttpServletRequest request,Login login) {
		return loginService.login(request,login);
	}
	
	@RequestMapping("queryUserId")
	@ResponseBody
	public Result queryUserId(Login login) {
		return loginService.queryUserId(login);
	}
	
	@RequestMapping("loginCheck")
	@ResponseBody
	public Result loginCheck(HttpServletRequest request,HttpServletResponse response) {
		return loginService.loginCheck(request,response);
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		return loginService.logout(request,response);
	}
	
	

}
