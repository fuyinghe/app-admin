package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.model.Code;
import com.hrbwmxx.system.vo.CodeCustom;
import com.hrbwmxx.system.vo.CodeTypeCustom;

public interface CodeMapper {
	//分页查询
	List<CodeCustom> queryCodeListPage(@Param("page") Page page, @Param("obj") CodeCustom obj);
	//查询条数
	int queryCodeListPageCount(@Param("page") Page page, @Param("obj") CodeCustom obj);
	//单条查询
	CodeCustom queryCodeByField(@Param("obj") CodeCustom obj);
	//添加
	void saveCode(CodeCustom obj);
	//修改
	void updateCode(CodeCustom obj);
	//删除
	void deleteCode(String id);
	//下拉
	List<CodeTypeCustom> selectTypeValueForCode();
	//查询标准代码是否重复
	int selectCodeValueByObj(@Param("str")String str);
	//查询类型
	CodeTypeCustom selectTypeValue(@Param("dmlx")String dmlx);
	//根据FHDM,SJDM,SJZ,查询状态为使用的CODE信息
	List<CodeCustom> queryCodeValueByObj(@Param("obj") CodeCustom obj);
	//城市代码查询
	List<CodeCustom> queryCityCodeValueByObj(@Param("page") Page page, @Param("obj")CodeCustom obj);
	//城市代码
	int queryCityCodeValueByObjCount(@Param("page") Page page,@Param("obj") CodeCustom obj);
	//查询市县名称
	List<CodeCustom>  queryCityCodeForAPP(@Param("page")CodeCustom obj);
	//三级联动
	List<CodeCustom> selectCityCodeVaule(@Param("obj") CodeCustom obj);
}
