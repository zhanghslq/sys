package com.yb.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JSONTest {
	public static void main(String[] args) {
		/*String jsonStringWithDateFormat = JSONObject.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd");
		System.out.println(jsonStringWithDateFormat);*/
		long unixstamp=1526681700;
        Date dt=new Date(unixstamp*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println(sdf.format(dt));
	}
}
