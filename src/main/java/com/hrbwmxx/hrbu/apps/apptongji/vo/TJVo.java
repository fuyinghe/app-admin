package com.hrbwmxx.hrbu.apps.apptongji.vo;

import java.util.List;


import com.hrbwmxx.hrbu.apps.apptongji.modal.UserTJ;
import com.hrbwmxx.system.vo.CodeCustom;

public class TJVo extends CustomTJ {

	//用户激活量
	private List<UserTJ> usertjList;
	//应用访问量
	private List<UserTJ> appList;
	
	//分类应用访问趋势
	private List<UserTJ> jzgappList;
	private List<UserTJ> xsappList;
	private List<UserTJ> ggappList;
	
	//年份
	private List<UserTJ> yearappList;
	private List<CodeCustom> citycodeappList;
		
		
	private	List<UserTJ> ggyyappList ; //公共应用
	private	List<UserTJ> hpjrappList ; //惠普金融
	private	List<UserTJ> jtcxappList ; //交通出行
	private	List<UserTJ> shfwappList; //生活服务
	private	List<UserTJ> zhxcappList; //智慧乡村
	private	List<UserTJ> bmfwappList ; //便民服务
	private	List<UserTJ> advertisingappList ; //广告
	private	List<UserTJ> typeList; //类型
	
	

	public List<UserTJ> getJzgappList() {
		return jzgappList;
	}

	public void setJzgappList(List<UserTJ> jzgappList) {
		this.jzgappList = jzgappList;
	}

	public List<UserTJ> getXsappList() {
		return xsappList;
	}

	public void setXsappList(List<UserTJ> xsappList) {
		this.xsappList = xsappList;
	}

	public List<UserTJ> getGgappList() {
		return ggappList;
	}

	public void setGgappList(List<UserTJ> ggappList) {
		this.ggappList = ggappList;
	}

	public List<UserTJ> getAppList() {
		return appList;
	}

	public void setAppList(List<UserTJ> appList) {
		this.appList = appList;
	}

	public List<UserTJ> getUsertjList() {
		return usertjList;
	}

	public void setUsertjList(List<UserTJ> usertjList) {
		this.usertjList = usertjList;
	}

	public List<UserTJ> getGgyyappList() {
		return ggyyappList;
	}

	public void setGgyyappList(List<UserTJ> ggyyappList) {
		this.ggyyappList = ggyyappList;
	}

	public List<UserTJ> getHpjrappList() {
		return hpjrappList;
	}

	public void setHpjrappList(List<UserTJ> hpjrappList) {
		this.hpjrappList = hpjrappList;
	}

	public List<UserTJ> getJtcxappList() {
		return jtcxappList;
	}

	public void setJtcxappList(List<UserTJ> jtcxappList) {
		this.jtcxappList = jtcxappList;
	}

	public List<UserTJ> getShfwappList() {
		return shfwappList;
	}

	public void setShfwappList(List<UserTJ> shfwappList) {
		this.shfwappList = shfwappList;
	}

	public List<UserTJ> getZhxcappList() {
		return zhxcappList;
	}

	public void setZhxcappList(List<UserTJ> zhxcappList) {
		this.zhxcappList = zhxcappList;
	}

	public List<UserTJ> getBmfwappList() {
		return bmfwappList;
	}

	public void setBmfwappList(List<UserTJ> bmfwappList) {
		this.bmfwappList = bmfwappList;
	}

	public List<UserTJ> getAdvertisingappList() {
		return advertisingappList;
	}

	public void setAdvertisingappList(List<UserTJ> advertisingappList) {
		this.advertisingappList = advertisingappList;
	}

	public List<UserTJ> getYearappList() {
		return yearappList;
	}

	public void setYearappList(List<UserTJ> yearappList) {
		this.yearappList = yearappList;
	}

	public List<CodeCustom> getCitycodeappList() {
		return citycodeappList;
	}

	public void setCitycodeappList(List<CodeCustom> citycodeappList) {
		this.citycodeappList = citycodeappList;
	}

	public List<UserTJ> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<UserTJ> typeList) {
		this.typeList = typeList;
	}

	
	
}
