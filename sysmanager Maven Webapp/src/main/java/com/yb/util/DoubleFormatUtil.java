package com.yb.util;

import java.text.DecimalFormat;

public class DoubleFormatUtil {
	private static DecimalFormat df=new DecimalFormat(".##");
	public static Double format(Double value){
		String st=df.format(value);
		Double valueOf = Double.valueOf(st);
		return valueOf;
	}
}
