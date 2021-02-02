package com.hrbwmxx.hrbu.rotatepic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicNHCustom;

public interface AppRotatePicNHMapper {

	List<AppRotatePicNHCustom> queryRotatePicListPage(@Param("page")Page page, @Param("obj")AppRotatePicNHCustom obj);

	int queryRotatePicForCount(@Param("page")Page page, @Param("obj")AppRotatePicNHCustom obj);

	List<AppRotatePicNHCustom> queryRotatePicList(@Param("obj")AppRotatePicNHCustom obj);

	AppRotatePicNHCustom queryRotatePicById(@Param("obj")AppRotatePicNHCustom obj);

	void saveRotatePic(AppRotatePicNHCustom obj);

	void updateRotatePic(AppRotatePicNHCustom obj);

	void delRotatePic(AppRotatePicNHCustom pic);

}
