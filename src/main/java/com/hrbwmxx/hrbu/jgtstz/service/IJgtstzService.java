package com.hrbwmxx.hrbu.jgtstz.service;
import com.hrbwmxx.hrbu.jgtstz.vo.Jglsb;
import com.hrbwmxx.hrbu.jgtstz.vo.TsTz;
import com.webmos.framework.model.Result;
public interface IJgtstzService {
	/**
	 * 
	* @MethodName: queryJgbm 
	* @description : 查询部门代码
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	Result queryJgbm();
	
	/**
	 * 
	* @MethodName: saveXslsb 
	* @description : 保存临时表教工代码，和推送信息wid
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result saveJglsb(TsTz tstz);
	/**
	 * 
	* @MethodName: queryYdjg 
	* @description : 查询出阅读人员和阅读总数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result queryYdjg(Jglsb obj);
	/**
	 * 
	* @MethodName: queryYdjg 
	* @description : 查询出未读人员和未读总数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result queryWdjg(Jglsb obj);
	
	/**
	 * 
	* @MethodName: updateTszt 
	* @description : 删除时候修改状态state为2，同时根据登入用户的userid和wid才能删除
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result updateTszt(TsTz obj);
	
}
