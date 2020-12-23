package com.hrbwmxx.framework.security.user;

import java.math.BigDecimal;

public interface UserContext {

	
	BigDecimal getUserId(); // sys_user_id
	String getUsername();//登录名
	BigDecimal getJobNumber(); //工号
	String getName();//真实姓名
	String getPicture(); // 登录头像
	BigDecimal getDeptId(); // 部门Id
	BigDecimal getPersonId();//t_person表id
	String getDeptName();//部门名称
	String getUserIP();
}