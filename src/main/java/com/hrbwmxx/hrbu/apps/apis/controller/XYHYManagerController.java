package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*校园黄页管理controller*/
@Controller
@RequestMapping("xyhygl")
public class XYHYManagerController extends AbstractModelController {
	public XYHYManagerController(){
   	 super.setModid("13d7f144-073e-43b7-9760-9ebc5712d8db");
    }
}
