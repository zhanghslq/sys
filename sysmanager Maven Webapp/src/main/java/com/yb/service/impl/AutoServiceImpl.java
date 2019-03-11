package com.yb.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.yb.dao.CouponDayDao;
import com.yb.dao.DailyDataDao;
import com.yb.entity.*;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.mail.SendJavaMail;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.yb.service.AutoService;
import com.yb.util.JsoupUtil;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
@Transactional
public class AutoServiceImpl implements AutoService{
	@Autowired
	private PriceDao priceDao;
	@Autowired
	private CouponDayDao couponDayDao;
	@Autowired
	private WeatherDao weatherDao;
	@Autowired
	private DailyDataDao dailyDataDao;

	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
							parse=new Date();
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
						parse=new Date();
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

	@Override
	public void autoSendCoupon() {
		List<CouponDaySend> couponDaySends = couponDayDao.queryDataYesterDay();
		HashMap<String, String> map = new LinkedHashMap<>();
		map.put("day","日期");
		map.put("station_id","油站");
		map.put("oils","油号");
		map.put("oilMoney","应收金额");
		map.put("real_pay","实收金额");
		map.put("oilLitre","销量");
		map.put("price1","优惠前单价");
		map.put("price2","优惠后单价");

		map.put("direct_discount","直降优惠");
		map.put("oil_discount","油品优惠");
		map.put("notoil_discount","非油优惠");
		map.put("ordinary_discount","普通优惠");
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        instance.add(Calendar.DATE,-1);
        Date time = instance.getTime();
        HSSFWorkbook sheets = EchartsExportExcelUtil.excelExport(couponDaySends, map, "优惠信息", time, time);
        FileOutputStream fout = null;
        String fileName="/opt/excel/"+simpleDateFormat.format(new Date())+".xls";
        try {
            fout = new FileOutputStream(fileName);
            sheets.write(fout);
            fout.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(couponDaySends.size()==0||couponDaySends==null){
			try {
				Address[] addresses=new InternetAddress[2];
				addresses[0]=new InternetAddress("962203169@qq.com");
				addresses[1]=new InternetAddress("shijian.zhang@ykd.me");
				SendJavaMail.send(fileName,"昨天的优惠券数据为空，请检查",addresses,"优惠券数据");
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}else{
			try {
				Address[] addresses=new InternetAddress[3];
				addresses[0]=new InternetAddress("962203169@qq.com");
				addresses[1]=new InternetAddress("ying.miao@bjshell.com");
				addresses[2]=new InternetAddress("dan.han@bjshell.com");
				SendJavaMail.send(fileName,"昨天的优惠券数据，请查收",addresses,"优惠券数据");
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}

    }

	@Override
	public void autoSendDailyData() {
		List<Oil> byOilsLitre = dailyDataDao.queryByOilsLitre();
		List<Oil> oils = dailyDataDao.queryOilByStation();
		List<NotOil> notOils = dailyDataDao.queryShopByStation();
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        instance.add(Calendar.DATE,-1);
        Date time = instance.getTime();
        String formatTime = simpleDateFormat.format(time);
        LinkedHashMap<String, DailyData> map = new LinkedHashMap<>();

		for (Oil oil : oils) {
			map.put(oil.getStationID(),new DailyData(oil.getStationID(),formatTime,oil.getOilLitre(),null,null,null,0.0,null));
		}

        for (Oil oil : byOilsLitre) {
            DailyData dailyData = map.get(oil.getStationID());
           if("92#".equals(oil.getOils())){
                dailyData.setLitre92(oil.getOilLitre());
           }else if("95#".equals(oil.getOils())){
               dailyData.setLitre95(oil.getOilLitre());
           }else if("98#".equals(oil.getOils())){
               dailyData.setLitre98(oil.getOilLitre());
           }else if("0#".equals(oil.getOils())||"-10#".equals(oil.getOils())||"-20#".equals(oil.getOils())||"-35#".equals(oil.getOils())){
               Double oilLitre = oil.getOilLitre();
               Double diesel = dailyData.getDiesel();
               diesel+=oilLitre;
               dailyData.setDiesel(diesel);

           }
           map.put(oil.getStationID(), dailyData);
        }

        for (NotOil notOil : notOils) {
            DailyData dailyData = map.get(notOil.getStationID());
            dailyData.setShopSales(notOil.getNotOilMoney());
            map.put(notOil.getStationID(),dailyData);
        }
        List<DailyData> dailyData = new ArrayList<>(map.values());
        HashMap<String, String> mapTitle = new LinkedHashMap<>();
        mapTitle.put("dateStr","日期");
        mapTitle.put("station","油站编号");
        mapTitle.put("litreAll","全部燃油销量");
        mapTitle.put("litre92","全部92#汽油销量");
        mapTitle.put("litre95","全部95#汽油销量");
        mapTitle.put("litre98","全部98#汽油销量");
        mapTitle.put("diesel","全部柴油销量");
        mapTitle.put("shopSales","便利店销售额");

        HSSFWorkbook sheets = EchartsExportExcelUtil.excelExport(dailyData, mapTitle, "每日简报", time, time);
        FileOutputStream fout = null;
        //String fileName="/opt/daily/"+simpleDateFormat.format(new Date())+".xls";
        String fileName="E:/"+simpleDateFormat.format(new Date())+".xls";
        try {
            fout = new FileOutputStream(fileName);
            sheets.write(fout);
            fout.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Address[] addresses=new InternetAddress[1];
            addresses[0]=new InternetAddress("962203169@qq.com");
            //addresses[1]=new InternetAddress("zhaoyang.yu@bjshell.com");
            SendJavaMail.send(fileName,"昨天的每日简报，请查收",addresses,"每日简报");
        } catch (AddressException e) {
            e.printStackTrace();
        }



    }
}
