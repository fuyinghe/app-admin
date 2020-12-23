package com.hrbwmxx.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.service.ICodeTypeService;
import com.hrbwmxx.system.service.IPayTypeService;
import com.hrbwmxx.system.vo.CodeTypeCustom;
import com.hrbwmxx.system.vo.PayTypeCustom;

/**
* @title: PayTypeController 
* @description：TODO
* @author： 刘威巍
* @date： 2018年11月15日 下午2:59:37
 */
@Controller
@RequestMapping("payType")
public class PayTypeController {
	
	@Autowired
	private IPayTypeService service;
	
	/**
	* @MethodName: queryPayTypeListPage 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年11月15日 下午3:14:43
	* @param obj
	* @param page
	* @return Result
	 */
	@RequestMapping("queryPayTypeListPage")
	@ResponseBody
	public Result queryPayTypeListPage(PayTypeCustom obj ,Page page) {
		return service.queryPayTypeListPage(obj,page);
		
	}
	/**
	* @MethodName: queryPayTypeFieldById 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年11月15日 下午3:15:07
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryPayTypeFieldById")
	@ResponseBody
	public Result queryPayTypeFieldById(PayTypeCustom obj) {
		return service.queryPayTypeFieldById(obj);
		
	}
	/**
	* @MethodName: savePayTypeValue 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年11月15日 下午3:15:30
	* @param obj
	* @return Result
	 */
	@RequestMapping("savePayTypeValue")
	@ResponseBody
	public Result savePayTypeValue(PayTypeCustom obj) {
		return service.savePayTypeValue(obj);
		
	}
	/**
	* @MethodName: updatePayTypeValue 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年11月15日 下午3:15:53
	* @param obj
	* @return Result
	 */
	@RequestMapping("updatePayTypeValue")
	@ResponseBody
	public Result updatePayTypeValue(PayTypeCustom obj) {
		return service.updatePayTypeValue(obj);
		
	}
	/**
	* @MethodName: deletePayTypeValue 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年11月15日 下午3:16:18
	* @param obj
	* @return Result
	 */
	@RequestMapping("deletePayTypeValue")
	@ResponseBody
	public Result deletePayTypeValue(PayTypeCustom obj) {
		return service.deletePayTypeValue(obj);
	}
	
	
	
	
	
	
}
