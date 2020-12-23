package com.hrbwmxx.hrbu.apps.admin.service;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.admin.model.JSYY;

public interface JSYYService {

	Result addJSYY(JSYY jsyy);

	Result removeJSYY(String appId, String roleId);
}
