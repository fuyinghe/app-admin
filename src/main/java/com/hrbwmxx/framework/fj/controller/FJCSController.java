package com.hrbwmxx.framework.fj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrbwmxx.framework.fj.model.FJCS;
import com.hrbwmxx.framework.fj.service.IFJCSService;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
@Controller
@RequestMapping("fjcs")
public class FJCSController {
	@Autowired
	private IFJCSService fjcsService;
	
	@RequestMapping("queryFJCSListPage")
	public Result queryFjcsListPage (Page page,FJCS obj) {
		return fjcsService.queryFJCSListPage(page,obj);
	}
	@RequestMapping("saveFJCS")
	public Result saveFJCS (FJCS obj) {
		return fjcsService.saveFJCS(obj);
	}
	@RequestMapping("updateFJCS")
	public Result updateFJCS (FJCS obj) {
		return fjcsService.updateFJCS(obj);
	}
}
