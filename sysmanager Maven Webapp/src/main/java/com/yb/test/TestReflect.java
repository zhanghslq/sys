package com.yb.test;

import java.lang.reflect.Field;

import com.yb.entity.OilAndVip;

public class TestReflect {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Field declaredField = OilAndVip.class.getDeclaredField("notVipAvgLitre");
		
		System.out.println(declaredField.getGenericType());
	}
}
