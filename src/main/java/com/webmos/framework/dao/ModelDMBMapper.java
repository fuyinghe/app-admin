package com.webmos.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.webmos.framework.model.CodeCollection;
import com.webmos.framework.model.CodeSql;
import com.webmos.framework.model.ModelDMB;




public interface ModelDMBMapper {
	
	/**
	 * 获取指定代码表ID对应的sql代码
	 * @param dmbId
	 * @return
	 */
	String queryDdmSQLCode(@Param("dmbId")String dmbId);
	
	/**
	 * 根据代码表对应sql返回代码表实体集合
	 * @param sqlcode
	 * @return
	 */
	List<ModelDMB> sqlCodeToDmbList(@Param("sqlcode")String sqlcode);
	
	/**
	 * 返回代码表与模型表配置关系集合
	 * @param moid
	 * @return
	 */
	List<CodeSql> queryCodeSQLList(@Param("moid")String moid);
	/**
	 * 返回代码表集合对象
	 * @param codesqllist
	 * @return
	 */
	List<CodeCollection> queryCodeCollection(@Param("codesqllist")List<CodeSql> codesqllist);
}
