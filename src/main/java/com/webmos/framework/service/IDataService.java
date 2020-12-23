package com.webmos.framework.service;
import java.util.Map;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.ModelTable;
import com.webmos.framework.model.Page;
import com.webmos.framework.model.Result;


public interface IDataService {
	
	/**
	 * 返回列表数据
	 * @param page		分页数据传递对象
	 * @param dataModel 模型数据传递对象
	 * @param isDisplay 是否返回checkbox的显示值
	 * @return
	 */
	public Result queryListData(Page page,DataModel dataModel,boolean isDisplay);
	
	/**
	 * 返回一条data数据
	 * @param dataModel 模型数据传递对象
	 * @return
	 */
	public Map<String,Object> queryViewData(DataModel dataModel);
	/**
	 * 返回一条data数据
	 * @param dataModel 模型数据传递对象
	 * @return
	 */
	public Map<String, Object> queryViewDataImger(DataModel dataModel);
	/**
	 * 返回一条Clob数据
	 * @param dataModel 模型数据传递对象
	 * @return
	 */
	public String queryDataClob(String clobColumnName,DataModel dataModel);
	/**
	 * 返回一条data数据,可将select radio checkbox的显示值带回
	 * @param dataModel
	 * @return
	 */
	public Map<String,String> queryViewDataDisplay(DataModel dataModel);
	
	/**
	 * 新增一条data数据
	 * @param dataModel 模型数据传递对象
	 * @return
	 */
	public Result addOneData(DataModel dataModel);
	/**
	 * 删除一条data数据
	 * @param dataModel 模型数据传递对象
	 * @return
	 */
	public Result deleteOneData(DataModel dataModel);
	/**
	 * 新增一条data数据
	 * @param dataModel 模型数据传递对象
	 * @return
	 */
	public Result updateOneData(DataModel dataModel);
	/**
	 * 根据模块ID获取模块信息
	 * @param moid
	 * @return
	 */
	public ModelTable queryModeInfo(String moid);

	
}
