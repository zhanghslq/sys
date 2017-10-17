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
	public Map<String,List> queryLubes(String date,Date start,Date end,String station,String query){
		List<Lube> list = lubeService.queryLubes(date, start, end, station, query);
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgAmount = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Lube lube : list) {
				dates.add(lube.getMinutes());
				amounts.add(lube.getLubeLitre());
				numbers.add(lube.getLubeNumber());
				avgAmount.add(lube.getAvgMoney());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
			numbers.add(0);
			avgAmount.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("amounts", amounts);
		map.put("numbers", numbers);
		map.put("avgAmount", avgAmount);
		return map;
	}
	
}
