package com.hrbwmxx.hrbu.tstz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.TimeUtil;
import com.hrbwmxx.hrbu.tstz.dao.TstzMapper;
import com.hrbwmxx.hrbu.tstz.vo.BjVo;
import com.hrbwmxx.hrbu.tstz.vo.Cs;
import com.hrbwmxx.hrbu.tstz.vo.LsbVo;
import com.hrbwmxx.hrbu.tstz.vo.TsTz;
import com.hrbwmxx.hrbu.tstz.vo.TzVo;
import com.hrbwmxx.hrbu.tstz.vo.XsBj;
import com.hrbwmxx.hrbu.tstz.vo.XsXx;
import com.hrbwmxx.hrbu.tstz.vo.Xslsb;
import com.hrbwmxx.hrbu.tstz.vo.XxVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TstzServiceImpl implements ITstzService {
	@Autowired
	private TstzMapper tstzMapper;


	public Result queryBj(Page page) {
		BjVo result = new BjVo();
		List<XsBj> list = tstzMapper.queryBj(page);
		int zs = tstzMapper.queryCountBj(page);
		result.setTotalCount(zs);
		result.setRows(list);
		return result;
	}


//	@Override
//	public Result queryTsBjRy(TsTz obj) {
//		TzVo  result = new TzVo();
//		obj =  tstzMapper.queryTsBjRy(obj);
//		result.setObj(obj);
//		return result;
//	}


	public Result queryXszs(XsXx obj) {
		XxVo result = new XxVo();
		obj = tstzMapper.queryXszs(obj);
		result.setObj(obj);
		return result;
	}


	public Result saveXslsb(TsTz tstz,Xslsb obj,XsXx xsob) {
		LsbVo result = new LsbVo();
		String code="200";
		String msg ="已推送状态";
		
		//查询出推送通知信息
		List<TsTz> bjlist =tstzMapper.queryTsBjRy(tstz);
		
		for(int i=0;i<bjlist.size();i++) {
			String sfts = bjlist.get(i).getSfts().toString();
			if(sfts.equals("0")) {
				String bjdm = bjlist.get(i).getTzbj();
				String[] bj = bjdm.split(",");
				for(int j=0;j<bj.length;j++) {
					String bjcx = bj[j].toString();
					xsob.setBjdm(bjcx);
					List<XsXx> list = tstzMapper.queryXsxx(xsob);
					if(list.size()!=0) {
						for(int c=0;c<list.size();c++) {
							String xh = list.get(c).getXh();
							String xm = list.get(c).getXm();
							String tsid= tstz.getWid();
							obj.setXh(list.get(c).getXh());
							obj.setXm(list.get(c).getXm());
							obj.setTsid(tstz.getWid());
							if( null==xh || xh.equals("")) {
								result.setErrcode("300");
								result.setErrmsg("学号不能为空");
								return result;
							}else if(null==xm || xm.equals("")) {
								result.setErrcode("300");
								result.setErrmsg("姓名有空值");
								return result;
							}else if(null==tsid || tsid.equals("")) {
								result.setErrcode("300");
								result.setErrmsg("wid为空");
								return result;
							}
							tstzMapper.saveXslsb(obj);
						}
					}else {
						result.setErrcode("400");
						result.setErrmsg("没有人员数据");
						return result;
					}
				}
				tstzMapper.updateTstzzt(tstz);
			}else {
				result.setErrcode(code);
				result.setErrmsg(msg);
			}
		}
		return result;

	}


	public Result updateXslsbzt(Xslsb obj) {
		LsbVo result = new LsbVo();
		obj.setYdsj(TimeUtil.getTime());
		TsTz tstz = new TsTz();
		tstz.setWid(obj.getTsid());
		List<Xslsb> list = tstzMapper.queryXslsbzt(obj);
		if(list.size()!=0) {
		List<TsTz> tslist = tstzMapper.queryTsBjRy(tstz);
		for(int i=0;i<list.size();i++) {
			String zt = list.get(i).getZt().toString();
			if(zt.equals("0")) {
				tstzMapper.updateXslsbzt(obj);
				int ydrs = tslist.get(0).getYdrs();
				int xj = ydrs+1;
				tstz.setYdrs(xj);
				tstz.setWid(obj.getTsid());
				tstzMapper.updateYdrs(tstz);
				result.setErrcode("100");
			}
		}
	}else {
		result.setErrcode("200");
		result.setErrmsg("信息不正确！");
	}
		return result;
	}


	public Result queryYdrs(Xslsb obj) {
		LsbVo result = new LsbVo();
		obj = tstzMapper.queryYdrs(obj);
		result.setObj(obj);
		return result;
	}


	public Result updateTszt(TsTz obj) {
		TzVo result = new TzVo();
		int up=tstzMapper.updateTszt(obj);
		if(up<1) {
			result.setErrcode("200");
			result.setErrmsg("不是本发布人不能进行删除");
		}
		return result;
	}


	public Result queryYdxs(Xslsb obj) {
		LsbVo result = new LsbVo();
		List<Xslsb> list = tstzMapper.queryYdxs(obj);
		int zs = tstzMapper.queryYdZs(obj);
		result.setRows(list);
		result.setTotalCount(zs);
		return result;
	}


	public Result queryWdxs(Xslsb obj) {
		LsbVo result = new LsbVo();
		List<Xslsb> list =tstzMapper.queryWdxs(obj);
		Integer zs = tstzMapper.queryWdZs(obj);
		result.setRows(list);
		result.setTotalCount(zs);
		return result;
	}


	public Map<String, Object> queryCs(Xslsb obj) {
		LsbVo result = new LsbVo();
		Cs  cs = new Cs();
		Xslsb xl = new Xslsb();
		List list = new ArrayList();
		cs.setKey("1234");
		cs.setValue("321312");
		list.add(cs);
		cs.setKey("2222");
		cs.setValue("9898");
		list.add(cs);
		Map<String, Object> map= tstzMapper.queryCs(obj);
		map.put("rows", list);
		
		return map;
	}



}
