package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*黄页部门信息controller*/
@Controller
@RequestMapping("bmxx")
public class BMXXController extends AbstractModelController {
	public BMXXController(){
		   super.setModid("1d38886a-b5a0-43fc-a6a6-df3d1b5f7144");
	   }
}
