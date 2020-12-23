package com.hrbwmxx.framework.security.handler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.hrbwmxx.framework.security.SecurityContext;



public class AdvancedLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private ServletContext applicationScope;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("myuserid", SecurityContext.getUserContext().getUserId().toString());
		applicationScope.setAttribute(SecurityContext.getUserContext().getUsername().toString(), SecurityContext.getUserContext().getUsername().toString());
		if(request.getHeader("referer")!=null){
			super.onAuthenticationSuccess(request, response, authentication);
		}else{                                       
			OutputStream servletOutPutStream=response.getOutputStream();
			servletOutPutStream.write("Success".getBytes());
			servletOutPutStream.flush();
			servletOutPutStream.close();
		}
	}

}
