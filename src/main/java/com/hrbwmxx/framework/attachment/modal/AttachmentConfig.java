package com.hrbwmxx.framework.attachment.modal;

public class AttachmentConfig {

	private String ATTACH_KEY = "";
	private String SAVEPATH = "";
	private int MAXFILESIZE = 0;
	private String ALLOWEXT = "jpg";
	
	public String getATTACH_KEY() {
		return ATTACH_KEY;
	}
	public void setATTACH_KEY(String aTTACH_KEY) {
		ATTACH_KEY = aTTACH_KEY;
	}
	public String getSAVEPATH() {
		return SAVEPATH;
	}
	public void setSAVEPATH(String sAVEPATH) {
		SAVEPATH = sAVEPATH;
	}
	public int getMAXFILESIZE() {
		return MAXFILESIZE;
	}
	public void setMAXFILESIZE(int mAXFILESIZE) {
		MAXFILESIZE = mAXFILESIZE;
	}
	public String getALLOWEXT() {
		return ALLOWEXT;
	}
	public void setALLOWEXT(String aLLOWEXT) {
		ALLOWEXT = aLLOWEXT;
	}
	
	
	
}
