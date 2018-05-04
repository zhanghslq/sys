package com.yb.test;

import java.util.HashMap;
import java.util.Map;



public class TestInteger {
	public static void main(String[] args) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "test");
		map.put(2, "test2");
		String string = map.get(Integer.parseInt("1"));
		System.out.println(string);
	}
}
