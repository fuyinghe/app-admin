package com.hrbwmxx.system.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.vo.PayTypeCustom;

public interface IPayTypeService {

	Result queryPayTypeListPage(PayTypeCustom obj, Page page);

	Result deletePayTypeValue(PayTypeCustom obj);

	Result updatePayTypeValue(PayTypeCustom obj);

	Result savePayTypeValue(PayTypeCustom obj);

	Result queryPayTypeFieldById(PayTypeCustom obj);

}
