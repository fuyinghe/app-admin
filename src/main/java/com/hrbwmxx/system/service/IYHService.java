package com.hrbwmxx.system.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.YHCustom;
 
public interface IYHService {
	/**
	 * 
	* @MethodName: queryYHListPage 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月13日 下午5:24:07
	* @param page
	* @param obj
	* @return Result
	 */
	Result queryYHListPage(Page page, YHCustom obj);
	/**
	 * 
	* @MethodName: queryYHByField 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月13日 下午6:10:18
	* @param obj
	* @return Result
	 */
	Result queryYHByField(YHCustom obj);
	/**
	 * 
	* @MethodName: saveYH 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月13日 下午6:21:39
	* @param obj
	* @return Result
	 */
	Result saveYH(YHCustom obj);
	/**
	 * 
	* @MethodName: updateYH 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月14日 下午8:39:31
	* @param obj
	* @return Result
	 */
	Result updateYH(YHCustom obj);
	
	Result deleteYH(YHCustom obj);
	
	Result findSelectedMenuByYhId(Page page, YHCustom yh);
	Result findUnSelectMenuByYhId(Page page, YHCustom yh);
	Result addUnSelectMenu(GNCustom gn, YHCustom yh);
	Result deleteSelectMenu(GNCustom gn, YHCustom yh);
	
	Result updatePassWord(String yhId,String oldPass,String newPass);
	Result updatePassword(HashMap<String, Object> resmap,HttpServletRequest request);
	Result resetPassword(HashMap<String, Object> resmap);
}
