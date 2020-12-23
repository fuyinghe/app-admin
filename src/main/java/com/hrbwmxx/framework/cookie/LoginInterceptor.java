package com.hrbwmxx.framework.cookie;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.hrbwmxx.system.model.Login;

public class LoginInterceptor  {
	
	@Autowired
	private HttpServletRequest request;
	//@Autowired
	//private YHMapper yhMapper;
	
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	 
	public void trackInfo() {
		try {
			boolean flag = checkSession();
			if(!flag) {
				throw new CookieErrorException("403");
			}
		} catch (Exception e) {
			throw new CookieErrorException("403")  ;
		}
	}
	
	private boolean checkSession() {
		boolean boo=true;
		Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
		if(sessionLogin==null) {
			boo=false;
		}
		//返回判断结果
		return boo;
		 
	}
	
	
	
}
