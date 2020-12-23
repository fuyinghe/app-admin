package com.hrbwmxx.hrbu.apps.apis.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.Result;
/**
 * 学校主站-哈院要闻查询控制类 
 * @author ZhangLiXia
 * @date 2018-03-14
 *
 */
@Controller
@RequestMapping("zqhyyw")
public class ZqHYYWController extends AbstractModelController {
	public ZqHYYWController() {
		super.setModid("09d8a885-8e2d-4db4-b535-37214a98bbb1");		
	}
	
}
