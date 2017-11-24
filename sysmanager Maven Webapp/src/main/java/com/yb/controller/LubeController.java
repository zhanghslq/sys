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

import com.yb.entity.Lube;
import com.yb.service.LubeService;

@Controller
@RequestMapping("/lube")
@Scope("prototype")
public class LubeController {

	@Resource
	private LubeService lubeService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryLubes")
	public Map<String,List> queryLubes(String date,Date start,Date end,String station,String query,String people){
		List<Lube> list = lubeService.queryLubes(date, start, end, station, query,people);
		List<String> dates = new ArrayList<String>();
		List<Double> moneys = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgMoney = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Lube lube : list) {
				dates.add(lube.getMinutes());
				moneys.add(lube.getLubeMoney());
				numbers.add(lube.getLubeNumber());
				avgMoney.add(lube.getAvgMoney());
			}
		}else {
			dates.add("无数据");
			moneys.add(0.0);
			numbers.add(0);
			avgMoney.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("moneys", moneys);
		map.put("numbers", numbers);
		map.put("avgMoney", avgMoney);
		return map;
	}
	
}
