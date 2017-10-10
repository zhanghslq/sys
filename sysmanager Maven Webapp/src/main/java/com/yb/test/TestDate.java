package com.yb.test;

import java.util.Calendar;
public class TestDate {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Calendar date=Calendar.getInstance();
		int lastDayofMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);//一个月的最后一天
		int currentDay = date.get(Calendar.DAY_OF_MONTH);//
		int lastSecDayofMonth=lastDayofMonth-1;//倒数第二天
		//获取最后月份
		int lastMonth=date.getActualMaximum(Calendar.MONTH);//11可能代表的是12月
		//获取当前月份
		int currentMonth=date.get(Calendar.MONTH);//当前月份
		System.out.println("lastDayofMonth"+lastDayofMonth);
		System.out.println("currentDay"+currentDay);
		System.out.println("lastSecDayofMonth"+lastSecDayofMonth);
		System.out.println("lastMonth"+lastMonth);
		System.out.println("currentMonth"+currentMonth);
		if(currentDay==lastDayofMonth||currentDay==lastSecDayofMonth&&currentMonth==lastMonth){//当前是12月/
			System.out.println("hello");
		}
		if(1==2||2==3&&2==0){
			System.out.println("hhhh");
		}
	}
}
