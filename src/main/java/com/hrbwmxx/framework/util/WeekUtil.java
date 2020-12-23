package com.hrbwmxx.framework.util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekUtil {

	private int year;
	private int month;
	private List<Week> weeks = new ArrayList<Week>();

	public WeekUtil(){
		Calendar calendar = Calendar.getInstance();
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		init();
	}
	
	public WeekUtil(int year, int month) {
		this.year = year;
		this.month = month - 1;
		init();
	}
	
	private void init(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfWeek;
		if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
			firstDayOfWeek = calendar.getTime();
		} else {
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			firstDayOfWeek = calendar.getTime();
		}
		int index = 1;
		while(addWeek(firstDayOfWeek, index)){
			index++;
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			firstDayOfWeek = calendar.getTime();
		}
	}

	private boolean addWeek(Date day, int index){
		Week week = new Week();
		week.setIndex(index);
		week.setMonday(day);
		weeks.add(week);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		return calendar.get(Calendar.MONTH) == month;
	}
	
	public List<Week> getWeeks(){
		return weeks;
	}
	
	public static void main(String[] args) {
		WeekUtil util = new WeekUtil(2017, 7);
		System.out.println(util.getWeeks().size());
		for(Week week : util.getWeeks()){
			System.out.println(week.getIndex());
			System.out.println(week.getMonday());
			System.out.println(week.getTuesday());
			System.out.println(week.getWednesday());
			System.out.println(week.getThursday());
			System.out.println(week.getFriday());
			System.out.println(week.getSaturday());
			System.out.println(week.getSunday());
		}
	}
}

 