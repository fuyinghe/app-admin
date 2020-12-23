package com.hrbwmxx.framework.attachment.modal;

/**
 * 上传附件实体类
 * @author lenovo
 *
 */
public class UploadFile {

	private String errcode = "0";
	private String errmsg = "操作成功";
	private String attachId;
	private String downloadUrl;
	private String url;
	private String originalName;
	private String name;
	private String type;
	private String state="fail";
	private String size;
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
//	{
//	    errcode:"0",
//	    errmsg:"上传成功",
//	    attachId:"ce8bd729171847369e84155aa6c33b75",
//	    downloadUrl:"controllerName/download.do?attachId=ce8bd729171847369e84155aa6c33b75",
//	    url:"controllerName/showImg/ce8bd729171847369e84155aa6c33b75.jpg",
//	    originalName:"fuyinghe.jpg",
//	    name:"ce8bd729171847369e84155aa6c33b75.jpg",
//	    type:".jpg",
//	    state:"SUCCESS",
//	    size:"102400"
//	}
//	    attachId：附件ID；
//	    downloadUrl:下载地址（用户打开此链接将强制下载，避免jpg/pdf等文件直接被浏览器打开）；
//	    url:如果是图片，该字段为预览地址（建议通过url rewrite生成对应后缀名的url）；
//	    originalName:该附件在客户端原有的文件名；
//	    name:该附件在上传到服务端后的新文件名；
//	    type:后缀名
//	    state:针对umeditor上传图片的成功标识，成功默认为"SUCCESS";
//	    size:该附件大小，也可以用字段len。（单位：字节）；
	
}
