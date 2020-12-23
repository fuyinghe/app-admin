package com.hrbwmxx.hrbu.apps.jgxxts.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.framework.util.TimeUtil;
import com.hrbwmxx.hrbu.apps.jgxxts.dao.JgxxtsMapper;
import com.hrbwmxx.hrbu.apps.jgxxts.service.IJgxxtsService;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.Jglsb;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.LsbVo;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.TSTZCustom;
import com.hrbwmxx.hrbu.apps.jgxxts.vo.TsTz;
import com.hrbwmxx.hrbu.log.model.Log;

@Service("JgxxtsServiceImpl")
public class JgxxtsServiceImpl implements IJgxxtsService {
	@Autowired
	private JgxxtsMapper jgxxtsMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	public Result getJgxxtsPage(Page page, Map resmap, String jgdm) {
		ResultPage result = new ResultPage();
		try {
			List<TSTZCustom> xxtsList = jgxxtsMapper.queryJgxxtsPage(page,jgdm);
			int count = jgxxtsMapper.queryJgxxtsPageCount(page,jgdm);
			result.setPageNo(page.getPageNo());
			result.setPageSize(page.getPageSize());
			result.setRows(xxtsList);
			result.setTotalCount(count);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}

		return result;
	}

	public Result queryJggltzts(TsTz obj) {
		ResultPage result = new ResultPage();
		String jgdm = obj.getJgdm();
		if(jgdm.equals("")) {
			result.setErrcode("200");
			result.setErrmsg("学号不能为空");
		}else {
			List<TSTZCustom> list = jgxxtsMapper.queryJggltzts(obj);
			result.setRows(list);
		}
		return result;
	}

	public Result updateJglsbzt(Jglsb obj) {
		LsbVo result = new LsbVo();
		obj.setYdsj(TimeUtil.getTime());
		TsTz tstz = new TsTz();
		tstz.setWid(obj.getTsid());
		List<Jglsb> list = jgxxtsMapper.queryJglsbzt(obj);
		if(list.size()!=0) {
			List<TsTz> tslist = jgxxtsMapper.queryTsBjRy(tstz);
			for(int i=0;i<list.size();i++) {
				String zt = list.get(i).getZt().toString();
				if(zt.equals("0")) {
					jgxxtsMapper.updateJglsbzt(obj);
					int ydrs = tslist.get(0).getYdrs();
					int xj = ydrs+1;
					tstz.setYdrs(xj);
					tstz.setWid(obj.getTsid());
					jgxxtsMapper.updateYdrs(tstz);
					result.setErrcode("100");
				}
			}
		}else {
			result.setErrcode("200");
			result.setErrmsg("信息不正确！");
		}
		return result;
	}

}
