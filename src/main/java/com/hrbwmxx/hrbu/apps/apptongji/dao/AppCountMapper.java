package com.hrbwmxx.hrbu.apps.apptongji.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ;
import com.hrbwmxx.hrbu.apps.apptongji.vo.CustomTJ;

public interface AppCountMapper {
	
	
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getAppCountApps(@Param("obj")CustomTJ obj);
	
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getAppMonthCountApps(@Param("obj")CustomTJ obj);
	
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_jzg();
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_xs();
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_gg();
	/**
	 * 公共应用 
	 * 惠普金融
	 * 交通出行
	 * 生活服务
	 * 智慧乡村
	 * 广告
	 * 便民服务
	 * @param obj 
	 */
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_ggyy(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_hpjr(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_jtcx(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_shfw(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_zhxc(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_bmfw(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_advertising(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_alltype(@Param("obj")CustomTJ obj);
	public List<com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ> getApptimes_type(@Param("obj")CustomTJ obj);
	/**
	 * 查询年份
	 * @Title: selectYearMethod   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: List<UserTJ>
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	public List<UserTJ> selectYearMethod();

	
	
	
}
