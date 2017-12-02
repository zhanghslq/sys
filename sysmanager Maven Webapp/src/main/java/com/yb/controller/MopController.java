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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;
import com.yb.entity.Oil;
import com.yb.entity.Station;
import com.yb.service.MopService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/mop")
@Scope("prototype")
public class MopController {

	@Resource
	private MopService mopService;
	@Resource
	private StationService stationService;
	
	
	@ResponseBody
	@RequestMapping("/queryAllMop")
	public List<String> queryAllMop(){
		List<String> list = mopService.queryAllMop();
		return list;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryMop")
	public Map<String, List> queryMop(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String query){
		List<DataPack> list = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list=mopService.queryMop(start, end,ArryToListUtil.format(station));
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
			list=mopService.queryMop(start, end,stationid);
		}
		List<String> mop = mopService.queryAllMop();
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("mop", mop);
		map.put("data", list);
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryHHT")
	@ResponseBody
	public Map<String,List> queryHHT(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end){
		HHT hht=null;
		List<DataPack> mophht=null;
		List<DataPack> mopipt=null;
		if(ArryToListUtil.format(station)!=null){
			hht=mopService.queryHHT(start, end,ArryToListUtil.format(station));
			mophht=mopService.queryMophht(start, end, ArryToListUtil.format(station));
			mopipt=mopService.queryMopipt(start, end, ArryToListUtil.format(station));
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
			hht=mopService.queryHHT(start, end,stationid);
			mophht=mopService.queryMophht(start, end, stationid);
			mopipt=mopService.queryMopipt(start, end, stationid);
		}
		
		List<DataPack> all = new ArrayList<DataPack>();
		List<String> mop = mopService.queryAllMop();
		if(hht!=null){
			all.add(new DataPack("HHT支付",DoubleFormatUtil.format(hht.getHhtMoney())));
			all.add(new DataPack("IPT支付",DoubleFormatUtil.format(hht.getIptMoney())));
		}else {
			all.add(new DataPack("无数据", 0.0));
		}
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("ipt", mopipt);
		map.put("hht", mophht);
		map.put("all", all);
		map.put("mop", mop);
		return map;
	}
}
