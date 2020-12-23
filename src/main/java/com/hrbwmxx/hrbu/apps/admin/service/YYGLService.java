package com.hrbwmxx.hrbu.apps.admin.service;

import com.hrbwmxx.framework.model.Result;
import com.webmos.framework.vo.DataModelVo;
public interface YYGLService {

	com.webmos.framework.model.Result listData(DataModelVo temp);

	Result changeAppState(String appId,int state);

}
