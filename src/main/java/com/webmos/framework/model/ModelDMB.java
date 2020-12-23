package com.webmos.framework.model;

public class ModelDMB {
	
	private String text="";
	
	private String value="";
	
	private String prentcode;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrentcode() {
		return prentcode;
	}

	public void setPrentcode(String prentcode) {
		this.prentcode = prentcode;
	}

	@Override
	public String toString() {
		return "DMB [text=" + text + ", value=" + value + ", prentcode="
				+ prentcode + "]";
	}
	
}
