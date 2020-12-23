package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 学校概况-学科建设查询控制类
 * @author ZhangLiXia
 * @date 2018-03-16
 *
 */
@Controller
@RequestMapping("xygkxkjs")
public class XygkXKJSController extends AbstractModelController {
	public XygkXKJSController() {
		super.setModid("ab4a9fcb-47cc-4fd9-bd64-33f9aab0a9bc");		
	}
	
	@Override
	public Map<String, Object> queryViewData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		//校园概况-学科建设-ViewData	T_XYGK_XKJS
		String moId = "451ff5c8-c20b-4bcc-9088-78222aa4cc79";
		//模型未定义返回clob字段内容
		Map<String, Object> result = super.queryViewDataCustom(moId, queryParamFiledMap);
		
		//调用单独方法返回clob内容,设置到返回值中
		String nr = super.queryDataClob(moId, "WBCONTENT", queryParamFiledMap);
		result.put("WBCONTENT", nr);
		return result;
	}
}
