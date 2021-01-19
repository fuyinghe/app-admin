package com.hrbwmxx.hrbu.rotatepic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicCustom;

public interface AppRotatePicMapper {

	List<AppRotatePicCustom> queryRotatePicListPage(@Param("page")Page page, @Param("obj")AppRotatePicCustom obj);

	int queryRotatePicForCount(@Param("page")Page page, @Param("obj")AppRotatePicCustom obj);

	List<AppRotatePicCustom> queryRotatePicList(@Param("obj")AppRotatePicCustom obj);

	AppRotatePicCustom queryRotatePicById(@Param("obj")AppRotatePicCustom obj);

	void saveRotatePic(AppRotatePicCustom obj);

	void updateRotatePic(AppRotatePicCustom obj);

	void delRotatePic(AppRotatePicCustom pic);

}
