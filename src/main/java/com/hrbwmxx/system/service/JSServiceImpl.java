package com.hrbwmxx.system.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.Constant;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.framework.util.TimeUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.system.dao.JSGNMapper;
import com.hrbwmxx.system.dao.JSMapper;
import com.hrbwmxx.system.model.JS;
import com.hrbwmxx.system.model.JSGN;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.JSCustom;
import com.hrbwmxx.system.vo.JSGNCustom;
import com.hrbwmxx.system.vo.JSVo;
import com.hrbwmxx.system.vo.YHCustom;
 

/**
 * 
* @title: JSServiceImpl 
* @description：角色接口实现类
* @author： lijingyu
* @date： 2018年1月12日 上午10:37:05
 */
@Service
public class JSServiceImpl implements IJSService {
	@Autowired
	private JSMapper jsMapper;
	@Autowired
	private JSGNMapper jsgnMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	/**
	 * 
	* @MethodName: queryJSListPage 
	* @description : 角色列表分页
	* @author：lijingyu
	* @date： 2018年1月12日 上午10:41:45
	* @param obj
	* @return Result
	 */
	public Result queryJSListPage(Page page,JSCustom obj) {
		JSVo result=new JSVo();
		List<JSCustom> list = jsMapper.queryJSListPage(page,obj);
		int totalCount=jsMapper.queryJSListPageCount(page,obj);
		result.setTotalCount(totalCount);
		result.setRows(list);
		return result;
	}
	 
	public Result saveJS(JSCustom obj) {
		JSVo result=new JSVo();
		obj.setJsId(Constant.getUUID());
		jsMapper.saveJS(obj);
		return result;
	}

	 
	public Result updateJS(JSCustom obj) {
		JSVo result=new JSVo();
		jsMapper.updateJS(obj);
		return result;
	}

	/**
	 * 
	* @MethodName: queryJSList 
	* @description : 角色列表（不含分页）
	* @author：lijingyu
	* @date： 2018年1月12日 上午10:41:45
	* @param obj
	* @return Result
	 */
	public Result queryJSList(JSCustom obj) {
		JSVo result=new JSVo();
		obj.setZt(Constant.SYS_STATE_1+"");
		List<JSCustom> list=jsMapper.queryJSList(obj);
		result.setJsList(list);
		return result;
	}

 
	public Result deleteJs(JSCustom obj) {
		//定义返回参数
		JSVo result=new JSVo();
		//查询角色是否被使用
		JSGN jsgn=new JSGN();
		jsgn.setJsId(obj.getJsId());
		List<JSGNCustom>  gnList = jsgnMapper.queryJSGNList(jsgn);
		if(gnList!=null &&gnList.size()>0){
			result.setErrcode("500");
			result.setErrmsg("当前角色已经被使用，操作失败！");
			return result;
		}
		//执行数据库操作
		obj.setZt(Constant.SYS_STATE_0+"");
		jsMapper.updateJS(obj);
		return result;
	}
	/**
	 * 通过用户ID 查出该用户的角色信息
	 */
	public Result findRoleByUserId(Page page,YHCustom yh) {
		JSVo jv=new JSVo();
		try {
			List<JSCustom> jsList = jsMapper.findJsListForPage(page,yh);
			int count = jsMapper.findJsListForCount(page,yh);
			jv.setRows(jsList);
			jv.setTotalCount(count);
			jv.setPageNo(page.getPageNo());
			jv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}
	/**
	 *  通过用户ID 查出该用户已选的角色信息
	 */
	public Result findUnselectRoleListByyhId(Page page, YHCustom yh) {
		JSVo jv=new JSVo();
		try {
			List<JSCustom> jsList = jsMapper.findUnselectRoleListByyhIdForPage(page,yh);
			int count = jsMapper.findUnselectRoleListByyhIdForCount(page,yh);
			jv.setRows(jsList);
			jv.setTotalCount(count);
			jv.setPageNo(page.getPageNo());
			jv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}
	/**
	 * 给用户添加未选角色
	 */
	public Result addUnSelectRole(YHCustom yh, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			int count = jsMapper.addUnSelectRole(yh,js);
			if (count <= 0) {
				jv.setErrcode("500");
				jv.setErrmsg("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}
	/**
	 * 删除用户已选角色
	 */
	public Result deleteSelectedRole(YHCustom yh, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			int count = jsMapper.deleteSelectedRole(yh ,js);
			if (count <= 0) {
				jv.setErrcode("500");
				jv.setErrmsg("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}
	/**
	 * 根据角色id 查出拥有该角色的用户
	 */
	public Result findSelectdeYhByJsId(Page page, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			List<YHCustom> yhList = jsMapper.findSelectdeYhByJsIdForPage(page,js);
			int count = jsMapper.findSelectdeYhByJsIdForCount(page,js);
			jv.setRows(yhList);
			jv.setTotalCount(count);
			jv.setPageNo(page.getPageNo());
			jv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}
	/**
	 * 根据角色id 查出未拥有该角色的用户
	 */
	public Result findUnseleteYhByJsId(Page page, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			List<YHCustom> yhList = jsMapper.findUnseleteYhByJsIdForPage(page,js);
			int count = jsMapper.findUnseleteYhByJsIdForCount(page,js);
			jv.setRows(yhList);
			jv.setTotalCount(count);
			jv.setPageNo(page.getPageNo());
			jv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}
	/**
	 *  根据角色ID 查出已选菜单
	 */
	public Result findSelectGNByJsId(Page page, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			List<GNCustom> gnList = jsMapper.findSelectGNByJsIdForPage(page,js);
			int count = jsMapper.findSelectGNByJsIdForCount(page,js);
			jv.setRows(gnList);
			jv.setTotalCount(count);
			jv.setPageNo(page.getPageNo());
			jv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}

	public Result findUnSelectGNByJsId(Page page, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			List<GNCustom> gnList = jsMapper.findUnSelectGNByJsIdForPage(page,js);
			int count = jsMapper.findUnSelectGNByJsIdForCount(page,js);
			jv.setRows(gnList);
			jv.setTotalCount(count);
			jv.setPageNo(page.getPageNo());
			jv.setPageSize(page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}

	public Result addUnSelectMenu(GNCustom gn,JSCustom js) {
		JSVo jv=new JSVo();
		try {
			int count = jsMapper.addUnSelectMenu(gn,js);
			if (count <= 0) {
				jv.setErrcode("500");
				jv.setErrmsg("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("添加失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}

	public Result deleteSelectMenu(GNCustom gn, JSCustom js) {
		JSVo jv=new JSVo();
		try {
			int count = jsMapper.deleteSelectMenu(gn ,js);
			if (count <= 0) {
				jv.setErrcode("500");
				jv.setErrmsg("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jv.setErrcode("500");
			jv.setErrmsg("查询失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(jv.getErrcode(),jv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return jv;
	}

}
