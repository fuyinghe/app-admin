package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 学校概况-导师风采查询控制类
 * @author ZhangLiXia
 * @date 2018-03-15
 *
 */
@Controller
@RequestMapping("xygkdsfc")
public class XygkDSFCController extends AbstractModelController {
	public XygkDSFCController() {
		super.setModid("332cc7ab-0d56-4f95-bf7d-db161bd3da41");		
	}
	
    @Override
	public Map<String, Object> queryViewData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		//校园概况-导师风采-ViewData	T_XYGK_DSFC
		String moId = "f194a56c-b5f9-476a-aef8-d30da7a7b62d";
		//模型未定义返回clob字段内容
		Map<String, Object> result = super.queryViewDataCustom(moId, queryParamFiledMap);
		
		//调用单独方法返回clob内容,设置到返回值中
		String nr = super.queryDataClob(moId, "WBCONTENT", queryParamFiledMap);
		result.put("WBCONTENT", nr);
		return result;
	}
}
