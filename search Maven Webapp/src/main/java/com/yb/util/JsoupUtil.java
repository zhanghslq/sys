package com.yb.util;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 平均降水量的获取
 * @author Administrator
 *
 */
public class JsoupUtil {
	public static Double getAvgPre(String args) throws MalformedURLException, IOException {
		Document document = Jsoup.parse(args);
		Elements select2 = document.select("tr");
		int a=0;
			for (Element element : select2) {
				a++;
				if(a==18){
					Elements elementsByIndexEquals = element.getElementsByIndexEquals(1);
					String html = elementsByIndexEquals.html();
					if(html!=null && !html.equals("")){
						Double avgDouble=Double.valueOf(html);
						return avgDouble;
					}else {
						return 0.0;
					}
				}
			}
			return 0.0;
		}
	public static Double getToday(String args) throws MalformedURLException, IOException {
		Document document = Jsoup.parse(args);
		Elements select2 = document.select("tr");
		int a=0;
			for (Element element : select2) {
				a++;
				if(a==18){
					Elements elementsByIndexEquals = element.getElementsByIndexEquals(1);
					String html = elementsByIndexEquals.html();
					if(html!=null && !html.equals("")){
						Double avgDouble=Double.valueOf(html);
						return avgDouble;
					}else {
						return 0.0;
					}
				}
			}
			return 0.0;
		}
	}
