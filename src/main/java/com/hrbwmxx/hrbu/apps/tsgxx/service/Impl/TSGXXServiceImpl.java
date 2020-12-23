package com.hrbwmxx.hrbu.apps.tsgxx.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Result;

import com.hrbwmxx.hrbu.apps.tsgxx.dao.TSGXXMapper;
import com.hrbwmxx.hrbu.apps.tsgxx.model.TSGXX;
import com.hrbwmxx.hrbu.apps.tsgxx.service.ITSGXXService;
import com.hrbwmxx.hrbu.apps.tsgxx.vo.TSGXXCustom;
import com.hrbwmxx.hrbu.apps.tsgxx.vo.TSGXXVO;
@Service("TSGXXServiceImpl")
public class TSGXXServiceImpl implements ITSGXXService {
	@Autowired
	private TSGXXMapper tsgxxMapper;
	public Result gettsgxx(Map resmap) {
		 TSGXX tsgxx=new TSGXX();
	     TSGXXVO result = new TSGXXVO();
	     String dztm=resmap.get("dztm")+"";
	    
	     tsgxx.setDztm(dztm);
	    
		List<TSGXXCustom> tsgxxlist=tsgxxMapper.queryTSGXX(tsgxx);
		result.setRows(tsgxxlist);
		return result;
	}
}
