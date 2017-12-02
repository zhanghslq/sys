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
import com.yb.entity.Oil;
import com.yb.entity.Oilb;
import com.yb.entity.Station;
import com.yb.service.OilService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/oil")
@Scope("prototype")
public class OilController {

	@Resource
	private OilService oilService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryOils")
	@ResponseBody
	public Map<String, List> queryOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date,String people){
		List<Oil> list = new ArrayList<Oil>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryOils(date, start,end,ArryToListUtil.format(station),people);
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
			list=oilService.queryOils(date, start,end,stationid,people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Double> numbers = new ArrayList<Double>();
		List<Double> avgAmounts = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Oil oil : list) {
				dates.add(oil.getMinutes());
				amounts.add(DoubleFormatUtil.format(oil.getOilLitre()/1000));
				avgAmounts.add(DoubleFormatUtil.format(oil.getOilMoney()));
				numbers.add(oil.getOilNumber());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
			avgAmounts.add(0.0);
			numbers.add(0.0);
		}
		map.put("dates", dates);
		map.put("amounts", amounts);
		map.put("avgAmounts", avgAmounts);
		map.put("numbers", numbers);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryByOils")
	@ResponseBody
	public Map<String, List> queryByOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date){
		
		List<Oilb> list = new ArrayList<Oilb>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryByOils(date, start,end,ArryToListUtil.format(station));
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
			list=oilService.queryByOils(date, start,end,stationid);
		}
		
		List<String> allName = oilService.queryAllName();
		List<String> dates = new ArrayList<String>();
		List<Double> litre92 = new ArrayList<Double>();
		List<Double> litre95 = new ArrayList<Double>();
		List<Double> litre97 = new ArrayList<Double>();
		List<Double> litre0 = new ArrayList<Double>();
		List<Double> litre10 = new ArrayList<Double>();
		List<Double> litre20 = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Oilb oil : list) {
				dates.add(oil.getDate());
				litre0.add(DoubleFormatUtil.format(oil.getLitre0()));
				litre10.add(DoubleFormatUtil.format(oil.getLitre10()));
				litre20.add(DoubleFormatUtil.format(oil.getLitre20()));
				litre92.add(DoubleFormatUtil.format(oil.getLitre92()));
				litre95.add(DoubleFormatUtil.format(oil.getLitre95()));
				litre97.add(DoubleFormatUtil.format(oil.getLitre97()));
			}
		}else {
			dates.add("无数据");
		}
		map.put("dates", dates);
		map.put("allName", allName);
		map.put("litre0",litre0 );
		map.put("litre10", litre10);
		map.put("litre20", litre20);
		map.put("litre92", litre92);
		map.put("litre95", litre95);
		map.put("litre97", litre97);
		return map;
	}
	@RequestMapping("/queryAllName")
	@ResponseBody
	public List<String> queryAllName(){
		List<String> list = oilService.queryAllName();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryzhanbi")
	public List<DataPack> queryzhanbi(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end){
		
		List<Oil> list = new ArrayList<Oil>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryzhanbi( start,end,ArryToListUtil.format(station));
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
			list=oilService.queryzhanbi( start,end,stationid);
		}
		
		List<DataPack> datas = new ArrayList<DataPack>();
		if(list!=null&&list.size()!=0){//有数据
			for (Oil oil : list) {
				DataPack dataPack = new DataPack(oil.getOils(), oil.getOilLitre());
				datas.add(dataPack);
			}
		}else {//条件内无数据
			DataPack dataPack = new DataPack("无数据", 0.0);
			datas.add(dataPack);
		}
		return datas;
	}
}
