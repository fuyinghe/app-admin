package com.hrbwmxx.framework.security.voter;


import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.Jsr250SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SecuredVoter implements AccessDecisionVoter {
	
	
	public boolean supports(ConfigAttribute configAttribute) {
		return configAttribute instanceof SecurityConfig;
	}

	
	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	
	public int vote(Authentication authentication, Object arg1, Collection arg2) {
		boolean jsr250AttributeFound = false;
        for (Object o : arg2) {
        	ConfigAttribute attribute=(ConfigAttribute)o;
            if (supports(attribute)) {
                jsr250AttributeFound = true;
                // Attempt to find a matching granted authority
            	if (isAccessGranted(attribute.getAttribute(), authentication)) {
    				return ACCESS_GRANTED;
            	}
            }
        }

        return jsr250AttributeFound ? ACCESS_DENIED : ACCESS_ABSTAIN;
	}

    protected boolean isAccessGranted(String attribute, Authentication authentication) {
    	if (attribute != null && !attribute.isEmpty()) {
    		// F1000||F1010  slipt || 
    		String [] functionIds = attribute.split("\\|\\|");
    		for (String functionId : functionIds){
    			for (GrantedAuthority authority : authentication.getAuthorities()) {
	    			if (functionId != null && functionId.equals(authority.getAuthority())) {
	    				return true;
	    			}
    			}
    		}
    	}
    	return false;
    }
 
    public static void main(String[] args ) {
    	String[] aa = "F1000||F1010".split("\\|\\|");
    	for (int i = 0 ; i<aa.length ; i++ ) { 
    		System.out.println(aa[i]); 
    	} 
    }
}

