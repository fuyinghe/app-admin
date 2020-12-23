package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*校历查询Controller*/
@Controller
@RequestMapping("xlcx")
public class XLQueryController extends AbstractModelController {
   public XLQueryController(){
	   super.setModid("efbe3e9b-9aad-4779-b9d0-9e7fbb5cfc1b");
   }
}
