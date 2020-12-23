package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 学校概况-专业介绍查询控制类
 * @author ZhangLiXia
 * @date 2018-03-15
 *
 */
@Controller
@RequestMapping("xygkzyjs")
public class XygkZYJSController extends AbstractModelController {
	public XygkZYJSController() {
		super.setModid("a8c84a16-cfd5-4051-90ce-689006d82fca");		
	}
	
	@Override
	public Map<String, Object> queryViewData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		//校园概况-专业介绍-ViewData	T_XYGK_ZYJS
		String moId = "20ed7813-94be-4d32-9c1b-4d9b85d38afb";
		//模型未定义返回clob字段内容
		Map<String, Object> result = super.queryViewDataCustom(moId, queryParamFiledMap);
		
		//调用单独方法返回clob内容,设置到返回值中
		String nr = super.queryDataClob(moId, "WBCONTENT", queryParamFiledMap);
		result.put("WBCONTENT", nr);
		return result;
	}
}
