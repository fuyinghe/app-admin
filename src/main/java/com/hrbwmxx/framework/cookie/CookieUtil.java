package com.hrbwmxx.framework.cookie;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 获取cookie
 * @description 
 * @author WhitePoplar
 * @date  2018年3月16日 下午2:58:04
 */
public class CookieUtil {
	
	
	
	public void setCookie(HttpServletResponse response,String userName , String token) {
		 Cookie cookie = new Cookie("userName", userName.trim());  
         cookie.setMaxAge(30* 60);// 设置为30min  
         cookie.setPath("/");  
         System.out.println("已添加userName===============");  
         response.addCookie(cookie);  
	}
	
	
	/**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
	 public static Cookie getCookieByName(HttpServletRequest request, String name) {
	        Map<String, Cookie> cookieMap = ReadCookieMap(request);
	        if (cookieMap.containsKey(name)) {
	            Cookie cookie = (Cookie) cookieMap.get(name);
	            return cookie;
	        } else {
	            return null;
	        }
	  }
	
	 /**
      * 将cookie封装到Map里面
      * 
      * @param request
      * @return
      */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
    
    /**
     * 获取token
     * @param request
     * @param name
     * @return
     */
	public static String getTokenByCookie(HttpServletRequest request, String name) {
    	String token = null;
    	Cookie cookie = getCookieByName(request , name);
    	if(cookie != null) {
    		if(name.equals(cookie.getName())) {
    			token = cookie.getValue();
    		}
    	}
    	return token;
    }
    
	 
}
