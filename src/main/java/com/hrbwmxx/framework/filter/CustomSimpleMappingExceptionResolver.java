package com.hrbwmxx.framework.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.hrbwmxx.framework.config.ApplicationConstants;
import com.hrbwmxx.framework.cookie.CookieErrorException;
import com.hrbwmxx.framework.util.Constant;



public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	   @Override  
	    protected ModelAndView doResolveException(HttpServletRequest request,  
	            HttpServletResponse response, Object handler, Exception ex) {  
		   
	        // Expose ModelAndView for chosen error view.  
	        String viewName = determineViewName(ex, request); 
	        /*
	        if (viewName != null) {// JSP格式返回  
	            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
	                    .getHeader("X-Requested-With")!= null && request  
	                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
	                // 如果不是异步请求  
	                // Apply HTTP status code for error views, if specified.  
	                // Only apply it if we're processing a top-level request.  
	                Integer statusCode = determineStatusCode(request, viewName);  
	                if (statusCode != null) {  
	                    applyStatusCodeIfPossible(request, response, statusCode);  
	                }  
	                return getModelAndView(viewName, ex, request);  
	            } else {// JSON格式返回  
	        */
	        
	        Integer statusCode = determineStatusCode(request, viewName);
	        if(ex instanceof MyBatisSystemException) {
	        	statusCode=Integer.valueOf(Constant.ERRCODE_310);
	        }
	        if(ex instanceof NullPointerException) {
	        	statusCode=Integer.valueOf(Constant.ERRCODE_310);
	        }
	        if(ex instanceof CookieErrorException) {
	        	statusCode=403;
	        }
	        String key = "errorMessage.default";
	        
	        ModelAndView mv = new ModelAndView();
			// 设置状态码,注意这里不能设置成500，设成500JQuery不会出错误提示            //并且不会有任何反应
			response.setStatus(HttpStatus.OK.value());
			// 设置ContentType
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			// 避免乱码
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			try {
				ex.printStackTrace();
				if(statusCode != null){
            		key = "errorMessage."+statusCode;
            	} 
//				String backMessage = ApplicationConstants.get(key);
				String backMessage = ex.getMessage();
				
				PrintWriter writer = response.getWriter();
				writer.write("{\"errmsg\":\"" + backMessage + "\",\"errcode\":\""+statusCode+"\"}");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
	   }
	   
	   
	   
	       /* 
	          } else {
	                try {
	                	String message = "errorMessage.500";
	                	if(viewName != null){
	                		message = ApplicationConstants.get("errorMessage."+viewName);
	                	}
	                	response.setContentType("text/html;charset=utf-8");
	                    PrintWriter writer = response.getWriter();  
	                    writer.write(message);  
	                    writer.flush();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }
	                return null;  
	  
	            }
	        } else {
	            return null;  
	        }  
	    }  
	    */
}
