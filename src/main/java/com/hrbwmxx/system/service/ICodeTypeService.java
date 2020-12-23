package com.hrbwmxx.system.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.vo.CodeTypeCustom;

public interface ICodeTypeService {

	Result queryCodeTypeListPage(CodeTypeCustom obj, Page page);

	Result deleteCodeTypeValue(CodeTypeCustom obj);

	Result updateCodeTypeValue(CodeTypeCustom obj);

	Result saveCodeTypeValue(CodeTypeCustom obj);

	Result queryCodeTypeFieldById(CodeTypeCustom obj);

}
