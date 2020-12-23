package com.hrbwmxx.framework.ldap.util;

import java.io.File;

import org.springframework.util.ResourceUtils;

import com.wiscom.ldapvalidate.ldapCheck;
public class LdapHelper {
	
	public static final String LDAPPATH = "/config/ldap.properties";
	
	public static boolean authUserPwd(String username, String password) throws Exception {
		return getLdap().checkPassword(username, password);
	}
	
	public static ldapCheck getLdap() throws Exception {
		File cfgFile = ResourceUtils.getFile("classpath:/config/ldap.properties");
		ldapCheck instance = ldapCheck.getInstance(cfgFile.getAbsolutePath());
		return instance;
	}

}
