package com.hrbwmxx.hrbu.apps.jgxxts.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.jgxxts.service.IJgxxtsService;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.Jglsb;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.TsTz;

@Controller
@RequestMapping("jgxxts")
public class JgxxtsController {
	@Qualifier("JgxxtsServiceImpl")
	@Autowired
	private IJgxxtsService jgxxtsService;
	
	@RequestMapping("getJgxxtsPage")
	@ResponseBody
	public Result getJgxxtsPage(com.hrbwmxx.framework.model.Page page,@RequestParam Map resmap) {
		//获取本人的ID即学号
		String jgdm = com.hrbwmxx.framework.util.TokenToUserId.getUserId(resmap);
//		String jgdm="123124";
		System.out.println("USERID:"+jgdm);
		if(jgdm!=null && !"".equals(jgdm)) {
			//返回跟本人有管的
			return jgxxtsService.getJgxxtsPage(page, resmap,jgdm);
		}else {
			//返回通用的
			return jgxxtsService.getJgxxtsPage(page, resmap,"0");
		}
		
	}
	
	@RequestMapping("queryJggltzts")
	@ResponseBody
	public Result queryJggltzts(TsTz obj,@RequestParam Map resmap) {
		//获取本人的ID即学号
		String jgdm = com.hrbwmxx.framework.util.TokenToUserId.getUserId(resmap);
//		String jgdm="123124";
		if(jgdm!=null && !"".equals(jgdm)) {
			obj.setJgdm(jgdm);
		}
		return jgxxtsService.queryJggltzts(obj);
		
	}
	
	@RequestMapping("updateJglsbzt")
	@ResponseBody
	public Result updateJglsbzt(Jglsb obj,@RequestParam Map resmap) {
		//获取本人的ID即学号
		String jgdm = com.hrbwmxx.framework.util.TokenToUserId.getUserId(resmap);
		obj.setJgdm(jgdm);
		return jgxxtsService.updateJglsbzt(obj);
		
	}

}
