package com.hrbwmxx.hrbu.apps.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.admin.service.YYGLService;
import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.Page;
import com.webmos.framework.vo.DataModelVo;

/*黄页部门信息controller*/
@Controller
@RequestMapping("yygl")
public class YYGLController extends AbstractModelController {

	@Qualifier("YYGLServiceImpl")
	@Autowired
	YYGLService yyglService;

	public YYGLController() {
		super.setModid("99e5c9a6-1fd3-4e7f-8fbe-0ddb87124831");
	}

	/**
	 * 
	 */
	@Override
	public com.webmos.framework.model.Result queryListData(Page page,
			HashMap<String, String> queryParamFiledMap) {
		DataModelVo temp = (DataModelVo) super.queryListData(page,
				queryParamFiledMap);
		return yyglService.listData(temp);
	}

	/**
	 * 下线
	 * 
	 * @param resmap
	 * @return
	 */
	@RequestMapping("chstate")
	@ResponseBody
	public Result chAppState(@RequestParam Map<?, ?> resmap) {
		String appId = (String) resmap.get("appId");
		int state = Integer.parseInt((String) resmap.get("state"));
		return (Result) yyglService.changeAppState(appId, state);
	}

}
