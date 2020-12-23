package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*校园地图controller*/
@Controller
@RequestMapping("xydt")
public class XYDTController extends AbstractModelController {
  public XYDTController(){
	  super.setModid("6b0e2eb2-3577-440f-854c-524f2c523cc9");
  }
}
