package com.hrbwmxx.hrbu.apps.apptongji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.apptongji.service.IAPPCountServer;
import com.hrbwmxx.hrbu.apps.apptongji.vo.CustomTJ;


@Controller
@RequestMapping("appcount")
public class APPCountController {
	
	@Qualifier("APPCountServerImpl")
	@Autowired
	private IAPPCountServer appCountServer;
	
	
	/**
	 * 获取app使用情况
	 * 时时统计全部应用访问量
	 * @param resmap
	 * @return
	 */
	@RequestMapping("getAppCountApps")
	@ResponseBody
	public Result getAppCountApps(CustomTJ obj) {
		
		return appCountServer.getAppCountApps(obj);
		
	}
	
	/**
	 * 获取app使用情况
	 * 最近一个月的应用访问情况
	 * @param resmap
	 * @return
	 */
	@RequestMapping("getAppMonthCountApps")
	@ResponseBody
	public Result getAppMonthCountApps(CustomTJ obj) {
		
		return appCountServer.getAppMonthCountApps(obj);
		
	}
	
	/**
	 * 应用访问趋势
	 * 全部
	 * @param resmap
	 * @return
	 */
	@RequestMapping("getApptimes")
	@ResponseBody
	public Result getApptimes(CustomTJ obj) {
		
		return appCountServer.getApptimes(obj);
		
	}
	/**
	 * 查询年份
	 * @Title: selectYearMethod   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("selectYear")
	@ResponseBody
	public Result selectYearForApp() {
		
		return appCountServer.selectYearForApp();
		
	}
	/**
	 * 查询地区
	 * @Title: queryCityCodeForAPP   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryCityCodeForAPP")
	@ResponseBody
	public Result queryCityCodeForAPP() {
		
		return appCountServer.queryCityCodeForAPP();
		
	}
	
	
}
