package com.hrbwmxx.framework.fj.dao;
/**
 * 
* @title: FJMapper 
* @description：附件数据层
* @author： 李静雨
* @date： 2018年1月12日 下午4:49:02
 */

import java.util.List;
import com.hrbwmxx.framework.fj.model.FJ;
import com.hrbwmxx.framework.fj.vo.FJCustom;
public interface FJMapper {
	/**
	 * 
	* @MethodName: saveBatcFJList 
	* @description : 批量保存
	* @author：lijingyu
	* @date： 2018年1月15日 下午5:28:27
	* @param list void
	 */
	void saveBatcFJList(List<FJCustom> list);
	/**
	 * 
	* @MethodName: queryFJList 
	* @description : 查询列表
	* @author：lijingyu
	* @date： 2018年1月15日 下午5:28:31
	* @param obj
	* @return List<FJCustom>
	 */
	List<FJCustom> queryFJList(FJ obj);
	/**
	 * 
	* @MethodName: updateFj 
	* @description : 更新附件
	* @author：lijingyu
	* @date： 2018年1月15日 下午5:28:35
	* @param obj void
	 */
	void updateFj (FJ obj);
	/**
	 * 
	* @MethodName: saveFJ 
	* @description : 保存附件
	* @author：lijingyu
	* @date： 2018年1月15日 下午5:28:38
	* @param obj void
	 */
	void saveFJ(FJ obj);
}
