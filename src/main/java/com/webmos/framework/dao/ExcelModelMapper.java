package com.webmos.framework.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.webmos.framework.model.ColumnProperty;
import com.webmos.framework.model.ImpModel;
import com.webmos.framework.model.VerifyEntity;

public interface ExcelModelMapper {
	/**
	 * 
	* @MethodName: queryBingTable 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月5日 上午11:02:42
	* @param modid
	* @return Map<String,String>
	 */
	Map<String, String>  queryBingTable(String modid);
	/**
	 * 
	* @MethodName: queryBingColumn 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月5日 上午11:02:46
	* @param modid
	* @return List<ColumnProperty>
	 */
	List<ColumnProperty> queryBingColumn(String modid);
	/**
	 * 
	* @MethodName: addTemplateData 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月5日 上午11:02:49
	* @param tableName
	* @param columns
	* @param values void
	 */
	void addTemplateData(@Param ("tableName")  String tableName,@Param ("columns")String columns,  @Param ("list") List<String> values);
	/**
	 * 
	* @MethodName: updateTemplateDataFkRrelation 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月5日 上午11:02:53
	* @param tabllename
	* @param whereSqlCode
	* @param list void
	 */
	void updateTemplateDataFkRrelation(
			@Param ("tableName") String tabllename,
			@Param ("whereSqlCode") String whereSqlCode, 
			@Param ("list")  List<String> list);

	List<Map<String, String>> queryModelFkRrelations(String modid);
	/**
	 * 
	* @MethodName: queryFkValuesMap 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月5日 上午11:02:35
	* @param fkMap
	* @return Map<String,String>
	 */
	List<Map<String, String>>  queryFkValuesMap(@Param("list")  List<Map<String, String>> fkMap);
	List<VerifyEntity> queryVerifyEntityModel(String modid);

	//返回模块导入模板模型配置信息
	List<ImpModel> queryImpTempMode(@Param("moid")String moid);
	
	List<Map<String, String>> existsData(@Param("columnCode")String columnStr, 
										 @Param("tableName")String tableName, 
										 @Param("keySqlCode")String keySql, 
										 @Param("czr")String czr, 
										 @Param("uuid")String uuid);
	List<Map<String, String>> notExistsData(@Param("columnCode")String columnStr, 
										 @Param("tableName")String tableName, 
										 @Param("keySqlCode")String keySql, 
										 @Param("czr")String czr, 
										 @Param("uuid")String uuid);
	void deleteModel(@Param("tableName")String tableName, @Param("uuid")String uuid, @Param("czr")String czr);
	
}
