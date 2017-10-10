package com.yb.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		String string="s";
		String string2="s";
		System.out.println(string.equals(string2));
		Date date = new Date();
		System.out.println(date);
		
		
		System.out.println("------------------");
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		String jsonString = JSONObject.toJSONString(map);
		System.out.println(jsonString);
	}
}
