package com.yb.test;

import com.baizhi.test.MD5Utils;


public class Test{
	public static void main(String[] args) {
		String digest = MD5Utils.getDigest("1EqHRp"+123);
		System.out.println(digest);
	}

}