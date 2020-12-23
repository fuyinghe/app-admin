package com.hrbwmxx.framework.fj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.fj.dao.FJCSMapper;
import com.hrbwmxx.framework.fj.model.FJCS;
import com.hrbwmxx.framework.fj.vo.FJVo;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.Constant;

@Service
public class FJCSServiceImpl implements IFJCSService{
	@Autowired
	private FJCSMapper fjcsMapper;

	
	public Result queryFJCSListPage(Page page, FJCS obj) {
		FJVo result =new FJVo();
		List<FJCS> list=fjcsMapper.queryFJCSListPage(page,obj);
		int count =fjcsMapper.queryFJCSListPageCount(page,obj);
		result.setRows(list);
		result.setTotalCount(count);
		return result;
	}

	
	public Result saveFJCS(FJCS obj) {
		FJVo result =new FJVo();
		obj.setFjcsId(Constant.getUUID());
		fjcsMapper.saveFJCS(obj);
		return result;
	}

	
	public Result updateFJCS(FJCS obj) {
		FJVo result =new FJVo();
		fjcsMapper.updateFJCS(obj);
		return result;
	}
}
