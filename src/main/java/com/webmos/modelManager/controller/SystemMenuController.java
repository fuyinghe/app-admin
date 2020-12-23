package com.webmos.modelManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/**
 * @title : 模型配置管理器菜单功能接口
 * @author:  GRMa
 * @description：
 */
@Controller
@RequestMapping("modelSystemMenu")
public class SystemMenuController extends AbstractModelController {

	public SystemMenuController() {
		super.setModid("f7d879c0-7d80-4488-9956-2da57b0cbebf");
	}

	
}
