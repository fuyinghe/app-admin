package com.hrbwmxx.system.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.vo.AppManageCustom;
import com.hrbwmxx.system.vo.AppTypeCustom;

public interface IAppManageService {
	//查询
	Result queryAppListPage(Page page, AppManageCustom obj);
	//单条
	Result queryAppValueOne(AppManageCustom obj);
	//添加
	Result saveAppValue(AppManageCustom obj);
	//修改
	Result updateAppValue(AppManageCustom obj);
	//删除
	Result deleteAppValue(AppManageCustom obj);
	//类型查询
	Result queryAppTypeListPage(Page page, AppTypeCustom obj);
	//类型单条
	Result queryAppTypeValueOne(AppTypeCustom obj);
	//类型添加
	Result saveAppTypeValue(AppTypeCustom obj);
	//类型修改
	Result updateAppTypeValue(AppTypeCustom obj);
	//类型删除
	Result deleteAppTypeValue(AppTypeCustom obj);
	//保存城市位置
	Result saveCityCodeForApp(AppManageCustom obj);
	//删除城市权限
	Result deleteCityCodeForApp(AppManageCustom obj);

}
