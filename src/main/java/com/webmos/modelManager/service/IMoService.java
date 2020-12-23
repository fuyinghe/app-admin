package com.webmos.modelManager.service;

import java.util.HashMap;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Result;

public interface IMoService {

	/**
	 * 复制一个模块
	 * @param moid
	 * @return
	 */
	public Result copyMo(String moid);

	/**
	 * 删除模块
	 * @param paramFiledMap
	 * @return
	 */
	public Result deleteOneData(HashMap<String, String> paramFiledMap);
	
	/**
	 * 根据模块ID创建数据库表
	 * @param moid
	 * @return
	 */
	public Result createTable(String moid);
	
}
