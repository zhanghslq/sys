package com.yb.test;

import com.baizhi.test.MD5Utils;

public class Md5Test {
	public static void main(String[] args) {
		String digest = MD5Utils.getDigest("Of6o2Hbjshell" +
                "");
		System.out.println(digest);
	}
}
