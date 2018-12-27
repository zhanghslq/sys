package com.yb.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yb.service.HeartService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.util.DateFormatUtils;

import javax.annotation.Resource;

@Controller
@RequestMapping("/time")
@Scope("prototype")
public class TimeController {
	@Resource
	private HeartService heartService;
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
	private  String getEight(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,8);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		String format = simpleDateFormat.format(calendar.getTime());
		return format;
	}
	
	private  String getEleven(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,11);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		String format = simpleDateFormat.format(calendar.getTime());
		return format;
	}
	
	private  String getFifteen(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,15);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		String format = simpleDateFormat.format(calendar.getTime());
		return format;
	}
	
	private  String getZero(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		String format = simpleDateFormat.format(calendar.getTime());
		return format;
	}
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("/queryOne")
	public String queryOne(){
		Date date = new Date();
		int hours = date.getHours();
		if(hours<8){
		 return	getZero();
		}else if (hours<11) {
			return getEight();
		}else if(hours<15){
			return getEleven();
		}else{
			return getFifteen();
		}
	}
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("/queryTwo")
	public String queryTwo(){
		Date date = new Date();
		int hours = date.getHours();
		if(hours<11){
			return	getZero();
		}else{
			return getEleven();
		}
	}
	@ResponseBody
	@RequestMapping("/queryThree")
	public String queryThree(){
		 return	getZero();
	}
	@ResponseBody
	@RequestMapping("/querypro")
	public String querypro(){
		Date monthStart = DateFormatUtils.getMonthStart();
		String format = simpleDateFormat.format(monthStart);
		return format;
	}
	@ResponseBody
	@RequestMapping("/queryTime")
	public String queryTime(String name){
        String s = heartService.queryTime(name);
        if(s==null){
            s=simpleDateFormat.format(new Date());
        }
        return s;
	}
}
