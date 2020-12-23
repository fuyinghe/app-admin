package com.webmos.framework.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map;

/**
 * 校验结果对象
 * @author GRMa
 */
public class VerifyResult{
	
	//校验是否通过,默认通过
	private boolean passed=true;
	//校验错误原因
	private String wrongReason="";
	//校验通过数据集合
	private List<Map<String,String>> passList=null;
	//校验错误数据集合
	private List<Map<String,String>> wrongList=null;
	
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public String getWrongReason() {
		return wrongReason;
	}
	public void setWrongReason(String wrongReason) {
		this.wrongReason = wrongReason;
	}
	@Override
	public String toString() {
		return "VerifyResult [passed=" + passed + ", wrongReason="
				+ wrongReason + "]";
	}
	public List<Map<String, String>> getPassList() {
		return passList;
	}
	public void setPassList(List<Map<String, String>> passList) {
		this.passList = passList;
	}
	public List<Map<String, String>> getWrongList() {
		return wrongList;
	}
	public void setWrongList(List<Map<String, String>> wrongList) {
		this.wrongList = wrongList;
	}
 
}
