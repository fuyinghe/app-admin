package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/**
 * 学校主站-社会服务查询控制类 
 * @author ZhangLiXia
 * @date 2018-03-14
 *
 */
@Controller
@RequestMapping("zqshfw")
public class ZqSHFWController extends AbstractModelController {
	public ZqSHFWController() {
		super.setModid("e020606b-76bc-4a15-bae6-5a850eb1efe3");		
	}
}
