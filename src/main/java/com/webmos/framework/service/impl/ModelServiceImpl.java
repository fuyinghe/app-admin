package com.webmos.framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webmos.framework.dao.ModelDMBMapper;
import com.webmos.framework.dao.ModelMapper;
import com.webmos.framework.model.CodeCollection;
import com.webmos.framework.model.CodeSql;
import com.webmos.framework.model.Result;
import com.webmos.framework.service.IModelService;
import com.webmos.framework.vo.ModelMeteCustom;
import com.webmos.framework.vo.ModelMeteVo;

@Service("ModelServiceImpl")
public class ModelServiceImpl implements IModelService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ModelDMBMapper modelDMBMapper;
	
	/**
	 * 获取mete模型
	 */
	public Result queryModelMeteList(String moid) {
		
		ModelMeteVo result = new ModelMeteVo();
		//获取到指定模块的mete模型数据
		List<ModelMeteCustom> meteList =  modelMapper.queryModelMeteList(moid);
		/**
		 * 代码表处理方案二 思路 （尝试阶段）
		 * 1 通过对模型与代码表之间的关系，取出为select类型相关模型对应的sql片段集合
		 * 2 通过codesql集合在mybitas中进行遍历，组装一个sql
		 * 3 用resultMap方式将数据返回到List<CodeCollection>集合中
		 * 4 CodeCollection对象内部存储一个dmb集合，该集合为某一个模型item的对应代码集合
		 * 5 遍历返回的metelist模型，将返回结果放入mete模型DmbList数组中
		 * 6 判断当前元素模型是否是select类型，如果是select类型，去集合中取出对应代码表集合；
		 * 7 优点：一次查询返回全部代码值
		 */
		List<CodeSql> codeSQLList = modelDMBMapper.queryCodeSQLList(moid);
		//通过组装的SQL对代码表进行一次性全部查询出来。
		List<CodeCollection> codecollection = null;
		if(codeSQLList.size()>0){
			codecollection = modelDMBMapper.queryCodeCollection(codeSQLList);
			//将返回结果放入mete模型DmbList数组中
			for(int i=0;i<meteList.size();i++){
				ModelMeteCustom mmc = meteList.get(i);
				//判断当前元素模型是否是select类型，如果是select类型，去集合中取出对应代码表集合；
				if(mmc.getFormType().equals("select") || mmc.getFormType().equals("radio") || mmc.getFormType().equals("checkbox")){
					for(int j=0;j<codecollection.size();j++){
						CodeCollection codeCollection=codecollection.get(j);
						if(mmc.getField().equals(codeCollection.getId())){
							//取出代码表，放入模型对象中
							mmc.setDmbList(codeCollection.getDmbList());
						}
					}
				}
			}
		}
		
		
		//模型mete结构数大于0时，正常返回,setErrcode为0是因为前端只接收0为正确的值
		if(meteList.size()>0){
			result.setModel(meteList);
			result.setErrcode("0");
		}
		
		return result;
	}
	
	
}
