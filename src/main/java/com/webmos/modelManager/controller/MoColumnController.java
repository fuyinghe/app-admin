package com.webmos.modelManager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;
import com.webmos.modelManager.service.IMoItemService;
import com.webmos.modelManager.service.IMoService;
/**
 * 模块属性配置管理
 * @author GRMa
 */
@Controller
@RequestMapping("modelMoColumn")
public class MoColumnController extends AbstractModelController {
	
	@Qualifier("MoItemServiceImpl")
	@Autowired
	private IMoItemService moItemService;
	
	public MoColumnController() {
		super.setModid("66b590ec-781d-4465-91fe-44154175e7d3");
	}
	
	/**
	 * 增加字段
	 * @param queryParamFiledMap
	 */
	@RequestMapping("createMoColumn")
	@ResponseBody
	public Result createMoColumn(@RequestParam HashMap<String,String>  queryParamFiledMap){
		
		return moItemService.createColumn(queryParamFiledMap);
	}
	
	/**
	 * 粘贴属性
	 * @param queryParamFiledMap
	 */
	@RequestMapping("PasteColumns")
	@ResponseBody
	public Result PasteColumns(@RequestParam HashMap<String,String>  paramFiledMap){
		
		return moItemService.pasteColumns(paramFiledMap);
	}
	/**
	 * 批量删除
	 * @param queryParamFiledMap
	 */
	@RequestMapping("deleteColumns")
	@ResponseBody
	public Result deleteColumns(@RequestParam HashMap<String,String>  paramFiledMap){
		
		return moItemService.deleteColumns(paramFiledMap);
	}
	
	
	

}
