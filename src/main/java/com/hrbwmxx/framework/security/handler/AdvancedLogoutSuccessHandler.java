package com.hrbwmxx.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.hrbwmxx.framework.security.SecurityContext;

public class AdvancedLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	@Autowired
	private ServletContext applicationScope;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		applicationScope.removeAttribute("myusername");
		super.handle(request, response, authentication);
	}
}
