package com.hrbwmxx.framework.apidoc.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.apidoc.model.Apidoc;
import com.hrbwmxx.framework.model.Page;

public interface ApidocMapper {
	/**
	 * 
	* @MethodName: queryApidocForPage 
	* @description :分页查询
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:52:08
	* @param page
	* @param apidoc
	* @return List<Apidoc>
	 */
	List<Apidoc> queryApidocForPage(@Param("page")Page page, @Param("apidoc") Apidoc apidoc);
	/**
	 * 
	* @MethodName: queryApidocForCount 
	* @description : 返回总条数
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:52:21
	* @param page
	* @param apidoc
	* @return int
	 */
	int queryApidocForCount(@Param("page")Page page, @Param("apidoc") Apidoc apidoc);
	/**
	 * 
	* @MethodName: queryApidocById 
	* @description : 根据主键查询一条详细数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:53:20
	* @param id
	* @return Apidoc
	 */
	Apidoc queryApidocById(@Param("id")BigDecimal id);
	/**
	 * 
	* @MethodName: saveApidocData 
	* @description : 保存数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:53:32
	* @param apidoc
	* @return int
	 */
	int saveApidocData(Apidoc apidoc);
	/**
	 * 
	* @MethodName: updateApidocData 
	* @description : 修改数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:53:52
	* @param apidoc
	* @return int
	 */
	int updateApidocData(Apidoc apidoc);
	/**
	 * 
	* @MethodName: deleteApidocDataById 
	* @description : 根据主键删除数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:54:05
	* @param id
	* @return int
	 */
	int deleteApidocDataById(@Param("id")BigDecimal id);
	/**
	 * 
	* @MethodName: queryApidocList 
	* @description : 查询所有的api列表
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:54:25
	* @return List<Apidoc>
	 */
	List<Apidoc> queryApidocList();
	/**
	 * 
	* @MethodName: queryApidocByPid 
	* @description : 根据pid查询
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:54:40
	* @param pid
	* @return List<Apidoc>
	 */
	List<Apidoc> queryApidocByPid(@Param("pid")BigDecimal pid);
	/**
	 * 
	* @MethodName: queryNextId 
	* @description : 查询下条ID
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:54:52
	* @return BigDecimal
	 */
	BigDecimal queryNextId();
}
