package com.hrbwmxx.framework.apidoc.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.apidoc.dao.ApidocMapper;
import com.hrbwmxx.framework.apidoc.model.Apidoc;
import com.hrbwmxx.framework.apidoc.vo.ApidocVO;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.model.ResultPage;
@Service
public class ApidocServiceImpl implements IApidocService {
	@Autowired
	private ApidocMapper apidocMapper;
	public Result queryApidocForPage(Page page, Apidoc apidoc) {
		ResultPage re = new ResultPage();
		try {
			List<Apidoc> apicleList = apidocMapper.queryApidocForPage(page,apidoc);
			int count = apidocMapper.queryApidocForCount(page,apidoc);
			re.setPageNo(page.getPageNo());
			re.setPageSize(page.getPageSize());
			re.setRows(apicleList);
			re.setTotalCount(count);
			if(apicleList.size() <= 0 || count <= 0){
				re.setErrcode("500");
				re.setErrmsg("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}
	/**
	 * 根据主键Id查询一条详细数据
	 */
	
	public Result queryApidocById(BigDecimal id) {
		ApidocVO adv = new ApidocVO();
		try {
			Apidoc ad = apidocMapper.queryApidocById(id);
			adv.setApidoc(ad);
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
		}
		return adv;
	}
	/**
	 * 保存数据，
	 * mysql中主键为自增主键，
	 * 不用前端给id，也不用设置Id，保存时主键会自动增加
	 */
	
	public Result insertApidocData(Apidoc apidoc) {
		ApidocVO adv = new ApidocVO();
		try {
			//查询Id
			BigDecimal id = apidocMapper.queryNextId();
			apidoc.setId(id);
			int count = apidocMapper.saveApidocData(apidoc);
			if(count <= 0){
				adv.setErrcode("500");
				adv.setErrmsg("操作失败");
			}
			adv.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
		}
		return adv;
	}
	/**
	 * 修改数据
	 */
	
	public Result updateApidocData(Apidoc apidoc) {
		ApidocVO adv = new ApidocVO();
		try {
			int count = apidocMapper.updateApidocData(apidoc);
			if(count <= 0){
				adv.setErrcode("500");
				adv.setErrmsg("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
		}
		return adv;
	}
	
	public Result deleteApidocDataById(BigDecimal id) {
		ResultEntity re = new ResultEntity();
		try {
			int count = apidocMapper.deleteApidocDataById(id);
			if(count <= 0){
				re.setErrcode("500");
				re.setErrmsg("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}
	
	public Result queryApidocList() {
		ResultEntity re = new ResultEntity();
		try {
			List<Apidoc> apiList = apidocMapper.queryApidocList();
			if(apiList.size() <= 0){
				re.setErrcode("500");
				re.setErrmsg("操作失败");
			}
			re.setList(apiList);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}
	/**
	 * 根据pid查询Api
	 */
	
	public Result queryApidocByPid(BigDecimal pid) {
		ResultEntity re = new ResultEntity();
		try {
			List<Apidoc> apiList = apidocMapper.queryApidocByPid(pid);
			re.setList(apiList);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}
}
