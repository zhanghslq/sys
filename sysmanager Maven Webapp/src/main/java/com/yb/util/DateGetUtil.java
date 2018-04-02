package com.yb.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DateGetUtil {
	public static void main(String args[]) {
	  /*System.out.println();
	  Date date = new Date();
	  System.out.println(date.getMonth());
	  Calendar c = Calendar.getInstance();
	  c.set(Calendar.MONTH,date.getMonth());
	  Date time = c.getTime();
	  System.out.println(time);*/
	  List<String> everyday = DateUtil.getEveryday("2016-01-01","2018-01-01");
	  System.out.println(everyday);
	}
	/*@SuppressWarnings("deprecation")
	public List<String> getDate(Date start,Date end){
		 Calendar c = Calendar.getInstance();
		 int sy = start.getYear();
		 int ey= end.getYear();
		 for (int i = sy; i < ey; i++) {
			
		}
		 int s = start.getMonth();
		 int e = end.getMonth();
		 SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		 List<Date> list = new ArrayList<Date>();
		 for (int i = s; i < e; i++) {
			Calendar time=Calendar.getInstance();
			time.set(Calendar.MONTH, i);
			time.set(Calendar.DAY_OF_MONTH,);
		}
		return null;
	}*/
}