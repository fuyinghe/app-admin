package com.hrbwmxx.system.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.service.IAppManageService;
import com.hrbwmxx.system.service.ICodeService;
import com.hrbwmxx.system.vo.AppManageCustom;
import com.hrbwmxx.system.vo.AppTypeCustom;
import com.hrbwmxx.system.vo.CodeCustom;
import com.hrbwmxx.system.vo.CodeVo;

@Controller
@RequestMapping("app")
public class AppManageController {
	
	@Autowired
	private IAppManageService manageService ;
	
	@Autowired
	private ICodeService codeService;
	
	/**
	 * 根据FHDM,SJDM,SJZ,查询状态为使用的CODE信息
	 * @Title: queryCodeValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryCodeValue")
	@ResponseBody
	public Result queryCodeValue(CodeCustom obj) {
		return codeService.queryCodeValue(obj);
	}

	/**
	 * 查询城市代码
	 * @Title: queryCityCodeValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param page
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryCityCodeValue")
	@ResponseBody
	public Result queryCityCodeValue(Page page, CodeCustom obj) {
		return codeService.queryCityCodeValue(page, obj);
	}
	
	/**
	 * 查询城市代码下拉列表
	 * @Title: selectCityCodeVaule   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: LiuGuoHui 2018-10-30
	 * @throws
	 */
	@RequestMapping("selectCityCodeVaule")
	@ResponseBody
	public Result selectCityCodeVaule(CodeCustom obj) {
		return codeService.selectCityCodeVaule(obj);
	}
	
	
	/**
	 * 保存城市位置
	 * @Title: saveCityCodeForApp   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("saveCityCodeForApp")
	@ResponseBody
	public Result saveCityCodeForApp(AppManageCustom obj) {
		return manageService.saveCityCodeForApp(obj);
	}
	/**
	 * 删除城市权限
	 * @Title: deleteCityCodeForApp   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("deleteCityCodeForApp")
	@ResponseBody
	public Result deleteCityCodeForApp(AppManageCustom obj) {
		return manageService.deleteCityCodeForApp(obj);
	}
	/**
	 * 查询
	 * @Title: queryAppListPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param page
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryAppListPage")
	@ResponseBody
	public Result queryAppListPage(Page page,AppManageCustom obj) {
		return manageService.queryAppListPage(page,obj);
	}
	/**
	 * 单条
	 * @Title: queryAppValueOne   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryAppValueOne")
	@ResponseBody
	public Result queryAppValueOne(AppManageCustom obj) {
		return manageService.queryAppValueOne(obj);
	}
	/**
	 * 添加
	 * @Title: saveAppValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("saveAppValue")
	@ResponseBody
	public Result saveAppValue(AppManageCustom obj) {
		return manageService.saveAppValue(obj);
	}
	/**
	 * 修改
	 * @Title: updateAppValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("updateAppValue")
	@ResponseBody
	public Result updateAppValue(AppManageCustom obj) {
		return manageService.updateAppValue(obj);
	}
	/**
	 * 删除
	 * @Title: deleteAppValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("deleteAppValue")
	@ResponseBody
	public Result deleteAppValue(AppManageCustom obj) {
		return manageService.deleteAppValue(obj);
	}
	
	/**
	 * app类型查询
	 * @Title: queryAppTypeListPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param page
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryAppTypeListPage")
	@ResponseBody
	public Result queryAppTypeListPage(Page page,AppTypeCustom obj) {
		return manageService.queryAppTypeListPage(page,obj);
	}
	/**
	 * app类型单条
	 * @Title: queryAppTypeValueOne   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryAppTypeValueOne")
	@ResponseBody
	public Result queryAppTypeValueOne(AppTypeCustom obj) {
		return manageService.queryAppTypeValueOne(obj);
	}
	/**
	 * app类型添加
	 * @Title: saveAppTypeValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("saveAppTypeValue")
	@ResponseBody
	public Result saveAppTypeValue(AppTypeCustom obj) {
		return manageService.saveAppTypeValue(obj);
	}
	/**
	 * app类型修改
	 * @Title: updateAppTypeValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("updateAppTypeValue")
	@ResponseBody
	public Result updateAppTypeValue(AppTypeCustom obj) {
		return manageService.updateAppTypeValue(obj);
	}
	/**
	 * app类型删除
	 * @Title: deleteAppTypeValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("deleteAppTypeValue")
	@ResponseBody
	public Result deleteAppTypeValue(AppTypeCustom obj) {
		return manageService.deleteAppTypeValue(obj);
	}
	
	
}
