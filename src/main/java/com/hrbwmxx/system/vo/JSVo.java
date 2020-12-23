package com.hrbwmxx.system.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultPage;

public class JSVo extends ResultPage{
	//返回对象
	private JSCustom obj;
	//角色列表
	private List<JSCustom> jsList;
	public JSCustom getObj() {
		return obj;
	}

	public void setObj(JSCustom obj) {
		this.obj = obj;
	}

	public List<JSCustom> getJsList() {
		return jsList;
	}

	public void setJsList(List<JSCustom> jsList) {
		this.jsList = jsList;
	}
	
}
