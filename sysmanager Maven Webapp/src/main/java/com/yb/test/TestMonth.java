package com.yb.test;

import java.util.Calendar;

public class TestMonth {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		System.out.println(year);
		System.out.println(month);
	}
}
