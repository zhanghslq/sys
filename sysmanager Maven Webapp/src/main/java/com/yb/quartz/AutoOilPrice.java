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
public class AutoOilPrice {
	@Autowired
	private PriceDao priceDao;
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Scheduled(cron="0 0 8 * * ?")//每天zao上8点
	public void autobeijing() throws Exception{
		Document document = Jsoup.connect("http://ny.gold600.com/").get();
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
					Date parse = simpleDateFormat.parse(html5);
					priceDao.insertbeijing(new Price(null, oil89, oil92, oil95, oil0, "北京", parse));
					return;
				}
			}
		}
	
}
