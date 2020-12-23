package com.webmos.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webmos.framework.service.IModelDMBService;
import com.webmos.framework.vo.ModelDMBVo;

@Controller
@RequestMapping("modelDMB")
public class ModelDMBController {
	
	@Autowired
	private IModelDMBService modelDMBService;
	
	
	@RequestMapping("/{dmbid}/query")
	@ResponseBody
	public ModelDMBVo getDmbJson(@PathVariable String dmbid) {
		if(dmbid!=null && !dmbid.equals("")){
			return modelDMBService.queryDmbList(dmbid);
		}else{
			return null;
		}
	}

}
