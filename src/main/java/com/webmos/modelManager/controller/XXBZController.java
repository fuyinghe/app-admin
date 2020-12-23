package com.webmos.modelManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webmos.framework.controller.AbstractModelController;

/**
 * @title : 模型使用的信息标准配置
 * @author:  Gmar
 * @description：
 */
@Controller
@RequestMapping("xxbzmanager")
public class XXBZController extends AbstractModelController {

	public XXBZController() {
		super.setModid("b569747d-b710-4a42-b092-5f6c872ecaea");
	}

	
}
