package com.hrbwmxx.system.dao;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.system.model.Login;


public interface LoginMapper {
	
	//检测用户名和密码是否匹配 返回用户
	Login loginCheck(@Param("login") Login login);
	//单点登录
	Login loginCheck_sso(@Param("openIdorUserId") String openIdorUserId);
	
	//存储token到数据库
	int insertToken(@Param("userId") String userId ,@Param("accessToken") String accessToken ,@Param("createTime") String createTime );
	
	//获取用户token
	String queryUserToken(@Param("userId") String userId);
	
	//获取用户userId
	String queryUserId(@Param("accessToken") String accessToken);
	
	//移除token
	int removeToken(@Param("accessToken") String accessToken);
	
}
