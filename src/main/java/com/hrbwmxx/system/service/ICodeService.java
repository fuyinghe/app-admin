package com.hrbwmxx.system.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.model.Code;
import com.hrbwmxx.system.vo.CodeCustom;

public interface ICodeService {

	Result queryCodeListPage(Page page, CodeCustom obj);

	Result queryCodeByField(CodeCustom obj);

	Result saveCode(CodeCustom obj);

	Result updateCode(CodeCustom obj);

	Result deleteCode(CodeCustom obj);
	
	Result selectTypeValueForCode();
	//查询所有代码信息
	Result queryCodeValue(CodeCustom obj);
	//查询城市代码信息
	Result queryCityCodeValue(Page page, CodeCustom obj);
	Result selectCityCodeVaule(CodeCustom obj);
}
