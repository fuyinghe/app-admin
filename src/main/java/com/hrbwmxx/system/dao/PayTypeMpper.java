package com.hrbwmxx.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.vo.PayTypeCustom;

public interface PayTypeMpper {

	List<PayTypeCustom> queryPayTypeListPage(@Param("page") Page page, @Param("obj") PayTypeCustom obj);

	int queryPayTypeListPageCount(@Param("page") Page page, @Param("obj") PayTypeCustom obj);

	void updatePayTypeValue(PayTypeCustom obj);

	void savePayTypeValue(PayTypeCustom obj);

	PayTypeCustom queryPayTypeFieldById(@Param("obj") PayTypeCustom obj);
	
	void deletePayTypeValueById(@Param("string")String string);

}
