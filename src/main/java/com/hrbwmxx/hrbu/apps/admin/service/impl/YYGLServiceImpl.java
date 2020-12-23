package com.hrbwmxx.hrbu.apps.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.apps.admin.dao.JSYYMapper;
import com.hrbwmxx.hrbu.apps.admin.model.JS;
import com.hrbwmxx.hrbu.apps.admin.service.YYGLService;
import com.hrbwmxx.hrbu.apps.admin.vo.JSYYVo;
import com.hrbwmxx.hrbu.log.model.Log;
import com.webmos.framework.vo.DataModelVo;

@Service("YYGLServiceImpl")
public class YYGLServiceImpl implements YYGLService {

	@Autowired
	JSYYMapper jsyyMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	@SuppressWarnings("unchecked")
	public DataModelVo listData(DataModelVo temp) {
		List<Map<String, Object>> list = (List<Map<String, Object>>) temp
				.getRows();
		List<JSYYVo> vos = jsyyMapper.queryAllJSYY();
		for (Map<String, Object> yyMap : list) {
			List<JS> roles = new ArrayList<JS>();
			for (JSYYVo vo : vos) {
				if (!vo.getAppId().equals(yyMap.get("ID")))
					continue;
				JS js = new JS(vo.getRoleId(), vo.getName(), vo.getDes());
				roles.add(js);
			}
			yyMap.put("roles", roles);
		}
		return temp;
	}

	/**
	 * 
	 */
	public Result changeAppState(String appId, int state) {
		ResultEntity resp = new ResultEntity();
		try {
			if (state == 1) {
				jsyyMapper.updateYYState0(appId);
			} else {
				jsyyMapper.updateYYState1(appId);
			}
		} catch (Exception e) {
			resp.setErrcode("200");
			resp.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(resp.getErrcode(),resp.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return (Result) resp;
	}

}
