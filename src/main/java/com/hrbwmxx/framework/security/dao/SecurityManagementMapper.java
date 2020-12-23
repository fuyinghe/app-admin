package com.hrbwmxx.framework.security.dao;

import com.hrbwmxx.framework.security.user.UserEntity;

public interface SecurityManagementMapper {

	UserEntity findUserEntity(String username);
	
	UserEntity findUserEntityByQuickLogin(String username);
	/**
	 * SELECT 
			sys_user.id,
			sys_user.username,
			sys_user.password,
			sys_user.picture,
			t_person.jobNumber,
			t_person.name,
			t_person.deptId,
			t_person.id personId,
			sys_dept.deptName
		 FROM 
		 	sys_user
		LEFT JOIN 
			t_person
		ON
			sys_user.jobNumber =  t_person.jobNumber
		LEFT JOIN
			sys_dept
		ON
			t_person.deptId = sys_dept.id
		 WHERE
		    sys_user.username = #{username}
		    and sys_user.recActiveFlag = 'Y'




SELECT 
			sys_user.id,
			sys_user.username,
			sys_user.password,
			sys_user.picture,
			t_person.jobNumber,
			t_person.name,
			t_person.deptId,
			t_person.id personId,
			sys_dept.deptName
		 FROM 
		 	sys_user
		LEFT JOIN 
			t_person
		ON
			sys_user.jobNumber =  t_person.jobNumber
		LEFT JOIN
			sys_dept
		ON
			t_person.deptId = sys_dept.id
		 WHERE
		    sys_user.username = #{username}
		    and sys_user.recActiveFlag = 'Y'

	 */
}
