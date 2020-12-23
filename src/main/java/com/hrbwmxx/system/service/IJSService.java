package com.hrbwmxx.system.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.JSCustom;
import com.hrbwmxx.system.vo.YHCustom;

/**
 * 
* @title: IJSService 
* @description：角色接口
* @author： lijingyu
* @date： 2018年1月12日 上午10:36:48
 */
public interface IJSService {
	/**
	 * 
	* @MethodName: queryJSListPage 
	* @description : 角色列表分页
	* @author：lijingyu
	* @date： 2018年1月12日 上午10:41:45
	* @param obj
	* @return Result
	 */
	Result queryJSListPage(Page page,JSCustom obj);
	/**
	 * 
	* @MethodName: saveJS 
	* @description : 保存角色
	* @author：lijingyu
	* @date： 2018年1月12日 上午11:16:58
	* @param obj
	* @return Result
	 */
	Result saveJS(JSCustom obj);
	/**
	 * 
	* @MethodName: updateJS 
	* @description : 更新角色
	* @author：lijingyu
	* @date： 2018年1月12日 上午11:22:24
	* @param obj
	* @return Result
	 */
	Result updateJS(JSCustom obj);
	/**
	 * 
	* @MethodName: queryJSByField 
	* @description : 查询角色详情
	* @author：lijingyu
	* @date： 2018年1月12日 上午11:22:28
	* @param page
	* @param obj
	* @return Result
	 */
	Result queryJSList( JSCustom obj);
	/**
	 * 
	* @MethodName: deleteJs 
	* @description : 删除角色
	* @author：lijingyu
	* @date： 2018年1月19日 下午1:41:02
	* @param obj
	* @return Result
	 */
	Result deleteJs(JSCustom obj);
	
	Result findRoleByUserId(Page page, YHCustom yh);
	
	Result findUnselectRoleListByyhId(Page page, YHCustom yh);
	
	Result addUnSelectRole(YHCustom yh, JSCustom js);
	
	Result deleteSelectedRole(YHCustom yh, JSCustom js);
	
	Result findSelectdeYhByJsId(Page page, JSCustom js);
	
	Result findUnseleteYhByJsId(Page page, JSCustom js);
	Result findSelectGNByJsId(Page page, JSCustom js);
	Result findUnSelectGNByJsId(Page page, JSCustom js);
	Result addUnSelectMenu(GNCustom gn, JSCustom js);
	Result deleteSelectMenu(GNCustom gn, JSCustom js);

}
