package com.webmos.modelManager.service.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.ModelTable;
import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;
import com.webmos.framework.service.impl.DataServiceImpl;
import com.webmos.modelManager.dao.MOMapper;
import com.webmos.modelManager.service.IMoService;

@Service("MoServiceImpl")
public class MoServiceImpl extends DataServiceImpl implements IMoService {
	
	@Autowired
	private MOMapper moMapper;
	
	//定义数据库用户
	public String owner;
	/**
	 * 将jdbc文件中定义的用户名引入到这个方法中，从而赋值给变量owner
	 */
	@Value("#{jdbc.user}")
	public void setOwner(String owner){
		this.owner = owner.toUpperCase();
	}
	
	/**
	 * 复制模块
	 */
	public Result copyMo(String moid) {
		Result result = new ResultEntity();
		result.setErrmsg("模块配置复制成功");
		if(moid==null || moid.equals("")){
			result.setErrcode("200");
			result.setErrmsg("要copy的模块ID未找到");
		}
		int copyCount = moMapper.copyMOPro(moid);
		if(copyCount==0){
			result.setErrcode("200");
			result.setErrmsg("要copy的模块ID未找到");
		}
		return result;
	}


	/**
	 * 删除模块
	 * 删除前检查该模块是否存在属性配置，如果存在不允许删除
	 */
	public Result deleteOneData(HashMap<String, String> paramFiledMap) {
		Result result = new ResultEntity();
		String moid=paramFiledMap.get("WID");
		if(moid==null || moid.equals("")){
			result.setErrcode("200");
			result.setErrmsg("要删除的模块ID未找到");
		}else{
			int itemCounts = moMapper.checkMoItems(moid);
			if(itemCounts==0){
				int delCount = moMapper.deleteMo(moid);
				if(delCount==0){
					result.setErrcode("200");
					result.setErrmsg("删除操作失败");
				}
			}else{
				result.setErrcode("500");
				result.setErrmsg("该模块有捆绑属性，不允许删除");
			}
		}
		
		return result;
	}


	/**
	 * 根据模块ID创建数据库表
	 * @param moid
	 * @return
	 */
	
	public Result createTable(String moid) {
		Result result = new ResultEntity();
		result.setErrmsg("操作成功，表已创建完成");
		if(moid==null || moid.equals("")){
			result.setErrcode("501");
			result.setErrmsg("非法访问,moid不存在");
			return result;
		}
		
		ModelTable modInfo = super.queryModeInfo(moid);
		if(modInfo==null){
			result.setErrcode("501");
			result.setErrmsg("moid错误，未找到对应模块的模型信息");
			return result;
		}
		
		int tableCount = 0;
		//判断数据表是否存在
		tableCount = moMapper.checkTableName(modInfo.getModel_Table(),this.owner);
		//创建模块对应表
		if(tableCount<=0){
			int creatState = moMapper.createTable(modInfo.getModel_Table());
			if(creatState!=0){
				result.setErrcode("501");
				result.setErrmsg("数据表创建失败，无法完成"+modInfo.getModel_Table()+"表的创建");
				return result;
			}
		}else{
			result.setErrcode("501");
			result.setErrmsg(modInfo.getModel_Table()+"表已存在无法创建");
			return result;
		}
		
		return result;
	}

	
	
}
