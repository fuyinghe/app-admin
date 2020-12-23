package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.vo.AppTypeCustom;

public interface IAppTypeMapper {

	List<AppTypeCustom> queryAppTypeListPage(@Param("page") Page page, @Param("obj") AppTypeCustom obj);

	int queryAppTypeListPageCount(@Param("obj") AppTypeCustom obj, @Param("page") Page page);

	AppTypeCustom queryAppTypeValueOne(@Param("obj") AppTypeCustom obj);

	void saveAppTypeValue(AppTypeCustom obj);

	void updateAppTypeValue(AppTypeCustom obj);

	void deleteAppTypeValue(String id);

}
