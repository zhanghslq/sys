package com.yb.quartz;

import java.text.SimpleDateFormat;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yb.dao.WeatherDao;
import com.yb.entity.Weather;
import com.yb.util.JsoupUtil;

@Component
public class AutoWeather {
	@Autowired
	private WeatherDao weatherDao;

	@Scheduled(cron="0 0 23 * * ?")//每天晚上11点
	public  void test() throws Exception {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = simpleDateFormat.format("2018-01-03");
			HttpEntity httpEntity = new StringEntity("time="+format+"","UTF-8");
			String asString = Request.Post("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c")
					.body(httpEntity).setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
			Double avgPre = JsoupUtil.getAvgPre(asString);
			weatherDao.insert(new Weather(format, avgPre, "北京"));
	}
}
