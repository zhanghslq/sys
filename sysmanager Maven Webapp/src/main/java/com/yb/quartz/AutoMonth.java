package com.yb.quartz;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yb.service.MonthService;

@Component
public class AutoMonth {
	@Autowired
	private MonthService monthService;
/**
 * 每个月1号注入数据，年月
 * 会员活跃情况，与月消费频次占比需要用到
 * @throws Exception
 */
	@Scheduled(cron="0 0 0 1 * ?")//每月一号凌晨
	public  void auto() throws Exception {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		String monthString="";
		if(month<10){
			monthString="0"+month;
		}else {
			monthString+=month;
		}
		monthService.insert(String.valueOf(year),monthString);
	}
}
