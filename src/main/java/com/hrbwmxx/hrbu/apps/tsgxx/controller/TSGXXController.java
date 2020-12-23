package com.hrbwmxx.hrbu.apps.tsgxx.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.tsgxx.service.ITSGXXService;
/**
 * 图书馆信息，包括外借情况和欠款情况
 * @author Yangyishan
 * @date 2018-05-07
 *
 */
@Controller
@RequestMapping("tsgxx")
public class TSGXXController {
	@Qualifier("TSGXXServiceImpl")
	@Autowired
	private ITSGXXService tsgxxService;
	@RequestMapping("gettsgxx")
	@ResponseBody
	public Result gettsgxx(@RequestParam Map resmap){
		//String token =(String) resmap.get("token");
		  //String dztm ="08081624";
		  resmap.put("dztm", com.hrbwmxx.framework.util.TokenToUserId.getUserId(resmap));
		  return tsgxxService.gettsgxx(resmap);
}
}