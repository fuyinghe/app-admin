package com.hrbwmxx.hrbu.apps.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.admin.model.JSYY;
import com.hrbwmxx.hrbu.apps.admin.service.JSYYService;
import com.webmos.framework.controller.AbstractModelController;

/**
 * 角色应用
 * 
 * @author hechunyang
 * @date 2018年5月8日
 * @remark TODO
 */
@Controller
@RequestMapping("jsyy")
public class JSYYController extends AbstractModelController {

	@Qualifier("JSYYServiceImpl")
	@Autowired
	private JSYYService service;

	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam Map<?, ?> resmap) {
		JSYY jsyy = new JSYY();
		jsyy.setAppId((String) resmap.get("appId"));
		jsyy.setRoleId((String) resmap.get("roleId"));
		return service.addJSYY(jsyy);
	}

	@RequestMapping("del")
	@ResponseBody
	public Result del(@SuppressWarnings("rawtypes") @RequestParam Map resmap) {
		String appId = (String) resmap.get("appId");
		String roleId = (String) resmap.get("roleId");
		return service.removeJSYY(appId, roleId);
	}

}
