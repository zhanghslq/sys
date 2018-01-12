package com.yb.test;

import java.util.ArrayList;

public class TestListNull {
	public static void main(String[] args) {
		ArrayList<Object> arrayList = new ArrayList<>();
		arrayList.add(null);
		arrayList.add(null);
		arrayList.add(null);
		System.out.println(arrayList);
	}
}
