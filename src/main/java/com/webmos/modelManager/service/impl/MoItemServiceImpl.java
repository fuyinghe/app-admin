package com.webmos.modelManager.service.impl;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;
import com.webmos.modelManager.dao.MOColumnMapper;
import com.webmos.modelManager.model.ColumnProperty;
import com.webmos.modelManager.service.IMoItemService;
@Service("MoItemServiceImpl")
public class MoItemServiceImpl implements IMoItemService {

	@Autowired
	private MOColumnMapper moColumnMapper;
	
	/**
	 * 添加字段
	 */
	public ResultEntity createColumn(Map<String, String> paramFiledMap) {
		ResultEntity rs = new ResultEntity();
		ColumnProperty cp = null;
		if(paramFiledMap.get("WID")!=null && !paramFiledMap.get("WID").equals("")){
			cp = new ColumnProperty();
			cp.setColumnId(paramFiledMap.get("WID"));
			//先获取要添加字段的模型信息
			cp = moColumnMapper.getColumnItem(cp);
			//检查要添加的字段是否存在
			int iscz = moColumnMapper.countColumnName(cp);
			if(iscz==0){
				if(cp.getTableName()!=null && cp.getColumnEn()!=null && cp.getColumnType()>0
						&& !cp.getTableName().equals("") && !cp.getColumnEn().equals("")){
					//添加字段
					moColumnMapper.createColumn(cp);
					//添加字段描述
					moColumnMapper.createColumnComment(cp);
					
				}else{
					rs.setErrcode("200");
					rs.setErrmsg("创建字段所需信息不足（如：对应表、英文名、类型）");
				}
			}else{
				rs.setErrcode("200");
				rs.setErrmsg("指定创建字段已存在，无法继续完成创建");
			}
		}else{
			rs.setErrcode("200");
			rs.setErrmsg("未指定操作模型属性ID");
		}

		//将字段增加
		return rs;
	}

	/**
	 * 粘贴属性
	 */
	public Result pasteColumns(HashMap<String, String> paramFiledMap) {
		
		Result rs = new ResultEntity();
		String idsstr = paramFiledMap.get("ids");
		String moid = paramFiledMap.get("moid");
		String[] ids =null;
		boolean errresult = true;
		if(moid!=null && moid.length()<=0){
			rs.setErrcode("300");
			rs.setErrmsg("参数错误，未找到目标模块id");
			errresult = false;
		}
		if(errresult && idsstr!=null && idsstr.length()<=0){
			rs.setErrcode("300");
			rs.setErrmsg("参数错误，未找到粘贴属性信息条目");
			errresult = false;
		}
		if(errresult){
			ids = idsstr.split(",");
			int pastes = moColumnMapper.pasteColumns(ids,moid);
			rs.setErrcode("0");
			rs.setErrmsg("成功粘贴到该模块"+pastes+"条属性信息");
		}
		return rs;
	}

	/**
	 * 批量删除
	 */
	public Result deleteColumns(HashMap<String, String> paramFiledMap) {
		Result rs = new ResultEntity();
		String idsstr = paramFiledMap.get("ids");
		String[] ids =null;
		boolean errresult = true;
		
		if(errresult && idsstr!=null && idsstr.length()<=0){
			rs.setErrcode("300");
			rs.setErrmsg("参数错误，未找到要删除的属性信息条目");
			errresult = false;
		}
		if(errresult){
			ids = idsstr.split(",");
			int deletes = moColumnMapper.deleteCheckColumns(ids);
			rs.setErrcode("0");
			rs.setErrmsg("成功删除该模块"+deletes+"条属性信息");
		}
		return rs;
	}

}
