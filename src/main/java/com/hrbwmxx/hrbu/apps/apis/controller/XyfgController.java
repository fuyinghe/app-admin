package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 校园风光查询控制类
 * @author ZhangLiXia
 * @date 2018-03-15
 *
 */
@Controller
@RequestMapping("xyfg")
public class XyfgController extends AbstractModelController {
	public XyfgController() {
		super.setModid("bd8a8601-6981-4d6f-b337-8f1db9db35cb");		
	}
}
