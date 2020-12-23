package com.hrbwmxx.framework.security.providers;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.util.Assert;

import com.hrbwmxx.framework.security.dao.SecurityManagementMapper;
import com.hrbwmxx.framework.security.user.UserEntity;


public class SecurityContextProviderImpl implements SecurityContextProvider, InitializingBean {

	
	private SecurityManagementMapper securityManagementMapper;
	private AccessDecisionManager accessDecisionManager; 
	
	
	public UserEntity getUserEntity(String username) {
		return securityManagementMapper.findUserEntity(username);
	}
	
	public UserEntity getUserPersonEntity(String username) {
		return securityManagementMapper.findUserEntity(username);
	}
	
	public UserEntity getUserEntityByQuickLogin(String username) {
		return securityManagementMapper.findUserEntityByQuickLogin(username);
	}
	
	public void setSecurityManagementMapper(
			SecurityManagementMapper securityManagementMapper) {
		this.securityManagementMapper = securityManagementMapper;
	}

	public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
		this.accessDecisionManager = accessDecisionManager;
	}
	
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(securityManagementMapper, "securityManagementMapper must be specified");
		Assert.notNull(accessDecisionManager, "accessDecisionManager must be specified");
	}
	

}
