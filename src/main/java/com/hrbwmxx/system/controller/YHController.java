package com.hrbwmxx.system.controller;
 
import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.service.IYHService;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.JSCustom;
import com.hrbwmxx.system.vo.YHCustom;

	/**
	 * 
	* @title: YHController 
	* @description：用户控制器
	* @author： 刘威巍
	* @date： 2018年1月12日 下午4:15:38
	 */
	@Controller
	@RequestMapping("yh")
	public class YHController {
		
	@Autowired
	private IYHService yhService;
	
	/**
	 * 
	* @MethodName: queryYHListPage 
	* @description : 用户分页查询
	* @author：刘威巍 
	* @date： 2018年1月12日 下午5:26:00
	* @param page
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryYHListPage")
	@ResponseBody
	public Result queryYHListPage(Page page,YHCustom obj) {
		return yhService.queryYHListPage(page,obj);
	}
	/**
	 * 
	* @MethodName: queryYHByField 
	* @description : 用户模糊查询
	* @author：刘威巍 
	* @date： 2018年1月13日 下午5:35:52
	* @param page
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryYHByField")
	@ResponseBody
	public Result queryYHByField(Page page,YHCustom obj) {
		return yhService.queryYHByField(obj);
	}
	/**
	 * 
	* @MethodName: saveYH 
	* @description : 保存用户
	* @author：刘威巍 
	* @date： 2018年1月13日 下午6:20:58
	* @param obj
	* @return Result
	 */
	@RequestMapping("saveYH") 
	@ResponseBody
	public Result saveYH(YHCustom obj) {
		return yhService.saveYH(obj);
	}
	/**
	 * 
	* @MethodName: updateYH 
	* @description : 更新用户
	* @author：刘威巍 
	* @date： 2018年1月14日 下午8:39:31
	* @param obj
	* @return Result
	 */
	@RequestMapping("updateYH")
	@ResponseBody
	public Result updateYH(YHCustom obj) {
		return yhService.updateYH(obj);
	}
	/**
	 * 删除用户
	 * @param obj
	 * @return
	 */
	@RequestMapping("deleteYH")
	@ResponseBody
	public Result deleteYH(YHCustom obj) {
		return yhService.deleteYH(obj);
	}
	/**
	 * 根据用户ID 分页查询已选功能
	 * @param page
	 * @param yh
	 * @return
	 */
	@RequestMapping("findSelectedMenuByYhId")
	@ResponseBody
	public Result findSelectedMenuByYhId(Page page,YHCustom yh) {
		return yhService.findSelectedMenuByYhId(page,yh);
	}
	/**
	 *  根据用户ID 分页查询未选功能
	 * @param page
	 * @param yh
	 * @return
	 */
	@RequestMapping("findUnSelectMenuByYhId")
	@ResponseBody
	public Result findUnSelectMenuByYhId(Page page,YHCustom yh) {
		return yhService.findUnSelectMenuByYhId(page,yh);
	}
	/**
	 * 添加未选菜单
	 * @param gn
	 * @param js
	 * @return
	 */
	@RequestMapping("addUnSelectMenu")
	@ResponseBody
	public Result addUnSelectMenu(GNCustom gn ,YHCustom yh) {
		return yhService.addUnSelectMenu(gn,yh);
	}
	/**
	 * 删除已选菜单
	 * @param gn
	 * @param js
	 * @return
	 */
	@RequestMapping("deleteSelectMenu")
	@ResponseBody
	public Result deleteSelectMenu(GNCustom gn ,YHCustom yh) {
		return yhService.deleteSelectMenu(gn,yh);
	}
	/**
	 * 修改密码
	 * @return
	 * @param userId oldpwd newpwd newpwd2
	 * @throws Result 
	 */
	@RequestMapping(value = "updatePassword", method = { RequestMethod.POST,
			RequestMethod.GET })
			
	@ResponseBody
	public Result updatePassword(@RequestParam HashMap<String, Object> resmap,HttpServletRequest request){
		return yhService.updatePassword(resmap,request);
	}
	/**
	 * 重置密码
	 * @return  
	 * @param userId
	 * @throws Result 
	 */
	@RequestMapping(value = "resetPassword", method = { RequestMethod.POST,
			RequestMethod.GET })
			
	@ResponseBody
	public Result resetPassword(@RequestParam HashMap<String, Object> resmap){
		return yhService.resetPassword(resmap);
	}
}
