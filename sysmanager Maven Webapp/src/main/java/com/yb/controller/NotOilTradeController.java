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

import com.yb.entity.NotOilTrade;
import com.yb.service.NotOilTradeService;

@Controller
@RequestMapping("/notOilTrade")
@Scope("prototype")
public class NotOilTradeController {
	@Resource
	private NotOilTradeService notOilTradeService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/query")
	@ResponseBody
	public Map<String,List> query(String station,String date,Date start,Date end){
		List<NotOilTrade> list = notOilTradeService.query(station, date, start, end);
		List<String> dates = new ArrayList<String>();
		List<Double> singleAmounts = new ArrayList<Double>();
		List<Integer> tradeNumbers = new ArrayList<Integer>();
		List<Double> tradeAmounts = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list.size()!=0){
			for (NotOilTrade notOilTrade : list) {
				dates.add(notOilTrade.getDate());
				singleAmounts.add(notOilTrade.getSingleAmount());
				tradeAmounts.add(notOilTrade.getTradeAmount());
				tradeNumbers.add(notOilTrade.getTradeNumber());
			}
		}else{
			dates.add("无数据");
			singleAmounts.add((double) 0);
			tradeAmounts.add((double) 0);
			tradeNumbers.add(0);
		}
		map.put("dates", dates);
		map.put("singleAmounts", singleAmounts);
		map.put("tradeAmounts", tradeAmounts);
		map.put("tradeNumbers", tradeNumbers);
		return map;
	}
}
