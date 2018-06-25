package com.yb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodayUtil {
	public String getToday(){
		String format = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		return format;
	}
}
