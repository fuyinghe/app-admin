package com.hrbwmxx.hrbu.rotatepic.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicCustom;

public interface IAppRotatePicService {

	Result queryRotatePicListPage(Page page, AppRotatePicCustom obj);

	Result queryRotatePicList(AppRotatePicCustom obj);

	Result queryRotatePicById(AppRotatePicCustom obj);

	Result saveRotatePic(AppRotatePicCustom obj);

	Result updateRotatePic(AppRotatePicCustom obj);

	Result delRotatePic(AppRotatePicCustom obj);

	Result cancelRotatePic(AppRotatePicCustom obj);

	Result publishRotatePic(AppRotatePicCustom obj);

}
