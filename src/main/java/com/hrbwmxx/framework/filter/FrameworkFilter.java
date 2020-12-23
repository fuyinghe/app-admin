package com.hrbwmxx.framework.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hrbwmxx.system.model.Login;


/**
* @title: FrameworkFilter 
* @description：框架过滤器 目前过滤是否登录
* @date： 2017年8月3日 下午4:16:41
*/
public class FrameworkFilter implements Filter{
    
    private static ServletContext servletContext;
    
    public static ServletContext getServletContext() {
        return servletContext;
    }
    
    /**
     * 初始化Filter，导出模块资源
     * init
     */
    public void init(FilterConfig config) throws ServletException {
    	getServletContext(config);
    }
    
    public static void getServletContext(FilterConfig config) {
    	servletContext = config.getServletContext();
    }
    
    /**
     * 设置应用信息
     * doFilter
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException,ServletException {
    	HttpServletRequest request = (HttpServletRequest)req;
    	HttpServletResponse response = (HttpServletResponse)resp;
    	//校验用户是否登陆
    	if(checkSession(request)) {
    		filterChain.doFilter(req, response);
		}else {
			//第一种方式：通过jsRoute路由提醒用户未登录
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.write("{\"errcode\":\"403\"}");
			pw.close();
		}
    }
	
	public void destroy() {
		
	}
	/**
	 * 校验用户是否登录
	 * @param request
	 * @return
	 */
	private boolean checkSession(HttpServletRequest request) {
		boolean boo=true;
		//获取url地址
        String url = request.getServletPath();
        //排除不需要过滤的url
        if( url.startsWith("/Login") || url.startsWith("/wx_") || url.startsWith("/Attachment") || url.startsWith("/tstz") ){
            
        	return boo;
        }
        
		Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
		if(sessionLogin==null) {
			boo=false;
		}
		//返回判断结果
		return boo;
	}
}
