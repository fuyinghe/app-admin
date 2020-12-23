package com.hrbwmxx.hrbu.apps.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;

/**
 * 教职工管理
 * 
 * @author hechunyang
 * @date 2018年5月2日
 * @remark TODO
 */
@Controller
@RequestMapping("smyh")
public class SMYHController extends AbstractModelController {
	public SMYHController() {
		super.setModid("c4aab5b9-6520-437a-8f1e-3be1a4600c04");
	}
}