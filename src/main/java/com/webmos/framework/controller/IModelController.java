package com.webmos.framework.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import com.webmos.framework.model.Result;
import com.webmos.framework.model.Page;

public interface IModelController {

	/**
	 * 获取绑定模块的Mate结构数据
	 * 该数据主要是为了做布局处理使用
	 * @return
	 */
	public Result queryModelMete();
	
	/**
	 * 获取数据列表List
	 * 比较常用方法
	 * 可将select/radio数据转换为显示值，但无法将checkbox转换
	 * @param page
	 * @param queryParamFiledMap
	 * @return
	 */
	public Result queryListData(Page page,@RequestParam HashMap<String,String>  queryParamFiledMap);
	
	public Result exportListData(HttpServletResponse response,HttpServletRequest request,Page page,@RequestParam HashMap<String,String>  queryParamFiledMap);
	/**
	 * 获取数据列表List
	 * 可将select/radio/checkbox数据转换为显示值；
	 * 方法不常用，如非必要在列表中显示checkbox的值，不建议使用，该方法耗能；
	 * @param page
	 * @param queryParamFiledMap
	 * @return 
	 */
	public Result queryListData_Display(Page page,HashMap<String, String> queryParamFiledMap);
	
	
	/**
	 * 获取一条数据
	 * @param queryParamFiledMap
	 * @return
	 */
	public Map<String,Object> queryViewData(@RequestParam HashMap<String,String>  queryParamFiledMap);
	/**
	 * 获取一条数据
	 * @param queryParamFiledMap
	 * @return
	 */
	public Map<String,Object> queryViewDataImger(@RequestParam HashMap<String,String>  queryParamFiledMap);
	
	
	/**
	 * 获取一条数据
	 * 代码值被转换为具体显示值
	 * @param queryParamFiledMap
	 * @return
	 */
	public Map<String,String> queryViewDataDisplay(@RequestParam HashMap<String,String>  queryParamFiledMap);
	
	
	/**
	 * 插入一条数据
	 * @param queryParamFiledMap
	 * @return
	 */
	public Result addOneData(@RequestParam HashMap<String,String>  queryParamFiledMap);
	/**
	 * 删除一条数据，伪删除
	 * 该方法将数据表中状态修改为2，默认数据读取时会过滤状态<2的数据
	 * @param queryParamFiledMap
	 * @return
	 */
	public Result deleteOneData(@RequestParam HashMap<String,String>  queryParamFiledMap);
	/**
	 * 更新一条数据
	 * @param queryParamFiledMap
	 * @return
	 */
	public Result updateOneData(@RequestParam HashMap<String,String>  queryParamFiledMap);




}
