package com.webmos.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.attachment.modal.Attachment;
import com.webmos.framework.model.CodeSql;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Page;


public interface DataMapper {
	
	//返回列表数据
	List<Map<String,String>> queryDataList(@Param("page") Page page,@Param("dataModel") DataModel dataModel,@Param("codeSqls") List<CodeSql> codeSQLList);
	//返回条数
	int queryDataCount(@Param("page") Page page,@Param("dataModel") DataModel dataModel);
	
	//返回一条数据
	Map<String,Object> queryDataView(@Param("dataModel") DataModel dataModel);
	//返回一条数据，该数据会将代码转换为
	Map<String,String> queryDataViewDisplay(@Param("dataModel") DataModel dataModel,@Param("codeSqls") List<CodeSql> codeSQLList);
	//返回clob字段内容
	String queryDataClob(@Param("dataModel") DataModel dataModel);
	//新增一条数据
	int addOneData(@Param("dataModel") DataModel dataModel);
	//删除一条数据
	int deleteOneData(@Param("dataModel") DataModel dataModel);
	//更新一条数据
	int updateOneData(@Param("dataModel") DataModel dataModel);
	
	//获取附件列表
	List<Attachment>  queryAttachments(@Param("ownerId") String ownerId);
	
}
