package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 学校概况-现任领导查询控制类
 * @author ZhangLiXia
 * @date 2018-03-16
 *
 */
@Controller
@RequestMapping("xygkxrld")
public class XygkXRLDController extends AbstractModelController {
	
	public XygkXRLDController() {
		super.setModid("efc778f7-f780-4afc-9293-8acb8b6eebb7");		
	}

	@Override
	public Map<String, Object> queryViewData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		//校园概况-现任领导-ViewData	T_ZQXYGKXRLD
		String moId = "9c8ae026-5a51-4f83-83e1-71a26afed052";
		//模型未定义返回clob字段内容
		Map<String, Object> result = super.queryViewDataCustom(moId, queryParamFiledMap);
		
		//调用单独方法返回clob内容,设置到返回值中
		String nr = super.queryDataClob(moId, "WBCONTENT", queryParamFiledMap);
		result.put("WBCONTENT", nr);
		return result;
	}
	
	
}
