package com.hrbwmxx.hrbu.jgtstz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.util.TimeUtil;
import com.hrbwmxx.hrbu.jgtstz.dao.JgtstzMapper;
import com.hrbwmxx.hrbu.jgtstz.vo.BmVo;
import com.hrbwmxx.hrbu.jgtstz.vo.Jgbm;
import com.hrbwmxx.hrbu.jgtstz.vo.Jglsb;
import com.hrbwmxx.hrbu.jgtstz.vo.Jgxx;
import com.hrbwmxx.hrbu.jgtstz.vo.LsbVo;
import com.hrbwmxx.hrbu.jgtstz.vo.TsTz;
import com.hrbwmxx.hrbu.jgtstz.vo.TzVo;
import com.webmos.framework.model.Result;
@Service
public class JgtstzServiceImpl implements IJgtstzService {
	@Autowired
	private JgtstzMapper jgtstzMapper;

	public Result queryJgbm() {
		BmVo bv = new BmVo();
		List<Jgbm> list = jgtstzMapper.queryJgbm();
		bv.setRows(list);
		bv.setErrcode("0");
		return  bv;
	}

	public Result saveJglsb(TsTz tstz) {
		LsbVo result = new LsbVo();
		List<TsTz> bjlist =jgtstzMapper.queryTsbmdm(tstz);
		String code="200";
		String msg ="已推送状态";
		for(int i=0;i<bjlist.size();i++) {
			String sfts = bjlist.get(i).getSfts().toString();
			if(sfts.equals("0")) {
				String bjdm = bjlist.get(i).getTzbj();
				String[] bj = bjdm.split(",");
				for(int j=0;j<bj.length;j++) {
					String bjcx = bj[j].toString();
					Jgxx jg = new Jgxx();
					jg.setBmdm(bjcx);
					List<Jgxx> list = jgtstzMapper.queryJgxx(jg);
					if(list.size()!=0) {
						for(int c=0;c<list.size();c++) {
							Jglsb obj = new Jglsb();
							String xh = list.get(c).getJgdm();
							String xm = list.get(c).getXm();
							String tsid= tstz.getWid();
							obj.setJgdm(list.get(c).getJgdm());
							obj.setXm(list.get(c).getXm());
							obj.setTsid(tstz.getWid());
							obj.setYdsj(TimeUtil.getTime());
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
							jgtstzMapper.saveJglsb(obj);
							result.setErrcode("0");
						}
					}else {
						result.setErrcode("400");
						result.setErrmsg("没有人员数据");
						return result;
					}
				}
				jgtstzMapper.updateTstzzt(tstz);
			}else {
				result.setErrcode(code);
				result.setErrmsg(msg);
			}
		}
		return result;
	}

	public Result queryYdjg(Jglsb obj) {
		LsbVo  lv = new LsbVo();
		String tsid = obj.getTsid();
		if(null==tsid || tsid.equals("")) {
			lv.setErrcode("200");
			lv.setErrmsg("tsid不能为空");
		}else {
			List<Jglsb> list = jgtstzMapper.queryYdjg(obj);
			int zs = jgtstzMapper.queryYdZs(obj);
			lv.setRows(list);
			lv.setTotalCount(zs);
			lv.setErrcode("0");
		}
		return lv;
	}

	public Result queryWdjg(Jglsb obj) {
		LsbVo  lv = new LsbVo();
		String tsid = obj.getTsid();
		if(null==tsid || tsid.equals("")) {
			lv.setErrcode("200");
			lv.setErrmsg("tsid不能为空");
		}else {
			List<Jglsb> list = jgtstzMapper.queryWdjg(obj);
			int zs = jgtstzMapper.queryWdZs(obj);
			lv.setRows(list);
			lv.setTotalCount(zs);
			lv.setErrcode("0");
		}
		return lv;
	}

	public Result updateTszt(TsTz obj) {
		TzVo result = new TzVo();
		int up=jgtstzMapper.updateTszt(obj);
		result.setErrcode("0");
		if(up<1) {
			result.setErrcode("200");
			result.setErrmsg("不是本发布人不能进行删除");
		}
		return result;
	}
}
