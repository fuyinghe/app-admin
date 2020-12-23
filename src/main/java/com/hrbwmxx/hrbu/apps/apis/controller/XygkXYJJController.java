package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.Page;
import com.webmos.framework.model.Result;
/**
 * 学校概况-学院简介查询控制类
 * @author ZhangLiXia
 * @date 2018-03-16
 *
 */
@Controller
@RequestMapping("xygkxyjj")
public class XygkXYJJController extends AbstractModelController {
	
	
	public XygkXYJJController() {
		super.setModid("d98e6bf4-3173-46cf-b40a-be8ccebb7ed6");
	}

	@Override
	public Map<String, Object> queryViewData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		//校园概况-学校简介-ViewData	T_ZQXYGKXYJJ
		String moId = "075a9054-cb67-480e-aaa0-fdc814599596";
		//模型未定义返回clob字段内容
		Map<String, Object> result = super.queryViewDataCustom(moId, queryParamFiledMap);
		
		//调用单独方法返回clob内容,设置到返回值中
		String nr = super.queryDataClob(moId, "WBCONTENT", queryParamFiledMap);
		result.put("WBCONTENT", nr);
		return result;
	}
}
