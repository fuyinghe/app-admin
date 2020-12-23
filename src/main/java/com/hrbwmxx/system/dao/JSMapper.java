package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.model.JS;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.JSCustom;
import com.hrbwmxx.system.vo.YHCustom;
/**
 * 
* @title: JSMapper 
* @description：数据接口层
* @author： lijingyu
* @date： 2018年1月12日 上午10:55:09
 */
public interface JSMapper {
	/**
	 * 
	* @MethodName: queryJSListPage 
	* @description :角色列表（分页列）
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:21:25
	* @param page
	* @param obj
	* @return List<JSCustom>
	 */
	List<JSCustom> queryJSListPage(@Param("page")  Page page, @Param("obj")JSCustom obj);
	/**
	 * 
	* @MethodName: queryJSListPageCount 
	* @description :角色列表（分页统计）
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:21:25
	* @param page
	* @param obj
	* @return List<JSCustom>
	 */
	int queryJSListPageCount(@Param("page")Page page, @Param("obj")JSCustom obj);
	/**
	 * 
	* @MethodName: queryJSList 
	* @description :角色列表（不含分页）
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:21:25
	* @param page
	* @param obj
	* @return List<JSCustom>
	 */
	List<JSCustom>  queryJSList(@Param("obj")JSCustom obj);
	/**
	 * 
	* @MethodName: saveJS 
	* @description : 保存角色
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:22:54
	* @param obj void
	 */
	void saveJS(JSCustom obj);

	/**
	 * 
	* @MethodName: updateJS 
	* @description : 修改角色
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:22:57
	* @param obj void
	 */
	void updateJS(JSCustom obj);
	//通过用户Id 查出该用户已选的角色
	List<JSCustom> findJsListForPage(@Param("page")Page page, @Param("yh")YHCustom yh);
	//通过用户Id 查出该用户的角色总记录数
	int findJsListForCount(@Param("page")Page page, @Param("yh")YHCustom yh);
	//通过用户Id 查出该用户未选的角色
	List<JSCustom> findUnselectRoleListByyhIdForPage(@Param("page")Page page, @Param("yh")YHCustom yh);
	//通过用户Id 查出该用户未选的角色 总记录数
	int findUnselectRoleListByyhIdForCount(@Param("page")Page page, @Param("yh")YHCustom yh);
	// 给用户添加未选角色
	int addUnSelectRole(@Param("yh")YHCustom yh, @Param("js")JSCustom js);
	//删除用户已选角色
	int deleteSelectedRole(@Param("yh")YHCustom yh, @Param("js")JSCustom js);
	//根据角色id 查出拥有该角色的用户集合
	List<YHCustom> findSelectdeYhByJsIdForPage(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色id 查出拥有该角色的用户 总条数
	int findSelectdeYhByJsIdForCount(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色id 查出未拥有该角色的用户
	List<YHCustom> findUnseleteYhByJsIdForPage(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色id 查出未拥有该角色的用户 总条数
	int findUnseleteYhByJsIdForCount(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色ID 查出已选菜单
	List<GNCustom> findSelectGNByJsIdForPage(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色ID 查出已选菜单 总条数
	int findSelectGNByJsIdForCount(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色ID 查出未选菜单
	List<GNCustom> findUnSelectGNByJsIdForPage(@Param("page")Page page, @Param("js")JSCustom js);
	//根据角色ID 查出未选菜单 总条数
	int findUnSelectGNByJsIdForCount(@Param("page")Page page, @Param("js")JSCustom js);
	//添加未选菜单
	int addUnSelectMenu(@Param("gn")GNCustom gn, @Param("js")JSCustom js);
	//删除已选功能
	int deleteSelectMenu(@Param("gn")GNCustom gn, @Param("js")JSCustom js);
	//通过政工主键， 查出角色信息
	//List<JS> findJSListByZGRYKey(@Param("zgry") ZGRYCustom zgry);
	//通过用户主键， 查出角色信息
	List<JS> findJSListByYhid(@Param("obj") YHCustom yh);
}
