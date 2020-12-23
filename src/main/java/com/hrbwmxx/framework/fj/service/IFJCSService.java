package com.hrbwmxx.framework.fj.service;

import com.hrbwmxx.framework.fj.model.FJCS;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;

public interface IFJCSService {

	Result queryFJCSListPage(Page page, FJCS obj);

	Result saveFJCS(FJCS obj);

	Result updateFJCS(FJCS obj);

}
