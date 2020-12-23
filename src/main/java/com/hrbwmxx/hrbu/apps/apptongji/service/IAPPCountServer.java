package com.hrbwmxx.hrbu.apps.apptongji.service;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.apptongji.vo.CustomTJ;

public interface IAPPCountServer {
	
	
	/**
	 * 获取app使用情况(全部)
	 * @param obj 
	 * @return
	 */
	public Result getAppCountApps(CustomTJ obj);
	
	/**
	 * 获取app使用情况(近一个月)
	 * @return
	 */
	public Result getAppMonthCountApps(CustomTJ obj);

	/**
	 * 应用访问趋势
	 * @return
	 */
	public Result getApptimes(CustomTJ obj);
	/*
	 * 查询年份
	 */
	public Result selectYearForApp();
	
	public Result queryCityCodeForAPP();
}
