package com.yb.test;

import com.baizhi.test.MD5Utils;


public class Test{
	public static void main(String[] args) {
		String digest = MD5Utils.getDigest("g9dqI8"+"bjshell123");
		System.out.println(digest);
	}

}