package com.yb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonArryTest1 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		list.add("赵六");
		String jsonString = JSONArray.toJSONString(list);
		System.out.println(jsonString);
		
		List<String> parse = (List<String>)JSONArray.parse(jsonString);
		List<String> parseArray = JSONArray.parseArray(jsonString, String.class);
		/*String jsonString2 = JSON.toJSONString(list);
		System.out.println(jsonString2);
		String jsonString3 = JSONObject.toJSONString(list);
		System.out.println(jsonString3);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", list);
		String jsonString4 = JSON.toJSONString(map);
		System.out.println(jsonString4);*/
		System.out.println(parseArray);
		System.out.println(JSONArray.parseArray("null", String.class)==null);
		
	}
}
