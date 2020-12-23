package com.hrbwmxx.system.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.util.Constant;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.framework.util.MD5;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.system.dao.YHMapper;
import com.hrbwmxx.system.model.Login;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.YHCustom;
import com.hrbwmxx.system.vo.YHVo;
/**
 * 
* @title: YHServiceImpl 
* @description：TODO
* @author： 刘威巍
* @date： 2018年1月13日 下午12:00:15
 */
@Service
public class YHServiceImpl implements IYHService{
	@Autowired
	private YHMapper yhMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	/**
	 * 
	* @MethodName: queryYHListPage 
	* @description : 用户列表分页
	* @author：刘威巍 
	* @date： 2018年1月12日 上午10:41:45
	* @param obj
	* @return Result
	 */
	public Result queryYHListPage(Page page, YHCustom obj) {
		// TODO Auto-generated method stub
		YHVo result = new YHVo();
		List<YHCustom> list =  yhMapper.queryYHListPage(page,obj);
		int totalCount = yhMapper.queryYHListPageCount(page,obj);
		result.setTotalCount(totalCount);
		result.setRows(list);
		return result;
	}
	/**
	 * 
	* @MethodName: queryYHByField 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月13日 下午6:10:18
	* @param obj
	* @return Result
	 */
	public Result queryYHByField(YHCustom obj) {
		// TODO Auto-generated method stub
		YHVo result = new YHVo();
		obj = yhMapper.queryYHByField(obj);
		result.setObj(obj);
		return result;
	}
	/**
	 * 
	* @MethodName: saveYH 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月13日 下午6:21:39
	* @param obj
	* @return Result
	 */
	public Result saveYH(YHCustom obj){
		YHVo result = new YHVo();
		try {
			//System.out.println("kkk---"+obj.getXm()+obj.getXh()+obj.getZt());
			//增加一个默认密码--111111
			obj.setPassWord(MD5.toMD5("111111"));
			obj.setYhId(Constant.getUUID());
			yhMapper.saveYH(obj);
		} catch (Exception e) {
			result.setErrcode("500");
			result.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		
		return result;
	}
	/**
	 * 
	* @MethodName: updateYH 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年1月14日 下午8:39:31
	* @param obj
	* @return Result
	 */
	public Result updateYH(YHCustom obj) {
		// TODO Auto-generated method stub
		YHVo result = new YHVo();
		//obj.setPassWord(PasswordEncoder.cryptoPassword(yhId, obj.getPassWord()));
		yhMapper.updateYH(obj);
		return result;
	}
	//删除用户时，需要删除所有域用户有关联的中间表 暂时没写中间表，只删除主表
	public Result deleteYH(YHCustom obj) {
		YHVo yv = new YHVo();
		try {
			int count = yhMapper.deleteYH(obj);
			// 根据用户ID 删除用户角色中间表
			yhMapper.deleteYHJS(obj);
			if(count <= 0) {
				yv.setErrcode("500");
				yv.setErrmsg("删除操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			yv.setErrcode("500");
			yv.setErrmsg("删除操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(yv.getErrcode(),yv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return yv;
	}
	/**
	 *  根据用户ID 分页查询已选功能
	 */
	public Result findSelectedMenuByYhId(Page page, YHCustom yh) {
		YHVo yv = new YHVo();
		try {
			List<GNCustom> gnList = yhMapper.findSelectedMenuByYhIdForPage(page,yh);
			int count = yhMapper.findSelectedMenuByYhIdForCount(page,yh);
			yv.setRows(gnList);
			yv.setTotalCount(count);
			yv.setPageNo(page.getPageNo());
			yv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			yv.setErrcode("500");
			yv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(yv.getErrcode(),yv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return yv;
	}
	/**
	 * 根据用户ID 分页查询未选功能
	 */
	public Result findUnSelectMenuByYhId(Page page, YHCustom yh) {
		YHVo yv = new YHVo();
		try {
			List<GNCustom> gnList = yhMapper.findUnSelectMenuByYhIdForPage(page,yh);
			int count = yhMapper.findUnSelectMenuByYhIdForCount(page,yh);
			yv.setRows(gnList);
			yv.setTotalCount(count);
			yv.setPageNo(page.getPageNo());
			yv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			yv.setErrcode("500");
			yv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(yv.getErrcode(),yv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return yv;
	}
	public Result addUnSelectMenu(GNCustom gn, YHCustom yh) {
		YHVo yv = new YHVo();
		try {
			int count = yhMapper.addUnSelectMenu(gn,yh);
			if (count <= 0) {
				yv.setErrcode("500");
				yv.setErrmsg("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			yv.setErrcode("500");
			yv.setErrmsg("添加失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(yv.getErrcode(),yv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return yv;
	}
	public Result deleteSelectMenu(GNCustom gn, YHCustom yh) {
		YHVo yv = new YHVo();
		try {
			int count = yhMapper.deleteSelectMenu(gn ,yh);
			if (count <= 0) {
				yv.setErrcode("500");
				yv.setErrmsg("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			yv.setErrcode("500");
			yv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(yv.getErrcode(),yv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return yv;
	}
	
	public Result updatePassWord(String yhId,String oldPass, String newPass) {
		Result result = new ResultEntity();
		int updateZt= yhMapper.updatePassWord(yhId, oldPass, newPass);
		if(updateZt==1) {
			result.setErrcode("0");
			result.setErrmsg("密码更新成功");
		}else{
			result.setErrcode("201");
			result.setErrmsg("密码更新失败");	
		}
		return result;
	}
	//重置密码
	@Override
	public Result updatePassword(HashMap<String, Object> resmap,HttpServletRequest request) {

		Result result = new ResultEntity();
		Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
		if(sessionLogin==null) {
			result.setErrcode("403");
			result.setErrmsg("系统监测到用户未登录");	
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			  return result;
		} 
		String oldpwd = resmap.get("passWord")+"";
		String newpwd = resmap.get("newPasword")+"";
		String newpwd2 = resmap.get("newPasword2")+"";
		String userId=sessionLogin.getUserId();
		//判断参数完整性
		if(oldpwd==null || "".equals(oldpwd) 
				|| newpwd==null || "".equals(newpwd)
				|| newpwd2==null && "".equals(newpwd2)) {
			result.setErrcode("500");
			result.setErrmsg("参数不完整，无法进行密码变更操作！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return result;
		}
		//判断2次输入密码是否相同
		if(!newpwd.equals(newpwd2)) {
			result.setErrcode("500");
			result.setErrmsg("新密码两次输入不同，请重新输入！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return result;
		}
		
		//判断原始密码与新密码是否相同
		if(newpwd.equals(oldpwd)) {
			result.setErrcode("500");
			result.setErrmsg("原始密码与新密码不可相同！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return result;
		}
		String pass = MD5.toMD5(oldpwd);
		String realPass = yhMapper.VerifitPass(userId);
		//判断原始密码是否正确
		if(!pass.equals(realPass)) {
			result.setErrcode("500");
			result.setErrmsg("原始密码错误！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return result;
		}
		//修改密码
		try {
			String updatepass= MD5.toMD5(newpwd);
			int a = yhMapper.UpdatePassWord(userId, updatepass);
			if(a==1) {
				result.setErrcode("0");
				result.setErrmsg("密码修改成功,下次登录请使用新密码！");
				return result;
			}else {
				result.setErrcode("500");
				result.setErrmsg("密码修改失败！");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("密码修改失败Exception！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return result;
		}
	
	}
	@Override
	public Result resetPassword(HashMap<String, Object> resmap) {
		Result result = new ResultEntity();
		try {
			String userId=resmap.get("userId")+"";
			String updatepass= MD5.toMD5("111111");
			int a = yhMapper.UpdatePassWord(userId, updatepass);
			if(a==1) {
				result.setErrcode("0");
				result.setErrmsg("密码重置成功,下次登录请使用默认密码！");
				return result;
			}else {
				result.setErrcode("500");
				result.setErrmsg("密码重置失败！");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("密码重置失败Exception！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			return result;
		}
	}
	
	

}
