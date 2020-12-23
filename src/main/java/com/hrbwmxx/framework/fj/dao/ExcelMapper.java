package com.hrbwmxx.framework.fj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ExcelMapper {
	/**
	 * 
	* @MethodName: saveBatchList 
	* @description : 批量保存数据（表和具体列需要动态拼凑）
	* @author：lijingyu 
	* @date： 2018年1月23日 下午2:15:06
	* @param list
	 */
	void saveBatchList(@Param("list")  List<String[]> list);

}
