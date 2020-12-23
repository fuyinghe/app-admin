package com.hrbwmxx.framework.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hrbwmxx.framework.config.ApplicationConstants;


public class AppContextListener implements ServletContextListener {
	
	
	public void contextInitialized(ServletContextEvent servletcontextevent) {

		ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletcontextevent.getServletContext());
		
		ApplicationConstants.init(appContext);

	}
	
    
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
    	
	}

}
