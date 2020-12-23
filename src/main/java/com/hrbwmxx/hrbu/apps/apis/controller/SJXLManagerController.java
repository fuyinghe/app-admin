package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*事件校历（管理）controller*/
@Controller
@RequestMapping("sjxlgl")
public class SJXLManagerController extends AbstractModelController {
   public SJXLManagerController() {
	   super.setModid("04300da9-d502-4e1d-89a3-124c72a869c9");
   }
}
