package com.yb.test;

import java.io.IOException;

import com.baizhi.test.MD5Utils;

public class Testcd {
	public static void main(String[] args) throws IOException {
		String digest = MD5Utils.getDigest("IbeDTR"+"123");
		System.out.println(digest);
	}
}
