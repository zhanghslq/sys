package com.yb.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Testcd {
	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect("http://www.weather.com.cn/weather1d/101090402.shtml#input").get();
		System.out.println(document);
	}
}
