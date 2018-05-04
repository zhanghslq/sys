package com.yb.test;

import java.io.IOException;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class Testcd {
	public static void main(String[] args) throws IOException {
		byte[] bytes = Sha256Hash.toBytes("test");
		String string = Sha256Hash.toString(bytes);
		System.out.println(string);
	}
}
