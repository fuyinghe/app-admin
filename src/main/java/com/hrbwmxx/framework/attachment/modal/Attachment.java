package com.hrbwmxx.framework.attachment.modal;

public class Attachment {
	
	private String ID;
	private String OWNERID;
	private String SAVENAME;
	private String CONTENTTYPE;
	private String FILENAME;
	private String ATTACHTYPE;
	private String CREATOR;
	private String CREATETIME;
	private String FileSize;
	
	
	@Override
	public String toString() {
		return "Attachment [ID=" + ID + ", OWNERID=" + OWNERID + ", SAVENAME=" + SAVENAME + ", CONTENTTYPE="
				+ CONTENTTYPE + ", FILENAME=" + FILENAME + ", ATTACHTYPE=" + ATTACHTYPE + ", CREATOR=" + CREATOR
				+ ", CREATETIME=" + CREATETIME + ", FileSize=" + FileSize + "]";
	}
	public String getFileSize() {
		return FileSize;
	}
	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}
	public Attachment() {
		
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getOWNERID() {
		return OWNERID;
	}
	public void setOWNERID(String oWNERID) {
		OWNERID = oWNERID;
	}
	public String getSAVENAME() {
		return SAVENAME;
	}
	public void setSAVENAME(String sAVENAME) {
		SAVENAME = sAVENAME;
	}
	public String getCONTENTTYPE() {
		return CONTENTTYPE;
	}
	public void setCONTENTTYPE(String cONTENTTYPE) {
		CONTENTTYPE = cONTENTTYPE;
	}
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}
	public String getATTACHTYPE() {
		return ATTACHTYPE;
	}
	public void setATTACHTYPE(String aTTACHTYPE) {
		ATTACHTYPE = aTTACHTYPE;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
	
	
}
