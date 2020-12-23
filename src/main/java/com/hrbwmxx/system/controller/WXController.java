package com.hrbwmxx.system.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrbwmxx.system.dao.LoginMapper;
import com.hrbwmxx.system.service.ILoginService;
import com.hrbwmxx.system.service.LoginUtil;

@Controller
@RequestMapping("wx_AuthServer")
public class WXController {
	
	@Autowired
	public HttpSession session;
	
	@Qualifier("LoginServiceImpl")
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("login")
	public void authServer(HttpServletRequest request,HttpServletResponse response,@Param(value = "success") String success) throws IOException {

		String code = request.getParameter("code");
		if(success==null) {
			response.setStatus(301);
			return;
		}else {
			String crop="wx3ea9c392c593c137";
			String secrit="bNFekPgj9CSenX0zVG6p9lxjW0A2M1r6OroTWmQLuWo";
			String userId = LoginUtil.getUserId(session);
			
			if(userId==null || "".equals(userId)) {
				//用户未登录,跳转微信认证
				if(code==null) {
					//首次访问
					String domain = "http://www.hrbwmxx.com/cpsp/wx_AuthServer/login.do?success=";
					String redirecturl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+crop+"&redirect_uri="+domain+success+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
					response.sendRedirect(redirecturl);
					return;
				}else {
					//已经从微信回来,开始获取code，然后使用code去获取openId
//					String openId = com.hrbwmxx.framework.weixin.help.AccessTokenHelper.getOpenIdorUserId(crop, secrit, code);
//					System.out.println(openId);
					//String openId ="1234";
					//单点登录
//					boolean iscg= loginService.loginWx(openId);
//					if(iscg) {
//						response.sendRedirect(success);
//						return;
//					}else {
//						response.setStatus(301);
//						return;
//					}
					
				}
				
			}else {
				response.sendRedirect(success);
				return;
			}
		}
		
		
		
		
	}

}
