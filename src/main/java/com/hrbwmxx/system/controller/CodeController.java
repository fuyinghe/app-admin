package com.hrbwmxx.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.service.ICodeService;
import com.hrbwmxx.system.vo.CodeCustom;


/**
 * 
 * @ClassName:  CodeController   
 * @Description:TODO(代码)   
 * @date:   2018年10月15日 
 * @author: Mr丶ZHAO  
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */

@Controller
@RequestMapping("code")
public class CodeController {

	@Autowired
	private ICodeService codeService;
	/**
	 * 
	 * @Title: queryCodeListPage   
	 * @Description: TODO(分页查询)   
	 * @param: @param page
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: Mr丶ZHAO      
	 * @throws
	 */
	@RequestMapping("queryCodeListPage")
	@ResponseBody
	public Result queryCodeListPage(Page page,CodeCustom obj) {
		return codeService.queryCodeListPage(page,obj);
	}
	/**
	 * @Title: queryCodeByField  
	 * @Description: TODO(详细信息)  
	 * @param @param obj
	 * @param @return    参数  
	 * @return Result    返回类型  
	 * @author    CHIUCLOUD(云)
	 * @date 2018年8月16日
	 * @throws
	 */
	@RequestMapping("queryCodeById")
	@ResponseBody
	public Result queryCodeByField(CodeCustom obj) {
		return codeService.queryCodeByField(obj);
	}
	
	/**
	 * 
	 * @Title: saveCode   
	 * @Description: TODO(保存)   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Result
	 * @author: Mr丶ZHAO      
	 * @throws
	 */
	@RequestMapping("saveCodeValue")
	@ResponseBody
	public Result saveCode(CodeCustom obj) {
		return codeService.saveCode(obj);
	}
	/**
	* @Description: updateCode
	* @Author: CHIUCLOUD(云)  
	* @Date: 2018年1月18日 上午11:29:37
	* @Version: 1.0
	* @param obj
	* @return
	*/
	@RequestMapping("updateCodeValue")
	@ResponseBody
	public  Result updateCode(CodeCustom obj) {
		return codeService.updateCode(obj);
	}
	/**
	 * @Title: deleteCode  
	 * @Description: TODO(删除)  
	 * @param @param obj
	 * @param @return    参数  
	 * @return Result    返回类型  
	 * @author    CHIUCLOUD(云)
	 * @date 2018年8月16日
	 * @throws
	 */
	@RequestMapping("deleteCodeValue")
	@ResponseBody
	public  Result deleteCode(CodeCustom obj) {
		return codeService.deleteCode(obj);
	}
	/**
	 * 
	 * @Title: selectTypeValueForCode   
	 * @Description: TODO(下拉)   
	 * @param: @return      
	 * @return: Result
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping("selectTypeValueForCode")
	@ResponseBody
	public  Result selectTypeValueForCode() {
		return codeService.selectTypeValueForCode();
	}
	
	
	
	
}
