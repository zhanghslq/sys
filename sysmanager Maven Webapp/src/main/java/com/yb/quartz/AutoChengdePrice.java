package com.yb.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yb.service.AutoService;

/**
 * @author Administrator
 */
@Component
public class AutoChengdePrice {
	@Resource
	private AutoService autoService;
	@Scheduled(cron="0 30 23 * * ?")//每天晚上11点半
	public void autochengde() throws  Exception{
		autoService.autoChengdePrice();
	}
	@Scheduled(cron="0 30 23 * * ?")//每天晚上11点半点
	public void autobeijing() throws Exception{
		autoService.autoBeijingPrice();
	}
	@Scheduled(cron="0 0 23 * * ?")//每天晚上11点
	public  void test()  {
		autoService.autoWeather();
	}
	@Scheduled(cron="0 0 9 * * ?")//每天早上九点
	public  void autoSendEmail()  {
		autoService.autoSendCoupon();
	}

}
