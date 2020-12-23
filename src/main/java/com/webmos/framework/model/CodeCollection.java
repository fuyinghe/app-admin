package com.webmos.framework.model;

import java.util.List;

/**
 * 代码表集合
 * 为了方便快速的为mete模型返回list代码集合，使用组合语句将代码返回至该实体中。
 * 在使用时候，可通过ID取出某一个代码对应的代码表集合；
 * @author grma
 *
 */
public class CodeCollection {
	
	private String id="";
	
	private String formType="";
	
	private List<ModelDMB> dmbList;

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public List<ModelDMB> getDmbList() {
		return dmbList;
	}

	public void setDmbList(List<ModelDMB> dmbList) {
		this.dmbList = dmbList;
	}

	@Override
	public String toString() {
		return "CodeCollection [id=" + id + ", dmbList=" + dmbList + "]";
	}

}
