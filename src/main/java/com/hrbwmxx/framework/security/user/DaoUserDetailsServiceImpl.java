package com.hrbwmxx.framework.security.user;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.hrbwmxx.framework.security.providers.SecurityContextProvider;
import com.hrbwmxx.framework.security.user.UserEntity;
public class DaoUserDetailsServiceImpl implements UserDetailsService {
	private SecurityContextProvider securityContextProvider;
	
	@Autowired
	private ServletContext applicationScope;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	public DaoUserDetailsServiceImpl(SecurityContextProvider securityContextProvider) {
		this.securityContextProvider = securityContextProvider;
		Assert.notNull(securityContextProvider, "securityContextProvider must be specified.");
	}
	
	//用户验证函数
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		
		if (userName == null || userName.isEmpty()) throw new UsernameNotFoundException("");
		UserEntity userEntity;
		if (userName.startsWith("QuickLogin:")) {
			userName = userName.substring("QuickLogin:".length());
			userEntity = securityContextProvider.getUserEntityByQuickLogin(userName);
			
			try {
				userEntity.setUserIP(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			Enumeration<String> names = applicationScope.getAttributeNames();
			while(names.hasMoreElements()){  //有没有下一个元素
			    String s = names.nextElement();  //获取下一个元素
			    if(s.equals(userName)){
			    	List<SessionInformation> allSessions = sessionRegistry.getAllSessions(userName, false);
			        if (allSessions != null) {
			             for (int i = 0; i < allSessions.size(); i++) {
			                 SessionInformation sessionInformation = allSessions.get(i);
			                 sessionInformation.expireNow();
			                 sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
			             }
			             
			        }//踢掉上一个人
			    	//throw new UsernameNotFoundException(userName);
			    }
			    
			}
			userEntity  = securityContextProvider.getUserEntity(userName);
			try {
				userEntity.setUserIP(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (userEntity == null) throw new UsernameNotFoundException(userName);
		UserContextWrapper userContext = new UserContextWrapper(userEntity);
		return userContext; 
		
	}

	
	
}
