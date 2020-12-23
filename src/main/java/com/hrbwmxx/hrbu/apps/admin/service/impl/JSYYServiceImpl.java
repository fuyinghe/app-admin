package com.hrbwmxx.hrbu.apps.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.apps.admin.dao.JSYYMapper;
import com.hrbwmxx.hrbu.apps.admin.model.JSYY;
import com.hrbwmxx.hrbu.apps.admin.service.JSYYService;
import com.hrbwmxx.hrbu.log.model.Log;

@Service("JSYYServiceImpl")
public class JSYYServiceImpl implements JSYYService {

	@Autowired
	JSYYMapper jsyyMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	public Result addJSYY(JSYY dto) {
		ResultEntity resp = new ResultEntity();
		JSYY temp = jsyyMapper.findByCondition(dto.getAppId(), dto.getRoleId());
		if (temp != null) {
			resp.setErrcode("200");
			resp.setErrmsg("不能重复添加");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(resp.getErrcode(),resp.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return resp;
		}
		jsyyMapper.insert(dto);
		return resp;

	}

	public Result removeJSYY(String appId, String roleId) {
		ResultEntity resp = new ResultEntity();
		try {
			jsyyMapper.delete(appId, roleId);
		} catch (Exception e) {
			resp.setErrcode("200");
			resp.setErrmsg("删除失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(resp.getErrcode(),resp.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return resp;

	}
}
