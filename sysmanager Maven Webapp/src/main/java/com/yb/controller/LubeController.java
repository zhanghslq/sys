package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.FabricCommunicationException;
import com.yb.entity.Lube;
import com.yb.entity.NotOil;
import com.yb.entity.Station;
import com.yb.service.LubeService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/lube")
@Scope("prototype")
public class LubeController {

	@Resource
	private LubeService lubeService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryLubes")
	public Map<String,List> queryLubes(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="station[]")String[] station,
			String date,Date start,Date end,String people){
		List<Lube> list = new ArrayList<Lube>();
		if(ArryToListUtil.format(station)!=null){
			list = lubeService.queryLubes(date, start, end, ArryToListUtil.format(station),people);
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=lubeService.queryLubes(date, start,end,stationid,people);
		}
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
