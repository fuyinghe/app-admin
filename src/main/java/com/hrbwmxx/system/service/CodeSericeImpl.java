package com.hrbwmxx.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.system.dao.CodeMapper;
import com.hrbwmxx.system.model.Code;
import com.hrbwmxx.system.vo.CodeCustom;
import com.hrbwmxx.system.vo.CodeTypeCustom;
import com.hrbwmxx.system.vo.CodeTypeVo;
import com.hrbwmxx.system.vo.CodeVo;


@Service
public class CodeSericeImpl implements ICodeService{

	@Autowired
	private CodeMapper codeMapper;
	/**
	 * 分页查询
	 * <p>Title: queryCodeListPage</p>   
	 * <p>Description: </p>   
	 * @param page
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#queryCodeListPage(com.hrbwmxx.framework.model.Page, com.hrbwmxx.system.vo.CodeCustom)
	 */
	@Override
	public Result queryCodeListPage(Page page, CodeCustom obj) {
		// TODO Auto-generated method stub
		CodeVo result = new CodeVo();
		List<CodeCustom> list =	codeMapper.queryCodeListPage(page,obj);
		int totalCount = codeMapper.queryCodeListPageCount(page,obj);
		result.setTotalCount(totalCount);
		result.setRows(list);
		return result;
	}
	/**
	 * 单条查询
	 * <p>Title: queryCodeByField</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#queryCodeByField(com.hrbwmxx.system.vo.CodeCustom)
	 */
	@Override
	public Result queryCodeByField(CodeCustom obj) {
		// TODO Auto-generated method stub
		CodeVo result = new CodeVo();
		try {
			CodeCustom custom =codeMapper.queryCodeByField(obj);
			result.setCodeCustom(custom);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("查询失败！");
		}
		return result;
	}
	/**
	 * 保存
	 * <p>Title: saveCode</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#saveCode(com.hrbwmxx.system.vo.CodeCustom)
	 */
	@Override
	public Result saveCode(CodeCustom obj) {
		// TODO Auto-generated method stub
		CodeVo re = new CodeVo();
		String  str = "";
		try {
			//根据ID查询代码类型表
			if ("".equals(obj.getBzdm())||"".equals(obj.getDmlx()) ) {
				re.setErrcode("500");
				re.setErrmsg("代码或代码类型为空,请重新输入");
			}else {
			//根据标准代码查询代码表信息
			int i =codeMapper.selectCodeValueByObj(obj.getBzdm());
			//根据代码类型查询代码类型表信息
			CodeTypeCustom typeCustom =codeMapper.selectTypeValue(obj.getDmlx());
			//判断标准代码是否唯一,如果唯一,设置成为主键,不唯一,加上返回代码
			if ( i <= 0 ) {
				str = obj.getBzdm();
			}else if ( i > 0 ) {
				//标准代码不唯一,代码表标准代码+类型表的返回代码
				str  =obj.getBzdm()+typeCustom.getFhdm();
				int j =codeMapper.selectCodeValueByObj(str);
				//判断标准代码是否唯一,如果唯一,设置成为主键,不唯一,加上返回代码+随机数
				if (j>0) {
					String  number = Integer.toString((int)(Math.random()*900)+100);
					 str = str+number;
				}
			}
			if ("".equals(obj.getState())) {
				obj.setState("0");
			}
			obj.setFhdm(typeCustom.getFhdm());
			obj.setDm(str);
			codeMapper.saveCode(obj); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败！");
		}
		return re;
	}
	/**
	 * 修改
	 * <p>Title: updateCode</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#updateCode(com.hrbwmxx.system.vo.CodeCustom)
	 */
	@Override
	public Result updateCode(CodeCustom obj) {
		// TODO Auto-generated method stub
		CodeVo result = new CodeVo();
		codeMapper.updateCode(obj);
		return result;
	}
	/**
	 * 删除
	 * <p>Title: deleteCode</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#deleteCode(com.hrbwmxx.system.vo.CodeCustom)
	 */
	@Override
	public Result deleteCode(CodeCustom obj) {
		// TODO Auto-generated method stub
		CodeVo result = new CodeVo();
		try {
			codeMapper.deleteCode(obj.getDm());
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("删除失败！");
		}
		return result;
	}
	/**
	 * 下拉
	 * <p>Title: selectTypeValueForCode</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#selectTypeValueForCode()
	 */
	@Override
	public Result selectTypeValueForCode() {
		// TODO Auto-generated method stub
		CodeTypeVo re = new CodeTypeVo();
		try {
			List<CodeTypeCustom> codeTypeCustoms= codeMapper.selectTypeValueForCode();
			re.setRows(codeTypeCustoms);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("查询失败！");
		}
		return re;
	}
	/**
	 * 根据FHDM,SJDM,SJZ,查询状态为使用的CODE信息
	 * <p>Title: queryCodeValue</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#queryCodeValue()
	 */
	@Override
	public Result queryCodeValue(CodeCustom obj) {
		// TODO Auto-generated method stub
		CodeVo result = new CodeVo();
		try {
			List<CodeCustom> list =	codeMapper.queryCodeValueByObj(obj);
			result.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("查询失败！");
		}
		return result;
	}
	/**
	 * 查询城市代码
	 * <p>Title: queryCityCodeValue</p>   
	 * <p>Description: </p>   
	 * @param page
	 * @param obj
	 * @return   
	 * @see com.hrbwmxx.system.service.ICodeService#queryCityCodeValue(com.hrbwmxx.framework.model.Page, com.hrbwmxx.system.vo.CodeCustom)
	 */
	@Override
	public Result queryCityCodeValue(Page page, CodeCustom obj) {
		CodeVo result = new CodeVo();
		try {
			List<CodeCustom> list =	codeMapper.queryCityCodeValueByObj(page,obj);
			int totalCount = codeMapper.queryCityCodeValueByObjCount(page,obj);
			result.setTotalCount(totalCount);
			result.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("查询失败！");
		}
		return result;
	}
	@Override
	public Result selectCityCodeVaule(CodeCustom obj) {
		CodeVo result = new CodeVo();
		try {
			List<CodeCustom> list =	codeMapper.selectCityCodeVaule(obj);
			result.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("查询失败！");
		}
		return result;
	}
	

}
