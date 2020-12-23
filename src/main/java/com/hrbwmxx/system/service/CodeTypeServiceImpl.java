package com.hrbwmxx.system.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.system.dao.CodeTypeMpper;
import com.hrbwmxx.system.vo.CodeTypeCustom;
import com.hrbwmxx.system.vo.CodeTypeVo;

@Service
public class CodeTypeServiceImpl implements ICodeTypeService{
	
	
	@Autowired
	private CodeTypeMpper typeMpper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	/**
	 * 分页查询
	 * <p>Title: queryCodeTypeListPage</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @param page
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeTypeService#queryCodeTypeListPage(com.hrbwmxx.system.vo.CodeTypeCustom, com.hrbwmxx.framework.model.Page)
	 */
	@Override
	public Result queryCodeTypeListPage(CodeTypeCustom obj, Page page) {
		// TODO Auto-generated method stub
		CodeTypeVo result = new CodeTypeVo();
		List<CodeTypeCustom> list =typeMpper.queryCodeTypeListPage(page,obj);
		int totalCount = typeMpper.queryCodeTypeListPageCount(page,obj);
		result.setTotalCount(totalCount);
		result.setRows(list);
		return result;
	}
	
	/**
	 * 删除
	 * <p>Title: deleteCodeTypeValue</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeTypeService#deleteCodeTypeValue(com.hrbwmxx.system.vo.CodeTypeCustom)
	 */
	@Override
	public Result deleteCodeTypeValue(CodeTypeCustom obj) {
		// TODO Auto-generated method stub
		CodeTypeVo result = new CodeTypeVo();
		try {
			typeMpper.deleteCodeTypeValueById(obj.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}
	/**
	 * 修改
	 * <p>Title: updateCodeTypeValue</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeTypeService#updateCodeTypeValue(com.hrbwmxx.system.vo.CodeTypeCustom)
	 */
	@Override
	public Result updateCodeTypeValue(CodeTypeCustom obj) {
		// TODO Auto-generated method stub
		CodeTypeVo result = new CodeTypeVo();
		typeMpper.updateCodeTypeValue(obj);
		return result;
	}
	/**
	 * 添加
	 * <p>Title: saveCodeTypeValue</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeTypeService#saveCodeTypeValue(com.hrbwmxx.system.vo.CodeTypeCustom)
	 */
	@Override
	public Result saveCodeTypeValue(CodeTypeCustom obj) {
		// TODO Auto-generated method stub
		CodeTypeVo result = new CodeTypeVo();
		obj.setId(UUID.randomUUID().toString().replace("-", ""));
		typeMpper.saveCodeTypeValue(obj);
		return result;
	}
	/**
	 * 单条
	 * <p>Title: queryCodeTypeFieldById</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeTypeService#queryCodeTypeFieldById(com.hrbwmxx.system.vo.CodeTypeCustom)
	 */
	@Override
	public Result queryCodeTypeFieldById(CodeTypeCustom obj) {
		// TODO Auto-generated method stub
		CodeTypeVo result = new CodeTypeVo();
		try {
			CodeTypeCustom custom =typeMpper.queryCodeTypeFieldById(obj);
			result.setCodeTypeCustom(custom);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("查询失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}

}
