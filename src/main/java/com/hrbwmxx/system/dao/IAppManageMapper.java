package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.vo.AppManageCustom;

public interface IAppManageMapper {
	//查询
	List<AppManageCustom> queryAppListPage(@Param("obj")AppManageCustom obj, @Param("page")Page page);
	//条数
	int queryAppListPageCount(@Param("obj")AppManageCustom obj, @Param("page")Page page);
	//单条
	AppManageCustom queryAppValueOne(@Param("obj")AppManageCustom obj);
	//添加
	void saveAppValue(AppManageCustom obj);
	//修改
	void updateAppValue(@Param("obj")AppManageCustom obj);
	//删除
	void deleteAppValue(String id);
	//删除城市权限
	void updateAppCityValue(AppManageCustom obj);

}
