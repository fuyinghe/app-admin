package com.hrbwmxx.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.service.ICodeTypeService;
import com.hrbwmxx.system.vo.CodeTypeCustom;

/**
 * 
 * @ClassName:  CodeTypeController   
 * @Description:TODO(代码类型)   
 * @date:   2018年10月15日 
 * @author: Mr丶ZHAO  
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */
@Controller
@RequestMapping("codeType")
public class CodeTypeController {
	
	@Autowired
	private ICodeTypeService service;
	
	/**
	 * 
	 * @Title: queryCodeTypeListPage   
	 * @Description: TODO(查询)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryCodeTypeListPage")
	@ResponseBody
	public Result queryCodeTypeListPage(CodeTypeCustom obj ,Page page) {
		return service.queryCodeTypeListPage(obj,page);
		
	}
	/**
	 * 
	 * @Title: queryCodeTypeFieldById   
	 * @Description: TODO(单条查询)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("queryCodeTypeFieldById")
	@ResponseBody
	public Result queryCodeTypeFieldById(CodeTypeCustom obj) {
		return service.queryCodeTypeFieldById(obj);
		
	}
	/**
	 * 
	 * @Title: saveCodeTypeValue   
	 * @Description: TODO(保存)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("saveCodeTypeValue")
	@ResponseBody
	public Result saveCodeTypeValue(CodeTypeCustom obj) {
		return service.saveCodeTypeValue(obj);
		
	}
	/**
	 * 
	 * @Title: updateCodeTypeValue   
	 * @Description: TODO(修改)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("updateCodeTypeValue")
	@ResponseBody
	public Result updateCodeTypeValue(CodeTypeCustom obj) {
		return service.updateCodeTypeValue(obj);
		
	}
	/**
	 * 
	 * @Title: deleteCodeTypeValue   
	 * @Description: TODO(删除)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("deleteCodeTypeValue")
	@ResponseBody
	public Result deleteCodeTypeValue(CodeTypeCustom obj) {
		return service.deleteCodeTypeValue(obj);
		
	}
	
	
	
	
	
	
}
