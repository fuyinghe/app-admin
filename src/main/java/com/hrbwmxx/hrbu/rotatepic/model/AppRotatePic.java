package com.hrbwmxx.hrbu.rotatepic.model;

/**
 * 轮播图片
* <p>Title: AppRotatePic</p>  
* <p>Description: </p>  
* @author Mr.Zhao 
* @date 2021年1月20日
 */
public class AppRotatePic {

	private String  id;
	private String  name;
	private String  link;
	private String  state;
	private String  linkType; //1.TOP 2.AD
	private String  openType;
	private String  sort;
	private String  needLogin;
	private String  cityCode;
	private String  attachId;
	private String  apple;
	private String  adr;
	private String  wx;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getNeedLogin() {
		return needLogin;
	}
	public void setNeedLogin(String needLogin) {
		this.needLogin = needLogin;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getApple() {
		return apple;
	}
	public void setApple(String apple) {
		this.apple = apple;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	@Override
	public String toString() {
		return "AppRotatePic [id=" + id + ", name=" + name + ", link=" + link + ", state=" + state + ", linkType="
				+ linkType + ", openType=" + openType + ", sort=" + sort + ", needLogin=" + needLogin + ", cityCode="
				+ cityCode + ", attachId=" + attachId + ", apple=" + apple + ", adr=" + adr + ", wx=" + wx + "]";
	}

	
}
