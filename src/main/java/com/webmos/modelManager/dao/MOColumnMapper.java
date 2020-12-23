package com.webmos.modelManager.dao;

import org.apache.ibatis.annotations.Param;

import com.webmos.modelManager.model.ColumnProperty;

public interface MOColumnMapper {
	
	/**
	 * 获取字段是否存在
	 * @param columnObj
	 * @return
	 */
	public int countColumnName(@Param("columnObj") ColumnProperty columnObj);
	/**
	 * 创建字段
	 * @param columnObj
	 * @return
	 */
	public int createColumn(@Param("columnObj") ColumnProperty columnObj);
	/**
	 * 获取字段属性信息
	 * @param columnObj
	 * @return
	 */
	public ColumnProperty getColumnItem(@Param("columnObj") ColumnProperty columnObj);
	
	/**
	 * 粘贴属性
	 * @param ids
	 * @param moid
	 */
	public int pasteColumns(@Param("ids") String[] ids,@Param("moid") String moid);
	/**
	 * 添加字段描述信息
	 * @param cp
	 */
	public int createColumnComment(@Param("columnObj") ColumnProperty columnObj);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteCheckColumns(@Param("ids") String[] ids);

}
