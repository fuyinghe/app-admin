package com.hrbwmxx.system.model;

/**
 * app管理
 * @ClassName:  AppManage   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @date:   2018年10月18日 
 * @author: CHIUCLOUD(云)    
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */
public class AppManage {
	
	private String  id; //id
	private String	name; //名称
	private String	link; //链接
	private String	state; //状态  0-否,1-是
	private String	open_type; //打开类型
	private String	app_type_id; //使用类型
	private String	sys_type; //系统应用  1-否,0-是
	private String	weight; //权重
	private String	need_login; //需要登录  0-否,1-是
	private String	icon; //图片链接
	private String	citycode; //城市代码
	private String	firstplace; //首页头部 , 0-否,1-是
	private String	secondplace;//首页中部  ,0-否,1-是
	private String	thirdplace;//首页中部广告  ,0-否,1-是
	private String	adplace; //广告 0-否,1-是
	private String	omit; //删除状态  0-否,1-是
	private String	urltype; //图片类型  1-本地,2-网络
	private String	ispay; //是否是缴费项  0-否,1-是
	private String	paytype; //缴费类型
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
	public String getOpen_type() {
		return open_type;
	}
	public void setOpen_type(String open_type) {
		this.open_type = open_type;
	}
	public String getSys_type() {
		return sys_type;
	}
	public void setSys_type(String sys_type) {
		this.sys_type = sys_type;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getNeed_login() {
		return need_login;
	}
	public void setNeed_login(String need_login) {
		this.need_login = need_login;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getFirstplace() {
		return firstplace;
	}
	public void setFirstplace(String firstplace) {
		this.firstplace = firstplace;
	}
	public String getSecondplace() {
		return secondplace;
	}
	public void setSecondplace(String secondplace) {
		this.secondplace = secondplace;
	}
	public String getAdplace() {
		return adplace;
	}
	public void setAdplace(String adplace) {
		this.adplace = adplace;
	}
	public String getOmit() {
		return omit;
	}
	public void setOmit(String omit) {
		this.omit = omit;
	}
	public String getApp_type_id() {
		return app_type_id;
	}
	public void setApp_type_id(String app_type_id) {
		this.app_type_id = app_type_id;
	}
	public String getUrltype() {
		return urltype;
	}
	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}
	public String getThirdplace() {
		return thirdplace;
	}
	public void setThirdplace(String thirdplace) {
		this.thirdplace = thirdplace;
	}
	public String getIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	
}
