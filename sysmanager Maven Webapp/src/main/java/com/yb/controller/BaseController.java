package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.AmountPack;
import com.yb.entity.DataPack;
import com.yb.entity.TradeAmount;
import com.yb.service.BaseService;

@Controller
@Scope("prototype")
@RequestMapping("/base")
public class BaseController {
	@Resource
	private BaseService baseService;
	@ResponseBody
	@RequestMapping("/querydata")
	public Map<String, Object> querydata(String date,Date start,Date end,String oilName,
			String station,HttpServletRequest request,HttpServletResponse response){
		//需要判断date的值，时按日展示还是按月或者年展示
		List<AmountPack> list= baseService.zoushi(date,station, oilName, start, end);
		List <Date> dates = new ArrayList<Date>();
		
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> litres = new ArrayList<Double>();
		List<Double> tradeAmounts = new ArrayList<Double>();
		List<Double> notOilAmounts = new ArrayList<Double>();
		List<Double> lubeAmounts = new ArrayList<Double>();
		Map<String,Object> map = new HashMap<String,Object>();
		for (AmountPack amountPack : list) {
			dates.add(amountPack.getDate());
			numbers.add(amountPack.getTradeNumber());
			litres.add(amountPack.getTradeLitre());
			tradeAmounts.add(amountPack.getTradeAmount());
			notOilAmounts.add(amountPack.getNotOilAmount());
			lubeAmounts.add(amountPack.getLubeAmount());
		}
		map.put("numbers", numbers);
		map.put("litres", litres);
		map.put("tradeAmounts", tradeAmounts);
		map.put("dates", dates);
		map.put("notOilAmounts", notOilAmounts);
		map.put("lubeAmounts", lubeAmounts);
		return map;
	}
	@ResponseBody
	@RequestMapping("/queryzhanbi")
	public Map<String,List<DataPack>> queryzhanbi(String station,String date,Date start, Date end){
		//需要判断维度，日月年
		List<TradeAmount> list = baseService.zhanbi(station, start);
		List<DataPack> numbers = new ArrayList<DataPack>();//封装的三个数据类型
		List<DataPack> litres = new ArrayList<DataPack>();
		List<DataPack> tradeAmounts = new ArrayList<DataPack>();
		
		for (TradeAmount tradeAmount : list) {
			numbers.add(new DataPack("交易笔数", Double.valueOf(tradeAmount.getTradeNumber())));
			litres.add(new DataPack("交易升数",tradeAmount.getTradeLitre()));
			tradeAmounts.add(new DataPack("交易金额",tradeAmount.getTradeAmount()));
		}
		HashMap<String,List<DataPack>> map = new HashMap<String,List<DataPack>>();
		return map;
	}
	//油枪效率，按照小时为单位来计算
	public String oilGun(){
		
		
		return null;
	}
	//燃油月度分布
	public String oilMonth(){
		
		return null;
	}
	//目标完成率
	public String target(){
		
		return null;
	}
	
}
