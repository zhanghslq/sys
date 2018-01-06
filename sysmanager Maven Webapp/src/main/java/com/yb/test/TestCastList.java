package com.yb.test;

import java.util.Arrays;
import java.util.List;


public class TestCastList {
	 public static void main(String[] args) {
	        
	        //字符串
	        String str="";
	        
	        //用逗号将字符串分开，得到字符串数组
	        String[] strs=str.split(",");
	        //将字符串数组转换成集合list
	        List list=Arrays.asList(strs);
	        //查看集合
	        for (int i = 0; i < list.size(); i++) {
	            System.out.println(list.get(i));
	        }
	    }
}
