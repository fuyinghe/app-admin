package com.hrbwmxx.hrbu.apps.apptongji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.apptongji.dao.AppCountMapper;
import com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ;
import com.hrbwmxx.hrbu.apps.apptongji.vo.CustomTJ;
import com.hrbwmxx.hrbu.apps.apptongji.vo.TJVo;
import com.hrbwmxx.system.dao.CodeMapper;
import com.hrbwmxx.system.vo.CodeCustom;

@Service("APPCountServerImpl")
public class APPCountServerImpl implements IAPPCountServer {

	@Autowired
	AppCountMapper appCountMapper;
	@Autowired
	private CodeMapper codeMapper;

	/**
	 * 获取app使用情况（全部）
	 */
	public Result getAppCountApps(CustomTJ obj) {
		TJVo result = new TJVo();
		List<UserTJ> appList = appCountMapper.getAppCountApps(obj);
		result.setAppList(appList);
		return result;
	}
	
	/**
	 * 获取app使用情况（近一个月）
	 */
	public Result getAppMonthCountApps(CustomTJ obj) {
		TJVo result = new TJVo();
		List<UserTJ> appList = appCountMapper.getAppMonthCountApps(obj);
		result.setAppList(appList);
		return result;
	}
	
	/**
	 * 应用访问趋势
	 */
	public Result getApptimes(CustomTJ obj) {
		TJVo result = new TJVo();
		System.err.println("id:"+obj.getYear());
		/**
		 * 		公共应用 
				惠普金融
				交通出行
				生活服务
				智慧乡村
				广告
				便民服务
		 */
		List<UserTJ> ggyyappList = appCountMapper.getApptimes_ggyy(obj);
		result.setGgyyappList(ggyyappList);
		return result;
	}
	
	/**
	 * 查询年份
	 * <p>Title: selectYearMethod</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.hrbwmxx.hrbu.apps.apptongji.service.IAPPCountServer#selectYearMethod()
	 */
	@Override
	public Result selectYearForApp() {
		// TODO Auto-generated method stub
		TJVo result = new TJVo();
		List<UserTJ>yearList=appCountMapper.selectYearMethod();
		result.setYearappList(yearList);
		return result;
	}
	/**
	 * 查询城市代码名称
	 * <p>Title: queryCityCodeForAPP</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.hrbwmxx.hrbu.apps.apptongji.service.IAPPCountServer#queryCityCodeForAPP()
	 */
	@Override
	public Result queryCityCodeForAPP() {
		TJVo result = new TJVo();
		List<CodeCustom> list =	codeMapper.queryCityCodeForAPP(null);
		result.setCitycodeappList(list);
		return result;
	}
	
	
	

}
