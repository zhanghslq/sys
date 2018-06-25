package com.yb.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.PriceDao;
import com.yb.dao.WeatherDao;
import com.yb.entity.Price;
import com.yb.entity.Weather;
import com.yb.service.AutoService;
import com.yb.util.JsoupUtil;

@Service
@Transactional
public class AutoServiceImpl implements AutoService{
	@Autowired
	private PriceDao priceDao;
	@Autowired
	private WeatherDao weatherDao;
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void autoChengdePrice() {
		// TODO Auto-generated method stub
			Document document;
			try {
				document = Jsoup.connect("http://ny.gold600.com/").get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new RuntimeException("连接异常");
			}
			Elements select2 = document.select("tr");
			int a=0;
				for (Element element : select2) {
					a++;
					if(a==46){
						Elements elementsByIndexEquals = element.getElementsByIndexEquals(1);
						String html = elementsByIndexEquals.html();//90#
						Double oil90=0.0;
						Double oil93=0.0;
						Double oil97=0.0;
						Double oil0=0.0;
						if(html!=null&&!html.equals("")&&!html.equals("-")){
							oil90=Double.valueOf(html);
						}
						Elements elementsByIndexEquals2 = element.getElementsByIndexEquals(2);
						String html2 = elementsByIndexEquals2.html();//93#
						if(html2!=null&&!html2.equals("")&&!html2.equals("-")){
							oil93=Double.valueOf(html2);
						}
						Elements elementsByIndexEquals3 = element.getElementsByIndexEquals(3);
						String html3 = elementsByIndexEquals3.html();//97#
						if(html3!=null&&!html3.equals("")&&!html3.equals("-")){
							oil97=Double.valueOf(html3);
						}
						Elements elementsByIndexEquals4 = element.getElementsByIndexEquals(4);
						String html4 = elementsByIndexEquals4.html();//0#
						if(html4!=null&&!html4.equals("")&&!html4.equals("-")){
							oil0=Double.valueOf(html4);
						}
						Elements elementsByIndexEquals5 = element.getElementsByIndexEquals(5);
						String html5 = elementsByIndexEquals5.html();//报价时间
						Date parse;
						try {
							parse = simpleDateFormat.parse(html5);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException("类型转换异常");
						}
						priceDao.insertchengde(new Price(null, oil90, oil93, oil97, oil0, "承德", parse));
						return;
					}
				}
		}
	@Override
	public void autoBeijingPrice() {
		// TODO Auto-generated method stub
		Document document;
		try {
			document = Jsoup.connect("http://ny.gold600.com/").get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException("连接异常");
		}
		Elements select2 = document.select("tr");
		int a=0;
			for (Element element : select2) {
				a++;
				if(a==2){
					Elements elementsByIndexEquals = element.getElementsByIndexEquals(1);
					String html = elementsByIndexEquals.html();//京89
					String[] split = html.split("");
					StringBuffer price=new StringBuffer();
					int i=0;
					for (String string2 : split) {
						i++;
						if(i<=5){
							price.append(string2);
						}
					}
					Double oil89=0.0;
					if(price!=null&&!price.equals("")&&!price.equals("-")){
						oil89=Double.valueOf(price.toString());
					}
					Elements elementsByIndexEquals2 = element.getElementsByIndexEquals(2);
					String html2 = elementsByIndexEquals2.html();//京92
					String[] split92 = html2.split("");
					StringBuffer price92=new StringBuffer();
					int j=0;
					for (String string2 : split92) {
						j++;
						if(j<=5){
							price92.append(string2);
						}
					}
					Double oil92=0.0;
					if(price92!=null&&!price92.equals("")&&!price92.equals("-")){
						oil92=Double.valueOf(price92.toString());
					}
					
					Elements elementsByIndexEquals3 = element.getElementsByIndexEquals(3);
					String html3 = elementsByIndexEquals3.html();//京95
					String[] split95 = html3.split("");
					StringBuffer price95=new StringBuffer();
					int k=0;
					for (String string2 : split95) {
						k++;
						if(k<=5){
							price95.append(string2);
						}
					}
					Double oil95=0.0;
					if(price95!=null&&!price95.equals("")&&!price95.equals("-")){
						oil95=Double.valueOf(price95.toString());
					}
					Elements elementsByIndexEquals4 = element.getElementsByIndexEquals(4);
					String html4 = elementsByIndexEquals4.html();//0#
					String[] split0 = html4.split("");
					StringBuffer price0=new StringBuffer();
					int m=0;
					for (String string2 : split0) {
						m++;
						if(m<=5){
							price0.append(string2);
						}
					}
					Double oil0=0.0;
					if(price0!=null&&!price0.equals("")&&!price0.equals("-")){
						oil0=Double.valueOf(price0.toString());
					}
					Elements elementsByIndexEquals5 = element.getElementsByIndexEquals(5);
					String html5 = elementsByIndexEquals5.html();//报价时间
					Date parse;
					try {
						parse = simpleDateFormat.parse(html5);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException("日期转换异常");
					}
					priceDao.insertbeijing(new Price(null, oil89, oil92, oil95, oil0, "北京", parse));
					return;
				}
			}
	}

	@Override
	public void autoWeather() {
		// TODO Auto-generated method stub
		SimpleDateFormat sF = new SimpleDateFormat("yyyy-MM-dd");
		String format = sF.format(new Date());
		HttpEntity httpEntity = new StringEntity("time="+format+"","UTF-8");
		String asString;
		try {
			asString = Request.Post("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c")
					.body(httpEntity).setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("连接异常");
		}
		Double avgPre;
		try {
			avgPre = JsoupUtil.getAvgPre(asString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取数据异常");
		}
		weatherDao.insert(new Weather(format, avgPre, "北京"));
	}
	
}
