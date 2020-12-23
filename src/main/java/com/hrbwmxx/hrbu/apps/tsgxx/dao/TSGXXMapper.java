package com.hrbwmxx.hrbu.apps.tsgxx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Result;

import com.hrbwmxx.hrbu.apps.tsgxx.model.TSGXX;
import com.hrbwmxx.hrbu.apps.tsgxx.vo.TSGXXCustom;




public interface TSGXXMapper {
	
	public List<TSGXXCustom> queryTSGXX(@Param("obj") TSGXX obj);

	
	
	
	
}
