package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.system.model.JSGN;
import com.hrbwmxx.system.vo.JSGNCustom;
/**
 * 
* @title: JSGNMapper 
* @description：角色功能dao接口
* @author： lijingyu
* @date： 2018年1月18日 上午9:28:34
 */
public interface JSGNMapper {
	/**
	 * 
	* @MethodName: saveBatchJSGNList 
	* @description : 批量插入角色功能（角色菜单的绑定）
	* @author：lijingyu
	* @date： 2018年1月18日 上午9:29:07
	* @param list void
	 */
	void saveBatchJSGNList(@Param("list") List<JSGN> list);
	/**
	 * 
	* @MethodName: deleteJSGN 
	* @description : 删除角色菜单中间表（解除绑定）
	* @author：lijingyu
	* @date： 2018年1月18日 上午9:29:11
	* @param obj void
	 */
	void deleteJSGN(@Param("obj") JSGN obj);
	/**
	 * 
	* @MethodName: queryJSGNList 
	* @description : 角色功能列表
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:48:23
	* @param obj
	* @return List<JSGNCustom>
	 */
	List<JSGNCustom> queryJSGNList(@Param("obj") JSGN obj);
}
