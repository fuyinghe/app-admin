package com.hrbwmxx.system.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.system.dao.PayTypeMpper;
import com.hrbwmxx.system.vo.CodeTypeVo;
import com.hrbwmxx.system.vo.PayTypeCustom;
import com.hrbwmxx.system.vo.PayTypeVo;

@Service
public class PayTypeServiceImpl implements IPayTypeService{
	
	@Autowired
	private PayTypeMpper typeMpper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	/**
	 * 
	 */
	@Override
	public Result queryPayTypeListPage(PayTypeCustom obj, Page page) {
		// TODO Auto-generated method stub
		PayTypeVo result = new PayTypeVo();
		List<PayTypeCustom> list =typeMpper.queryPayTypeListPage(page,obj);
		int totalCount = typeMpper.queryPayTypeListPageCount(page,obj);
		result.setTotalCount(totalCount);
		result.setRows(list);
		return result;
	}
	
	
	@Override
	public Result deletePayTypeValue(PayTypeCustom obj) {
		// TODO Auto-generated method stub
		CodeTypeVo result = new CodeTypeVo();
		try {
			typeMpper.deletePayTypeValueById(obj.getId());
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
	
	@Override
	public Result updatePayTypeValue(PayTypeCustom obj) {
		// TODO Auto-generated method stub
		PayTypeVo result = new PayTypeVo();
		typeMpper.updatePayTypeValue(obj);
		return result;
	}
	/**
	 * 添加
	 * <p>Title: savePayTypeValue</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 */
	@Override
	public Result savePayTypeValue(PayTypeCustom obj) {
		// TODO Auto-generated method stub
		PayTypeVo result = new PayTypeVo();
		obj.setId(UUID.randomUUID().toString().replace("-", ""));
		typeMpper.savePayTypeValue(obj);
		return result;
	}
	
	@Override
	public Result queryPayTypeFieldById(PayTypeCustom obj) {
		// TODO Auto-generated method stub
		PayTypeVo result = new PayTypeVo();
		try {
			PayTypeCustom custom =typeMpper.queryPayTypeFieldById(obj);
			result.setPayTypeCustom(custom);
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
