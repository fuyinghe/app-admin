package com.hrbwmxx.framework.security.providers;

import com.hrbwmxx.framework.security.user.UserEntity;



public interface SecurityContextProvider {
	
	UserEntity getUserPersonEntity(String username);
	
	UserEntity getUserEntity(String username);
	
	UserEntity getUserEntityByQuickLogin(String username);
	
}
