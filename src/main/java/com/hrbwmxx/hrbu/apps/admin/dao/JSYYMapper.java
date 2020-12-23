package com.hrbwmxx.hrbu.apps.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.hrbu.apps.admin.model.JSYY;
import com.hrbwmxx.hrbu.apps.admin.vo.JSYYVo;

public interface JSYYMapper {

	/**
	 * 
	 * @return
	 */
	List<JSYYVo> queryAllJSYY();

	/**
	 * 
	 * @param jsyy
	 */
	void insert(JSYY jsyy);

	/**
	 * delete
	 * 
	 * @param appId
	 * @param roleId
	 */
	void delete(@Param("appId") String appId, @Param("roleId") String roleId);

	/**
	 * 
	 * @param appId
	 * @param roleId
	 * @return
	 */
	JSYY findByCondition(@Param("appId") String appId,
			@Param("roleId") String roleId);

	/**
	 * 
	 * @param appId
	 */
	void updateYYState1(@Param("appId") String appId);

	/**
	 * 
	 * @param appId
	 */
	void updateYYState0(@Param("appId") String appId);

}
