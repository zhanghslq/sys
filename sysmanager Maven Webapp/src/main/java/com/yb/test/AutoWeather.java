package com.yb.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yb.dao.WeatherDao;
import com.yb.entity.Weather;
import com.yb.util.DateUtil;
import com.yb.util.JsoupUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class AutoWeather {
	@Autowired
	private WeatherDao weatherDao;

	@Test
	public  void test() throws Exception {
		List<String> everyday = DateUtil.getEveryday("2018-01-03","2018-01-30");
		for (String string : everyday) {
			/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");*/
			String format = string;
			HttpEntity httpEntity = new StringEntity("time="+format+"","UTF-8");
			String asString = Request.Post("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c")
					.body(httpEntity).setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
			Double avgPre = JsoupUtil.getAvgPre(asString);
			System.out.println(avgPre);
			weatherDao.insert(new Weather(format, avgPre, "北京"));
		}
			
	}
}
