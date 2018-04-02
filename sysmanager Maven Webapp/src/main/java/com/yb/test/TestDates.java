package com.yb.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDates {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(new Date());
		System.out.println(format);
	}
}
