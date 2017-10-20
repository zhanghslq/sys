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

import com.yb.entity.DataPack;
import com.yb.service.OilPriceService;

@Controller
@RequestMapping("/oilPrice")
@Scope("prototype")
public class OilPriceController {

	@Resource
	private OilPriceService oilPriceService;
	
	@ResponseBody
	@RequestMapping("/queryAllName")
	public List<String> queryAllName(){
		List<String> list = oilPriceService.queryAllName();
		return list;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryPrice")
	@ResponseBody
	public Map<String, List> queryPrice(Date start,Date end,String station,String oilName){
		List<DataPack> list = oilPriceService.queryPrice(start, end, station, oilName);
		List<String> dates = new ArrayList<String>();
		List<Double> prices = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				prices.add(dataPack.getValue());
			}
		}else {
			dates.add("无数据");
			prices.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("prices", prices);
		return map;
	}

}
