package com.yb.test;

import java.text.DecimalFormat;

public class TestDoubleFormat {
	public static void main(String[] args) {
		DecimalFormat df=new DecimalFormat(".##");
		double d=1252.2563;
		String st = df.format(d);
		System.out.println(st);
	}
}
