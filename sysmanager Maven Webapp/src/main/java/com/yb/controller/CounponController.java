package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Coupon;
import com.yb.entity.DataPack;
import com.yb.service.CouponService;

@Controller
@RequestMapping("/coupon")
@Scope("prototype")
public class CounponController {
	@Resource
	private CouponService couponService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(Date start,Date end){
		List<Coupon> list = couponService.query(start, end);
		List<String> days = new ArrayList<String>();
		List<Double> all = new ArrayList<Double>();
		List<Double> used = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Coupon coupon: list) {
				days.add(coupon.getDays());
				all.add(coupon.getAllMoney());
				used.add(coupon.getUsedMoney());
			}
		}else {
			days.add("无数据");
			all.add(0.0);
			used.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("all", all);
		map.put("used", used);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryZhanbi")
	@ResponseBody
	public Map<String,List> queryZhanbi(){
		List<DataPack> list = couponService.queryZhanbi();
		List<String> names = new ArrayList<String>();
		for (DataPack dataPack : list) {
			names.add(dataPack.getName());
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("names", names);
		map.put("data", list);
		return map;
	}
}
