package com.hrbwmxx.framework.util;

import java.util.Calendar;
import java.util.Date;

import org.omg.Messaging.SyncScopeHelper;

public class Week {
	private int index; // 第几周
	private Date monday; // 周一

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Date getMonday() {
		return monday;
	}

	public String  getMondayStr( ) {
		return  TimeUtil.getDay(monday);
	}

	public String getTuesdayStr() {
		return TimeUtil.getDay(add(monday, 1));
	 
	}

	public String getWednesdayStr() {
		return TimeUtil.getDay(add(monday, 2));
	}

	public String getThursdayStr() {
		return TimeUtil.getDay(add(monday, 3));
	}

	public String getFridayStr() {
		return TimeUtil.getDay(add(monday, 4));
	}

	public String getSaturdayStr() {
		return TimeUtil.getDay(add(monday, 5));
	}

	public String getSundayStr() {
		return TimeUtil.getDay(add(monday, 6));
	}
	public void setMonday(Date monday) {
		this.monday = monday;
	}

	public Date getTuesday() {
		return add(monday, 1);
	}

	public Date getWednesday() {
		return add(monday, 2);
	}

	public Date getThursday() {
		return add(monday, 3);
	}

	public Date getFriday() {
		return add(monday, 4);
	}

	public Date getSaturday() {
		return add(monday, 5);
	}

	public Date getSunday() {
		return add(monday, 6);
	}
	
	
	private Date add(Date date, int number){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, number);
		return calendar.getTime();
	}
	 
}
