package com.webmos.framework.vo;

import java.util.List;
import java.util.Map;

import com.webmos.framework.model.ResultEntity;
import com.webmos.framework.model.VerifyResult;

public class ExcelModelVo extends ResultEntity{
	//校验结果类
	private VerifyResult verifyResult;
	//新增数据集合
	private List<Map<String,String>> addList;
	//修改数据集合
	private List<Map<String,String>> uptList;

	public VerifyResult getVerifyResult() {
		return verifyResult;
	}

	public void setVerifyResult(VerifyResult verifyResult) {
		this.verifyResult = verifyResult;
	}

	public List<Map<String, String>> getAddList() {
		return addList;
	}

	public void setAddList(List<Map<String, String>> addList) {
		this.addList = addList;
	}

	public List<Map<String, String>> getUptList() {
		return uptList;
	}

	public void setUptList(List<Map<String, String>> uptList) {
		this.uptList = uptList;
	}
}
