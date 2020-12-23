package com.hrbwmxx.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.service.IJSService;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.JSCustom;
import com.hrbwmxx.system.vo.YHCustom;

/**
 * 
* @title: JSController 
* @description：角色控制器
* @author： lijingyu
* @date： 2018年1月12日 上午10:35:39
 */
@Controller
@RequestMapping("js")
public class JSController {
	@Autowired
	private IJSService jsService;
	/**
	 * 
	* @MethodName: queryJSListPage 
	* @description : 角色分页查询
	* @author：lijingyu
	* @date： 2018年1月12日 上午11:14:23
	* @param page
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryJSListPage")
	@ResponseBody
	public Result queryJSListPage(Page page,JSCustom obj) {
		return jsService.queryJSListPage(page,obj);
	}
	/**
	 * 
	* @MethodName: queryJSList 
	* @description : 查询角色列表-（可以查询单个角色）
	* @author：lijingyu
	* @date： 2018年1月19日 下午4:23:11
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryJSList")
	@ResponseBody
	public Result queryJSList(JSCustom obj) {
		return jsService.queryJSList(obj);
	}
	/**
	 * 
	* @MethodName: saveJS 
	* @description : 保存角色
	* @author：lijingyu
	* @date： 2018年1月12日 上午11:19:50
	* @param obj
	* @return Result
	 */
	@RequestMapping("saveJS")
	@ResponseBody
	public Result saveJS(JSCustom obj) {
		return jsService.saveJS(obj);
	}
	/**
	 * 
	* @MethodName: updateJS 
	* @description : 更新角色
	* @author：lijingyu
	* @date： 2018年1月12日 上午11:21:11
	* @param obj
	* @return Result
	 */
	@RequestMapping("updateJS")
	@ResponseBody
	public  Result updateJS(JSCustom obj) {
		return jsService.updateJS(obj);
	}
	/**
	 * 
	* @MethodName: deleteJs 
	* @description : 删除角色
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:40:34
	* @param obj
	* @return Result
	 */
	@RequestMapping("deleteJs")
	@ResponseBody
	public Result deleteJs(JSCustom obj) {
		return jsService.deleteJs(obj);
	}
	/**
	 * 通过用户ID 查出该用户已选的角色信息
	 * @param yhId
	 * @return
	 */
	@RequestMapping("findSelectRoleByUserId")
	@ResponseBody
	public Result findRoleByUserId(Page page ,YHCustom yh) {
		return jsService.findRoleByUserId(page ,yh);
	}
	/**
	 *  通过用户ID 查出该用户未选的角色信息
	 * @param page
	 * @param yh
	 * @return
	 */
	@RequestMapping("findUnselectRoleListByyhId")
	@ResponseBody
	public Result findUnselectRoleListByyhId(Page page ,YHCustom yh,String mc) {
		//框架错误，单独处理一下
		yh.setJsMc(mc);
		return jsService.findUnselectRoleListByyhId(page ,yh);
	}
	/**
	 * 给用户添加未选的角色
	 * @param yh
	 * @param js
	 * @return
	 */
	@RequestMapping("addUnSelectRole")
	@ResponseBody
	public Result addUnSelectRole(YHCustom yh ,JSCustom js){
		return jsService.addUnSelectRole(yh,js);
	}
	/**
	 * 删除用户已选角色
	 * @param yh
	 * @param js
	 * @return
	 */
	@RequestMapping("deleteSelectedRole")
	@ResponseBody
	public Result deleteSelectedRole(YHCustom yh ,JSCustom js) {
		return jsService.deleteSelectedRole(yh,js);
	}
	/**
	 * 根据角色id 查出拥有该角色的用户
	 * @param page
	 * @param js
	 * @return
	 */
	@RequestMapping("findSelectdeYhByJsId")
	@ResponseBody
	public Result findSelectdeYhByJsId(Page page ,JSCustom js) {
		return jsService.findSelectdeYhByJsId(page,js);
	}
	/**
	 * 根据角色id 查出未拥有该角色的用户
	 * @param page
	 * @param js
	 * @return
	 */
	@RequestMapping("findUnseleteYhByJsId")
	@ResponseBody
	public Result findUnseleteYhByJsId(Page page ,JSCustom js) {
		return jsService.findUnseleteYhByJsId(page,js);
	}
	/**
	 * 根据角色ID 查出已选菜单
	 * @param page
	 * @param js
	 * @return
	 */
	@RequestMapping("findSelectGNByJsId")
	@ResponseBody
	public Result findSelectGNByJsId(Page page ,JSCustom js) {
		return jsService.findSelectGNByJsId(page,js);
		
	}
	/**
	 *  根据角色ID 查出未选菜单
	 * @param page
	 * @param js
	 * @return
	 */
	@RequestMapping("findUnSelectGNByJsId")
	@ResponseBody
	public Result findUnSelectGNByJsId(Page page ,JSCustom js) {
		return jsService.findUnSelectGNByJsId(page,js);
	}
	@RequestMapping("addUnSelectMenu")
	@ResponseBody
	public Result addUnSelectMenu(GNCustom gn ,JSCustom js) {
		return jsService.addUnSelectMenu(gn,js);
	}
	@RequestMapping("deleteSelectMenu")
	@ResponseBody
	public Result deleteSelectMenu(GNCustom gn ,JSCustom js) {
		return jsService.deleteSelectMenu(gn,js);
	}
	
	
	
}
