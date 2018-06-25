package com.yb.test;

import com.baizhi.test.MD5Utils;

public class Md5Test {
	public static void main(String[] args) {
		String digest = MD5Utils.getDigest("NIQdmHbjshell123");
		System.out.println(digest);
	}
}
