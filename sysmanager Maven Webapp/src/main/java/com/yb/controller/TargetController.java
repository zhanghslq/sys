package com.yb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.Station;
import com.yb.service.StationService;
import com.yb.service.TargetService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@Scope("prototype")
@RequestMapping("/target")
@Controller
public class TargetController {

	@Resource
	private TargetService targetService;
	@Resource
	private StationService stationService; 
	
	@RequestMapping("/queryTarget")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Map<String, List> queryTarget(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station){
		List<DataPack> list = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list=targetService.queryTarget(ArryToListUtil.format(station));
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
			list=targetService.queryTarget(stationid);
		}
		List<String> days = new ArrayList<String>();
		List<Double> data=new ArrayList<Double>();
		Double queryRate = targetService.queryRate(ArryToListUtil.format(station));
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				days.add(dataPack.getName());
				data.add(DoubleFormatUtil.format(dataPack.getValue()*100));
			}
			days.add("年度目标完成率");
			data.add(DoubleFormatUtil.format(queryRate*100));
		}else {
			days.add("无数据");
			data.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("data", data);
		return map;
	}
}
