package com.hrbwmxx.system.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.system.model.JSGN;

public class JSGNVo extends ResultEntity{
	
	private JSGN jsgn;
	private List<JSGNCustom> jsgnList;
	public JSGN getJsgn() {
		return jsgn;
	}
	public void setJsgn(JSGN jsgn) {
		this.jsgn = jsgn;
	}
	public List<JSGNCustom> getJsgnList() {
		return jsgnList;
	}
	public void setJsgnList(List<JSGNCustom> jsgnList) {
		this.jsgnList = jsgnList;
	}
}
