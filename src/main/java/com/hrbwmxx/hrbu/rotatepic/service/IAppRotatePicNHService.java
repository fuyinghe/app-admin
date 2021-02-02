package com.hrbwmxx.hrbu.rotatepic.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicNHCustom;

public interface IAppRotatePicNHService {

	Result queryRotatePicListPage(Page page, AppRotatePicNHCustom obj);

	Result queryRotatePicList(AppRotatePicNHCustom obj);

	Result queryRotatePicById(AppRotatePicNHCustom obj);

	Result saveRotatePic(AppRotatePicNHCustom obj);

	Result updateRotatePic(AppRotatePicNHCustom obj);

	Result delRotatePic(AppRotatePicNHCustom obj);

	Result cancelRotatePic(AppRotatePicNHCustom obj);

	Result publishRotatePic(AppRotatePicNHCustom obj);

}
