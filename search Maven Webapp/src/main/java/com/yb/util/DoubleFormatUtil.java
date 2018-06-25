package com.yb.util;

import java.text.DecimalFormat;

public class DoubleFormatUtil {
	private static DecimalFormat df=new DecimalFormat("#.##");
	private static DecimalFormat df0=new DecimalFormat("###");
	public static Double format(Double value){
		try {
			if(value!=null){
				String st=df.format(value);
				Double valueOf = Double.valueOf(st);
				return valueOf;
			}
			return 0.0;
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	public static Double formatZero(Double value){
		try {
			if(value!=null){
				String st=df0.format(value);
				Double valueOf = Double.valueOf(st);
				return valueOf;
			}
			return 0.0;
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	public static String formatString(Double value){
		String format = String.format("%.2f", value);
		return format;
	}
	public static void main(String[] args) {
		Double format= 28.000000000000004;
		System.out.println(format);
		System.out.println(String.format("%.2f", format));
	}
}
