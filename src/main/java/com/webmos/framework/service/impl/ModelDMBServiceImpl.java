package com.webmos.framework.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webmos.framework.dao.ModelDMBMapper;
import com.webmos.framework.service.IModelDMBService;
import com.webmos.framework.vo.ModelDMBVo;

@Service("ModelDMBServiceImpl")
public class ModelDMBServiceImpl implements IModelDMBService {

	@Autowired
	private ModelDMBMapper modelDMBMapper;
	
	public ModelDMBVo queryDmbList(String dmbId) {
		ModelDMBVo dmbvo = new ModelDMBVo();
		String sqlcode = modelDMBMapper.queryDdmSQLCode(dmbId);
		dmbvo.setList(modelDMBMapper.sqlCodeToDmbList(sqlcode));
		dmbvo.setErrcode("0");
		dmbvo.setErrmsg("操作成功");
		dmbvo.setCode("200");
		dmbvo.setMessage("操作成功");
		return dmbvo;
	}

}
