package com.yb.util;

import java.util.Calendar;
import java.util.Date;

public class DateFormatUtils {
	public static void main(String[] args) {
		Date weekStart = getMonthStart();
		System.out.println(weekStart);
	}
	public static Date getMonthStart(){
		   Calendar c = Calendar.getInstance();    
	       c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	       c.set(Calendar.HOUR_OF_DAY, 0);
	       c.set(Calendar.MINUTE, 0);
	       c.set(Calendar.SECOND, 0);
	       Date time = c.getTime();
		   return time;
	}
	public static Date getDayStart(){
		   Calendar c = Calendar.getInstance();
	       c.set(Calendar.HOUR_OF_DAY, 0);
	       c.set(Calendar.MINUTE, 0);
	       c.set(Calendar.SECOND, 0);
	       Date time = c.getTime();
		   return time;
	}
	//获取一周前的零点
	public static Date getWeekStart(){
		   Calendar c = Calendar.getInstance();
		   c.add(Calendar.DAY_OF_YEAR, -6);
	       c.set(Calendar.HOUR_OF_DAY, 0);
	       c.set(Calendar.MINUTE, 0);
	       c.set(Calendar.SECOND, 0);
	       Date time = c.getTime();
	       return time;
	}
	public static Date getYearStart(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date time = c.getTime();
		return time;
	}
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
