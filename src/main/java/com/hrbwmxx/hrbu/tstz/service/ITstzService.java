package com.hrbwmxx.hrbu.tstz.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.tstz.vo.TsTz;
import com.hrbwmxx.hrbu.tstz.vo.XsXx;
import com.hrbwmxx.hrbu.tstz.vo.Xslsb;
/**
 * 
* @title: ITstzService 
* @description：菜单接口
* @author： shijiajun
* @date： 2018年5月11日 
 */
public interface ITstzService {
	/**
	 * 
	* @MethodName: queryBj 
	* @description : 查询班级代码
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	Result queryBj(@Param("page")Page page);
	/**
	 * 
	* @MethodName: queryTsBjRy 
	* @description : 根据wid获取推送新闻信息
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
//	Result queryTsBjRy(TsTz obj);
	/**
	 * 
	* @MethodName: queryTsBjRy 
	* @description : 获取班级总数人员
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result queryXszs(XsXx obj);
	/**
	 * 
	* @MethodName: saveXslsb 
	* @description : 保存临时表进行学生学号，和推送信息wid
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result saveXslsb(TsTz tstz,Xslsb obj,XsXx xsob);
	/**
	 * 
	* @MethodName: updateXslsbzt 
	* @description : 修改学生临时表状态
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result updateXslsbzt(Xslsb obj);
	/**
	 * 
	* @MethodName: queryYdrs 
	* @description : 查询临时表越多人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result queryYdrs(Xslsb obj);
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
	/**
	 * 
	* @MethodName: queryYdxs 
	* @description : 查询阅读状态和人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result queryYdxs(Xslsb obj);
	/**
	 * 
	* @MethodName: queryWdxs 
	* @description : 查询未读状态和人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	Result queryWdxs(Xslsb obj);
	
	Map<String, Object> queryCs(Xslsb obj);

}
