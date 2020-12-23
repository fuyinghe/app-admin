package com.hrbwmxx.system.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.model.GN;
import com.hrbwmxx.system.vo.GNCustom;

public interface IGNService {

	Result findGNListForPage(Page page, GN gn);

	Result updateGn(GNCustom gn);

	Result findGnListByPid(GNCustom gn);

	Result saveGN(GNCustom gn);

	Result findChildGNByKey(GNCustom gn);

	Result deleteGNByKey(GNCustom gn);

	Result findChildGNList(Page page, GNCustom gn);

	Result findGNList(GNCustom gn);
	
	Result findGNOne(Page page, GN gn);

}
