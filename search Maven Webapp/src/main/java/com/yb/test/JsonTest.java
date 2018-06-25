package com.yb.test;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonTest {
	/**
	 * 集合转json与数组转json得到结果是相同的，可以通过json进行转换
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arrString=new String[]{"1","2","3"};
		String jsonString = JSON.toJSONString(arrString);
		System.out.println(jsonString);
		List<String> parseArray = JSON.parseArray(jsonString, String.class);
		/*ArrayList<Object> arrayList = new ArrayList<>();
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		String jsonString2 = JSON.toJSONString(arrayList);
		System.out.println(jsonString2);*/

		for (String string : parseArray) {
			System.out.println(string);
		}
	}
}
