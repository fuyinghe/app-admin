package com.webmos.modelManager.controller;

import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 模型系统，子系统管理
 */
import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;

/**
 * 模型系统分类管理
 * @author GRMa
 */
@Controller
@RequestMapping("modelSystem")
public class SystemController extends AbstractModelController {
	
	public SystemController() {
		super.setModid("07cb1092-7097-4222-87ce-998b4ee1f2d2");
	}
	
	//屏蔽删除方法
	@Override
	public Result deleteOneData(HashMap<String, String> queryParamFiledMap) {
		Result result = new ResultEntity();
		result.setErrcode("301");
		result.setErrmsg("无权访问");
		return result;
	}
	
	
	
	

}
