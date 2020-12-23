package com.hrbwmxx.system.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.Constant;
import com.hrbwmxx.framework.util.EhcacheUtil;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.system.dao.GNMapper;
import com.hrbwmxx.system.model.GN;
import com.hrbwmxx.system.model.Login;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.GNVo;


@Service
public class GNSerivceImpl implements IGNService {
	@Autowired
	private GNMapper gnMapper;
	@Autowired
	private HttpSession session;
	@Autowired
    private ExceptionUtil exceptionUtil;
	public Result findGNListForPage(Page page, GN gn) {
		GNVo gv = new GNVo();
		try {
			List<GNCustom> gvList = gnMapper.findGNListForPage(page,gn);
			int count = gnMapper.findGNListForCount(page,gn);
			gv.setRows(gvList);
			gv.setTotalCount(count);
			gv.setPageNo(page.getPageNo());
			gv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}

	public Result updateGn(GNCustom gn) {
		GNVo gv = new GNVo();
		try {
			if (gn.getpId() == null || gn.getpId() == "") {
				gn.setpId("-1");
			}
			int count = gnMapper.updateGn(gn);
			if(count <= 0) {
				gv.setErrcode("500");
				gv.setErrmsg("修改失败");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("修改失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}

	public Result findGnListByPid(GNCustom gn) {
		GNVo gv = new GNVo();
		try {
			int dj = Integer.parseInt(gn.getDj());
			if( dj > 1) {
				gn.setDj(String.valueOf(dj-1));
			}
			List<GNCustom> list = gnMapper.findGnListByPid(gn);
			gv.setpList(list);
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}

	public Result saveGN(GNCustom gn) {
		GNVo gv = new GNVo();
		try {
			gn.setGnId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+Constant.getRandom());
			if (gn.getpId() == null || gn.getpId().equals("")) {
				gn.setpId("-1");
			}
			int count = gnMapper.saveGN(gn);
			if(count <= 0) {
				gv.setErrcode("500");
				gv.setErrmsg("保存失败");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("保存失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}

	public Result findChildGNByKey(GNCustom gn) {
		GNVo gv = new GNVo();
		try {
			List<GNCustom> childList = gnMapper.findChildGNByKey(gn);
			if(childList.size() > 0) {
				gv.setErrcode("0");
				gv.setErrmsg("下属有子菜单");
				
			}
			
			gv.setRows(childList);
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}

	public Result deleteGNByKey(GNCustom gn) {
		GNVo gv = new GNVo();
		try {
			List<GNCustom> childList = gnMapper.findChildGNByKey(gn);
			if(childList.size() > 0) {
				gv.setErrcode("0");
				gv.setErrmsg("下属有子菜单，不能删除");
				return gv;
			}
			int count = gnMapper.deleteGNByKey(gn);
			if(count <= 0) {
				gv.setErrcode("500");
				gv.setErrmsg("删除失败");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("删除失败");
		}
		return gv;
	}

	public Result findChildGNList(Page page, GNCustom gn) {
		GNVo gv = new GNVo();
		try {
			List<GNCustom> childList = gnMapper.findChildGNListFroPage(page,gn);
			int totalCount = gnMapper.findChildGNByKeyForCount(page,gn);
			gv.setRows(childList);
			gv.setTotalCount(totalCount);
			gv.setPageSize(page.getPageSize());
			gv.setPageNo(page.getPageNo());
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("删除失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}

	public Result findGNOne(Page page, GN gn) {
		
		GNVo gv = new GNVo();
		try {
			List<GNCustom> gvList = gnMapper.findGNOne(page,gn);
			int count = gnMapper.findGNListForCount(page,gn);
			gv.setRows(gvList);
			gv.setTotalCount(count);
			gv.setPageNo(page.getPageNo());
			gv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}
	
	public Result findGNList(GNCustom gn) {
		GNVo gv = new GNVo();
		Login loginUser = new Login();
		//获取登录的用户
		loginUser = (Login) session.getAttribute("LoginUserSession");
		//获取用户ID,并放入参数中,查询菜单时可根据用户ID进行查询
		String userId = loginUser.getUserId();
		gn.setUserId(userId);
		
		try {
			//查出该角色所有的二级菜单
			List<GNCustom> gnList =  gnMapper.findGNList(gn);
			//根据二级菜单，查出一级菜单
			List<GNCustom> firstList = gnMapper.findFirstGNList(gn);
			firstList.addAll(gnList);
			gv.setpList(firstList);
		} catch (Exception e) {
			e.printStackTrace();
			gv.setErrcode("500");
			gv.setErrmsg("根据角色查询菜单失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(gv.getErrcode(),gv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return gv;
	}
	
	

}
