package com.hrbwmxx.framework.apidoc.service;

import java.math.BigDecimal;

import com.hrbwmxx.framework.apidoc.model.Apidoc;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;

public interface IApidocService {
	/**
	 * 
	* @MethodName: queryApidocForPage 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:55:31
	* @param page
	* @param apidoc
	* @return Result
	 */
	Result queryApidocForPage(Page page, Apidoc apidoc);
	/**
	 * 
	* @MethodName: queryApidocById 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:55:34
	* @param id
	* @return Result
	 */
	Result queryApidocById(BigDecimal id);
	/**
	 * 
	* @MethodName: insertApidocData 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:55:42
	* @param apidoc
	* @return Result
	 */
	Result insertApidocData(Apidoc apidoc);
	/**
	 * 
	* @MethodName: updateApidocData 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:55:47
	* @param apidoc
	* @return Result
	 */
	Result updateApidocData(Apidoc apidoc);
	/**
	 * 
	* @MethodName: deleteApidocDataById 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:55:56
	* @param id
	* @return Result
	 */
	Result deleteApidocDataById(BigDecimal id);
	/**
	 * 
	* @MethodName: queryApidocList 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:56:02
	* @return Result
	 */
	Result queryApidocList();
	/**
	 * 
	* @MethodName: queryApidocByPid 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:56:05
	* @param pid
	* @return Result
	 */
	Result queryApidocByPid(BigDecimal pid);
}
