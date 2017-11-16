package com.yb.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yb.dao.PriceDao;
import com.yb.entity.Price;

@Component
public class AutoChengdePrice {
	@Autowired
	private PriceDao priceDao;
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Scheduled(cron="0 0 8 * * ?")//每天za上8点
	public void autochengde() throws  Exception{
		Document document = Jsoup.connect("http://ny.gold600.com/").get();
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
					Date parse = simpleDateFormat.parse(html5);
					priceDao.insertchengde(new Price(null, oil90, oil93, oil97, oil0, "承德", parse));
					return;
				}
			}
	}
	
}
