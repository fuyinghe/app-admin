package com.hrbwmxx.framework.fj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.fj.model.FJCS;
import com.hrbwmxx.framework.model.Page;
/**
 * 
* @title: FJCSMapper 
* @description：附件参数列表
* @author： 李静雨
* @date： 2018年1月12日 下午5:30:49
 */
public interface FJCSMapper {
	/**
	 * 
	* @MethodName: queryFJCSByField 
	* @description : 查询参数详情
	* @author：lijingyu
	* @date： 2018年1月12日 下午4:52:56
	* @param obj
	* @return FJCS
	 */
	FJCS  queryFJCSByField( @Param("obj")FJCS obj);
	/**
	 * 
	* @MethodName: queryFjcsListPage 
	* @description : 文件列表查询（分页）
	* @author：lijingyu
	* @date： 2018年1月16日 上午9:39:42
	* @param page
	* @param obj
	* @return List<FJCS>
	 */
	List<FJCS> queryFJCSListPage(@Param("page") Page page, @Param("obj")FJCS obj);
	/**
	 * 
	* @MethodName: queryFjcsListPageCount 
	* @description : 文件列表查询（分页统计）
	* @author：lijingyu
	* @date： 2018年1月16日 上午9:39:46
	* @param page
	* @param obj
	* @return int
	 */
	int queryFJCSListPageCount(@Param("page") Page page, @Param("obj")FJCS obj);
	void saveFJCS( @Param("obj")FJCS obj);
	void updateFJCS( @Param("obj")FJCS obj);
}
