package com.hrbwmxx.hrbu.rotatepic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.rotatepic.service.IAppRotatePicService;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicCustom;

/**
 * 轮播图片
* <p>Title: AppRotatePicController</p>  
* <p>Description: </p>  
* @author Mr.Zhao 
* @date 2021年1月20日
 */
@Controller
@RequestMapping("rotate")
public class AppRotatePicController {

	public static final Logger logger = LoggerFactory.getLogger(AppRotatePicController.class);
	
	@Autowired
	private IAppRotatePicService rotateService;
	
	
	
	@ResponseBody
	@RequestMapping("queryRotatePicListPage")
	public Result queryRotatePicListPage(Page page,AppRotatePicCustom obj ) {
		return rotateService.queryRotatePicListPage(page,obj);
	}
	
	@ResponseBody
	@RequestMapping("queryRotatePicList")
	public Result queryRotatePicList(AppRotatePicCustom obj ) {
		return rotateService.queryRotatePicList(obj);
	}
	
	@ResponseBody
	@RequestMapping("queryRotatePicById")
	public Result queryRotatePicById(AppRotatePicCustom obj ) {
		return rotateService.queryRotatePicById(obj);
	}
	
	@ResponseBody
	@RequestMapping("saveRotatePic")
	public Result saveRotatePic(AppRotatePicCustom obj ) {
		return rotateService.saveRotatePic(obj);
	}
	
	@ResponseBody
	@RequestMapping("updateRotatePic")
	public Result updateRotatePic(AppRotatePicCustom obj ) {
		return rotateService.updateRotatePic(obj);
	}
	
	@ResponseBody
	@RequestMapping("delRotatePic")
	public Result delRotatePic(AppRotatePicCustom obj ) {
		return rotateService.delRotatePic(obj);
	}
	
	//cancel
	@ResponseBody
	@RequestMapping("cancelRotatePic")
	public Result cancelRotatePic(AppRotatePicCustom obj ) {
		return rotateService.cancelRotatePic(obj);
	}
	//publish
	@ResponseBody
	@RequestMapping("publishRotatePic")
	public Result publishRotatePic(AppRotatePicCustom obj ) {
		return rotateService.publishRotatePic(obj);
	}
}
