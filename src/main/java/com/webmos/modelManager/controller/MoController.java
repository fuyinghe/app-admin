package com.webmos.modelManager.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Result;
import com.webmos.modelManager.service.IMoService;

/**
 * 模块管理配置管理
 * @author GRMa
 */
@Controller
@RequestMapping("modelMo")
public class MoController extends AbstractModelController {

	@Qualifier("MoServiceImpl")
	@Autowired
	private IMoService moService;
	
	
	public MoController() {
		super.setModid("09dad5b5-f1d2-4bf7-894f-09bbd28b2894");
	}
	
	//删除方法改造
	//如果删除的模块有属性存在，则不允许删除
	@Override
	public Result deleteOneData(@RequestParam HashMap<String, String> ParamFiledMap) {
		return moService.deleteOneData(ParamFiledMap);
	}
	
	/**
	* @MethodName: copyMo 
	* @description : copy一个模块
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("copyMo")
	@ResponseBody
	public Result copyMo(@RequestParam String moid){
		return moService.copyMo(moid);
	}
	
	/**
	 * 根据模块id创建表
	 * @param moid
	 * @return
	 */
	@RequestMapping("createTable")
	@ResponseBody
	public Result createTable(@RequestParam String moid){
		return moService.createTable(moid);
	}
	
	
	

}
