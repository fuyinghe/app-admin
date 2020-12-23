package com.hrbwmxx.framework.apidoc.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.apidoc.model.Apidoc;
import com.hrbwmxx.framework.apidoc.service.IApidocService;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
/**
 * 
* @title: ApidocController 
* @description：系统接口api
* @author： lijingyu
* @date： 2018年1月9日 下午3:47:26
 */
@Controller
@RequestMapping("apidoc")
public class ApidocController {
	@Autowired
	private IApidocService apidocService;
	/**
	 * 
	* @MethodName: queryApidocForPage 
	* @description : api查询（含分页）
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:48:10
	* @param page
	* @param apidoc
	* @return Result
	 */
	@RequestMapping("queryApidocForPage")
	@ResponseBody
	public Result queryApidocForPage(@Param("page") Page page,@Param("apidoc") Apidoc apidoc){
		return apidocService.queryApidocForPage(page,apidoc);
	}
	/**
	 * 
	* @MethodName: queryApidocList 
	* @description : api查询（不含分页）
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:48:25
	* @return Result
	 */
	@RequestMapping("queryApidocList")
	@ResponseBody
	public Result queryApidocList(){
		return apidocService.queryApidocList();
	}
	/**
	 * 
	* @MethodName: queryApidocByPid 
	* @description : 查询-根据父节点查看api详情
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:48:58
	* @param pid
	* @return Result
	 */
	@RequestMapping("queryApidocByPid")
	@ResponseBody
	public Result queryApidocByPid(@Param("pid")BigDecimal pid){
		return apidocService.queryApidocByPid(pid);
	}
	/**
	 * 
	* @MethodName: queryApidocById 
	* @description : 查询-根据id查看api详情
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:50:11
	* @param id
	* @return Result
	 */
	@RequestMapping("queryApidocById")
	@ResponseBody
	public Result queryApidocById(@Valid BigDecimal id){
		return apidocService.queryApidocById(id);
	}
	/**
	 * 
	* @MethodName: saveApidocData 
	* @description : 保存-api数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:50:41
	* @param apidoc
	* @return Result
	 */
	@RequestMapping("saveApidocData")
	@ResponseBody
	public Result saveApidocData(@Valid Apidoc apidoc){
		return  apidocService.insertApidocData(apidoc);
	}
	/**
	 * 
	* @MethodName: updateApidocData 
	* @description : 修改-api数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:50:41
	* @param apidoc
	* @return Result
	 */
	@RequestMapping("updateApidocData")
	@ResponseBody
	public Result updateApidocData(@Valid Apidoc apidoc){
		return apidocService.updateApidocData(apidoc);
	}
	/**
	 * 
	* @MethodName: deleteApidocDataById 
	* @description : 删除-api数据
	* @author：lijingyu
	* @date： 2018年1月9日 下午3:51:30
	* @param id
	* @return Result
	 */
	@RequestMapping("deleteApidocDataById")
	@ResponseBody
	public Result deleteApidocDataById(@Param("id") BigDecimal id){
		return apidocService.deleteApidocDataById(id);
	}
}
