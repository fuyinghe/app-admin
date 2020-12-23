package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*校园黄页查询controller*/
@Controller
@RequestMapping("xyhycx")
public class XYHYQueryController extends AbstractModelController {
     public XYHYQueryController(){
    	 super.setModid("dd6c7f37-d348-4a03-978c-cd1b023633b8");
     }
}
