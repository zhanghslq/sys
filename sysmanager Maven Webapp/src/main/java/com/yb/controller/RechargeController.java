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

import com.yb.entity.Recharge;
import com.yb.service.RechargeService;

@Controller
@RequestMapping("/recharge")
@Scope("prototype")
public class RechargeController {
	@Resource
	private RechargeService rechargeService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(String date,Date start,Date end){
		List<Recharge> list = rechargeService.query(date, start, end);
		List<String> dates = new ArrayList<String>();
		List<Double> tradeAmounts = new ArrayList<Double>();
		List<Double> avgAmounts = new ArrayList<Double>();
		List<Integer> tradeNumber = new ArrayList<Integer>();
		Map<String,List> map= new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Recharge recharge : list) {
				dates.add(recharge.getDate());
				tradeAmounts.add(recharge.getTotalAmount());
				avgAmounts.add(recharge.getAvgAmount());
				tradeNumber.add(recharge.getTradeNumber());
			}
		}else {
			dates.add("无数据");
			tradeAmounts.add((double) 0);
			avgAmounts.add((double) 0);
			tradeNumber.add(0);
		}
		map.put("dates", dates);
		map.put("tradeAmounts", tradeAmounts);
		map.put("avgAmounts", avgAmounts);
		map.put("tradeNumber", tradeNumber);
		return map;
	}
}
