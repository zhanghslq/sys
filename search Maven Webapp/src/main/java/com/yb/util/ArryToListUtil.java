package com.yb.util;

import java.util.Arrays;
import java.util.List;
/**
 * 用于数组转换为集合，避免空指针的判断
 * @author Administrator
 *
 */
public class ArryToListUtil {
	public static List<String> format(String[] args){
		if(args!=null&&args.length!=0){
			List<String> asList = Arrays.asList(args);
			return asList;
		}
		return null;
	}
	public static List<Integer> formatInteger(Integer[] args){
		if(args!=null&&args.length!=0){
			List<Integer> asList = Arrays.asList(args);
			return asList;
		}
		return null;
	}
}
