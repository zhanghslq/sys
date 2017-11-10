package com.yb.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 平均降水量的获取
 * @author Administrator
 *
 */
public class AvgWeather {
	public static Double getAvgPre(String[] args) throws MalformedURLException, IOException {
		Document document = Jsoup.parse(new URL("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c"), 1000);
		Elements select2 = document.select("tr");
		int a=0;
			for (Element element : select2) {
				a++;
				if(a==18){
					Elements elementsByIndexEquals = element.getElementsByIndexEquals(1);
					String html = elementsByIndexEquals.html();
					Double avgDouble=Double.valueOf(html);
					return avgDouble;
				}
			}
			return null;
		}
	}
