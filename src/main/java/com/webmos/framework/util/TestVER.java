package com.webmos.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.webmos.framework.model.VerifyEntity;
import com.webmos.framework.model.VerifyResult;

public class TestVER {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//模拟检查模型
		List<VerifyEntity> verifyEntity= new ArrayList<VerifyEntity>();
		VerifyEntity ve = new VerifyEntity();
		ve.setColumn_id("11111");
		ve.setField("Name");
		ve.setTitle("姓名");
		ve.setIsRequired("true");//是否必填
		ve.setValueType("time");//检查类型
		ve.setMaxValue("4");//最大值
		ve.setMinValue("2");
		ve.setMaxSize(18);//长度
		ve.setMinSize(0);
		ve.setFormType("text");//布局结构
		ve.setIsOnlyread("0");
		verifyEntity.add(ve);
		//模拟数据
		HashMap<String, String> dataMap = new HashMap<String,String>();
		dataMap.put("Name", "12:12:00");
		

		ModelValidator mv = new ModelValidator();
		
		VerifyResult verResult = mv.checkModelDate(verifyEntity,dataMap);
		
		System.out.println(verResult);
		

	}

}
