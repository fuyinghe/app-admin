package com.hrbwmxx.system.vo;

import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.system.model.Login;

public class LoginVo extends ResultEntity {

	private Login obj;

	public Login getObj() {
		return obj;
	}

	public void setObj(Login obj) {
		this.obj = obj;
	}
}
