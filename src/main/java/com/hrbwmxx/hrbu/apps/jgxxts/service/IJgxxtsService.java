package com.hrbwmxx.hrbu.apps.jgxxts.service;

import java.util.Map;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.Jglsb;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.TsTz;

public interface IJgxxtsService {
	
	/**
	 * 返回带分页的通知公告
	 * @param resmap
	 * @return
	 */
	public Result getJgxxtsPage(com.hrbwmxx.framework.model.Page page,Map resmap,String jgdm);
	/**
	 * 根据教工代码和wid获取视图推送通知信息
	 * @param resmap
	 * @return
	 */
	public Result queryJggltzts(TsTz obj);
	
	public Result updateJglsbzt(Jglsb obj);

}
