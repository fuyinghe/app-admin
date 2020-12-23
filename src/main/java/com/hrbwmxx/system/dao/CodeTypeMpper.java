package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.vo.CodeTypeCustom;

public interface CodeTypeMpper {

	List<CodeTypeCustom> queryCodeTypeListPage(@Param("page") Page page, @Param("obj") CodeTypeCustom obj);

	int queryCodeTypeListPageCount(@Param("page") Page page, @Param("obj") CodeTypeCustom obj);



	void updateCodeTypeValue(CodeTypeCustom obj);

	void saveCodeTypeValue(CodeTypeCustom obj);

	CodeTypeCustom queryCodeTypeFieldById(@Param("obj") CodeTypeCustom obj);
	
	void deleteCodeTypeValueById(@Param("string")String string);

}
