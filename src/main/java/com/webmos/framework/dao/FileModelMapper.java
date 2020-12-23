package com.webmos.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.webmos.framework.model.Page;

public interface FileModelMapper {
	/**
	 * 
	* @MethodName: queryFileParamByDm 
	* @description : 根据参数查询附件参数
	* @author：lijingyu 
	* @date： 2018年3月16日 下午2:42:05
	* @param map_request
	* @return Map<String,String>
	 */
	Map<String, String> queryFileParamBycode(@Param("obj") Map<String, String> map_request);
	/**
	 * 
	* @MethodName: saveFileData 
	* @description : 保存附件数据
	* @author：lijingyu 
	* @date： 2018年3月16日 下午4:43:10
	* @param map_result void
	 */
	void saveFileInfoData(Map<String, String> map_result);
	/**
	 * 
	* @MethodName: queryFileInfoList 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月16日 下午4:49:41
	* @param map_request
	* @return List<Map<String,String>>
	 */
	List<Map<String, String>>  queryAttrList(Map<String, String> map_request);
	/***以下是配置信息*********************************************************************/
	/**
	 * 
	* @MethodName: queryFileParamListPage 
	* @description : 分页查询
	* @author：lijingyu 
	* @date： 2018年3月16日 下午5:25:37
	* @param map_request
	* @param page
	* @return List<Map<String,String>>
	 */
	List<Map<String, String>>  queryFileParamListPage(@Param("obj") Map<String, String> map_request,@Param("page")Page page);
	/**
	 * 
	* @MethodName: queryFileParamListPageCount 
	* @description :  分页条数
	* @author：lijingyu 
	* @date： 2018年3月16日 下午5:25:56
	* @param map_request
	* @param page
	* @return int
	 */
	int  queryFileParamListPageCount(@Param("obj") Map<String, String> map_request,@Param("page")Page page);
	/**
	 * 
	* @MethodName: saveFileParam 
	* @description : 插入数据
	* @author：lijingyu 
	* @date： 2018年3月16日 下午5:26:13
	* @param map_request void
	 */
	void saveFileParam(Map<String, String> map_request);
}
