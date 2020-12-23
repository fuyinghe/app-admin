package com.hrbwmxx.hrbu.apps.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;
/*校园黄页全部信息，包括部门信息、科室信息，应用v_xyhy_all视图，增加ls,jb字段*/
@Controller
@RequestMapping("xyhyall")
public class XYHYAllController extends AbstractModelController {
   public XYHYAllController() {
	   super.setModid("595187b3-3637-4ff3-9da1-c29a80270fb3");
   }
}
