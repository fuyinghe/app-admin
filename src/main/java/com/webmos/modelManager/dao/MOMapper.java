package com.webmos.modelManager.dao;

import org.apache.ibatis.annotations.Param;

public interface MOMapper {
	/**
	 * 创建表
	 * @param dataModel
	 * @return
	 */
	int createTable(@Param("tableName") String tableName);
	/**
	 * 检查表名是否存在物理表
	 * @param tableName
	 * @return
	 */
	int checkTableName(@Param("tableName") String tableName,@Param("owner") String owner);
	/**
	 * 检查模块是否有配置属性
	 * @param moid
	 * @return
	 */
	int checkMoItems(@Param("moid") String moid);
	/**
	 * 删除模块配置属性
	 * @param moid
	 * @return
	 */
	int deleteMo(@Param("moid") String moid);
	
	int copyMOPro(@Param("moid") String moid);
}
