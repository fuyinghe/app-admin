package com.webmos.modelManager.service;

import java.util.HashMap;
import java.util.Map;

import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;

public interface IMoItemService {
	
	/**
	 * 创建字段属性
	 * @param dataModel
	 * @return
	 */
	public ResultEntity createColumn(Map<String, String> ParamFiledMap);

	/**
	 * 粘贴属性
	 * @param paramFiledMap
	 * @return
	 */
	public Result pasteColumns(HashMap<String, String> paramFiledMap);

	/**
	 * 批量删除
	 * @param paramFiledMap
	 * @return
	 */
	public Result deleteColumns(HashMap<String, String> paramFiledMap);

}
