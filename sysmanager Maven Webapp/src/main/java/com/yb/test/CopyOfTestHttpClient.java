package com.yb.test;

import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;

import com.yb.dao.WeatherDao;
public class CopyOfTestHttpClient {
	@Autowired
	private WeatherDao weatherDao;
	
	@org.junit.Test
	public  void test() throws Exception {
			String asString = Request.Post("localhost:8989/server/update/query")
					.execute().returnContent().asString();
			System.out.println(asString);
	}
}
