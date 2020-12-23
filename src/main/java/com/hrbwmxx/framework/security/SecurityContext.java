package com.hrbwmxx.framework.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hrbwmxx.framework.security.user.UserContext;


public class SecurityContext {
	
	public static UserContext getUserContext() {
		UserContext userContext = null;
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication(); 
		if (authentication != null) {
			userContext = (UserContext)authentication.getPrincipal();
		}
		return userContext;
	}
}
