package com.yb.test;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yb.dao.WeatherDao;
import com.yb.entity.Weather;
import com.yb.util.DateUtil;
import com.yb.util.JsoupUtil;
public class CopyOfTestHttpClient {
	@Autowired
	private WeatherDao weatherDao;
	
	@org.junit.Test
	public  void test() throws Exception {
			HttpEntity httpEntity = new StringEntity("time="+"2016-11-06"+"","UTF-8");
			String asString = Request.Post("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c")
					.body(httpEntity).setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
			System.out.println(asString);
	}
}
