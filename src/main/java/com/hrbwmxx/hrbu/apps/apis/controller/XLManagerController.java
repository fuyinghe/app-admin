package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*校历管理Controller*/
@Controller
@RequestMapping("xlgl")
public class XLManagerController extends AbstractModelController {
  public XLManagerController(){
	  super.setModid("aedb234a-382b-40c6-9a9d-9dfa161d4c72");
  }
}
