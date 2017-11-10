package com.yb.test;

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
public class TestJsoup {
	public static void main(String[] args) throws MalformedURLException, IOException {
		Document document = Jsoup.parse(new URL("http://ny.gold600.com/"), 1000);
		Elements select2 = document.select("tr");
		int a=0;
			for (Element element : select2) {
				a++;
				if(a==46){
					System.out.println(element);
					Elements elementsByIndexEquals = element.getElementsByIndexEquals(1);
					String html = elementsByIndexEquals.html();
					System.out.println(html);
					Elements elementsByIndexEquals2 = element.getElementsByIndexEquals(2);
					String html2 = elementsByIndexEquals2.html();
					System.out.println(html2);
					Elements elementsByIndexEquals3 = element.getElementsByIndexEquals(3);
					String html3 = elementsByIndexEquals3.html();
					System.out.println(html3);
					Elements elementsByIndexEquals4 = element.getElementsByIndexEquals(4);
					String html4 = elementsByIndexEquals4.html();
					System.out.println(html4);
					Elements elementsByIndexEquals5 = element.getElementsByIndexEquals(5);
					String html5 = elementsByIndexEquals5.html();
					System.out.println(html5);
				}
			}
		}
	}
