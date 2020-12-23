package com.hrbwmxx.framework.fj.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrbwmxx.framework.fj.vo.FJCustom;
/**
 * 
* @title: IFJService 
* @description：附件接口
* @author： 李静雨
* @date： 2018年1月15日 下午3:21:14
 */
public interface IFJService {
	/**
	 * 
	* @MethodName: upload 
	* @description : 附件上传并入库
	* @author：lijingyu
	* @date： 2018年1月15日 下午3:20:32
	* @param file
	* @param obj
	* @return
	* @throws Exception String
	 */
	String upload(MultipartFile file, FJCustom obj) throws Exception ;
	/**
	 * 
	* @MethodName: saveBatcFJList 
	* @description : 附件批量保存
	* @author：lijingyu
	* @date： 2018年1月15日 下午3:20:36
	* @param list void
	 */
	void saveBatcFJList(List<FJCustom> list);
	/**
	 * 
	* @MethodName: queryFJList 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月15日 下午3:20:40
	* @param obj
	* @return List<FJCustom>
	 */
	List<FJCustom> queryFJList(FJCustom obj);
	/**
	 * 
	* @MethodName: updateFj 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月15日 下午3:20:43
	* @param obj void
	 */
	void updateFj (FJCustom obj);
	/**
	 * 
	* @MethodName: saveFJ 
	* @description : TODO
	* @author：lijingyu
	* @date： 2018年1月15日 下午3:20:47
	* @param obj void
	 */
	void saveFJ(FJCustom obj);
}
