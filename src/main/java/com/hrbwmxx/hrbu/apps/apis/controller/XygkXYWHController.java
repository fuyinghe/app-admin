package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 学校概况-校园文化查询控制类
 * @author ZhangLiXia
 * @date 2018-05-04
 *
 */
@Controller
@RequestMapping("xygkxywh")
public class XygkXYWHController extends AbstractModelController {
	public XygkXYWHController() {
		super.setModid("6C8FDC0A9D9A4F18AB150885083FCD86");		
	}
	
	@Override
	public Map<String, Object> queryViewData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		//校园文化-ViewData	T_XYGK_XYWH
		String moId = "3b20a22f-4651-448e-bd24-880ef4bc0513";
		//模型未定义返回clob字段内容
		Map<String, Object> result = super.queryViewDataCustom(moId, queryParamFiledMap);
		
		//调用单独方法返回clob内容,设置到返回值中
		String nr = super.queryDataClob(moId, "WBCONTENT", queryParamFiledMap);
		result.put("WBCONTENT", nr);
		return result;
	}
}
