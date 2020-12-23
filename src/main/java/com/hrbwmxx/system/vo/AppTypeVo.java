package com.hrbwmxx.system.vo;

import com.hrbwmxx.framework.model.ResultPage;

/**
 * app类型管理
 * @ClassName:  AppTypeVo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @date:   2018年10月18日 
 * @author: CHIUCLOUD(云)    
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */
public class AppTypeVo  extends ResultPage{
	
	private AppTypeCustom custom ;

	public AppTypeCustom getCustom() {
		return custom;
	}

	public void setCustom(AppTypeCustom custom) {
		this.custom = custom;
	}
}
