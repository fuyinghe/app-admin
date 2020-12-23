package com.hrbwmxx.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.model.GN;
import com.hrbwmxx.system.service.IGNService;
import com.hrbwmxx.system.vo.GNCustom;

@Controller
@RequestMapping("gn")
public class GNController {
	@Autowired
	private IGNService gnService;
	/**
	 * 分页查询
	 * @param page
	 * @param gn
	 * @return
	 */
	@RequestMapping("findGNListForPage")
	@ResponseBody
	public Result findGNListForPage(Page page,GN gn) {
		return gnService.findGNListForPage(page,gn);
	}
	/**
	 * 修改
	 * @param gn
	 * @return
	 */
	@RequestMapping("updateGn")
	@ResponseBody
	public Result updateGn(GNCustom gn) {
		return gnService.updateGn(gn);
	}
	/**
	 * 根据所选菜单级别 ，查出所有上级菜单
	 * @param gn
	 * @return
	 */
	@RequestMapping("findGnListByPid")
	@ResponseBody
	public Result findGnListByPid(GNCustom gn) {
		return gnService.findGnListByPid(gn);
	}
	/**
	 * 保存
	 * @param gn
	 * @return
	 */
	@RequestMapping("saveGN")
	@ResponseBody
	public Result saveGN(GNCustom gn) {
		return gnService.saveGN(gn);
	}
	/**
	 * 根据主键，查询是否有子菜单
	 * @param gn
	 * @return
	 */
	@RequestMapping("findChildGNByKey")
	@ResponseBody
	public Result findChildGNByKey(GNCustom gn) {
		return gnService.findChildGNByKey(gn);
	}
	/**
	 * 删除
	 * @param gn
	 * @return
	 */
	@RequestMapping("deleteGNByKey")
	@ResponseBody
	public Result deleteGNByKey(GNCustom gn) {
		return gnService.deleteGNByKey(gn);
	}
	/**
	 * 根据主键，分页查询子菜单
	 * @param page
	 * @param gn
	 * @return
	 */
	@RequestMapping("findChildGNList")
	@ResponseBody
	public Result findChildGNList(Page page , GNCustom gn) {
		return gnService.findChildGNList(page,gn);
	}
	/**
	 * 查询菜单列表 ， 用于主页面左侧菜单展示
	 * @param gn
	 * @return
	 */
	@RequestMapping("findGNList")
	@ResponseBody
	public Result findGNList(GNCustom gn) {
		return gnService.findGNList(gn);
	}
	
	/**
	 * 获取一条菜单
	 * @param gn
	 * @return
	 */
	@RequestMapping("findGNOne")
	@ResponseBody
	public Result findGNOne(Page page ,GNCustom gn) {
		return gnService.findGNOne(page ,gn);
	}
}
