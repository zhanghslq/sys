package com.yb.util;

import java.text.DecimalFormat;

public class DoubleFormatUtil {
	private static DecimalFormat df=new DecimalFormat(".##");
	public static Double format(Double value){
		try {
			if(value!=null){
				String st=df.format(value);
				Double valueOf = Double.valueOf(st);
				return valueOf;
			}
			return 0.0;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.0;
		}
	}
}
