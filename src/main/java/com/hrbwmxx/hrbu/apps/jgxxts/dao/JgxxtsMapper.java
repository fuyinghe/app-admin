package com.hrbwmxx.hrbu.apps.jgxxts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.Jglsb;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.TSTZCustom;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.TsTz;

public interface JgxxtsMapper {
	
	//返回消息信息
	public List<TSTZCustom> queryJgxxtsPage(@Param("page") Page page,@Param("jgdm") String jgdm);
	//返回消息总数目
	public int queryJgxxtsPageCount(@Param("page") Page page,@Param("jgdm") String jgdm);
	//根据教工代码和wid获取视图推送通知信息
	public List<TSTZCustom> queryJggltzts(@Param("obj") TsTz obj);
	
	List<TsTz> queryTsBjRy(@Param("obj")TsTz obj);
	void updateJglsbzt(@Param("obj")  Jglsb obj);
	List<Jglsb> queryJglsbzt(@Param("obj")  Jglsb obj);
	void updateYdrs(@Param("obj")TsTz obj);

}
