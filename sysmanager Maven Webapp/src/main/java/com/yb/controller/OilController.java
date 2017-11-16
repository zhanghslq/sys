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
import com.yb.entity.Oil;
import com.yb.service.OilService;

@Controller
@RequestMapping("/oil")
@Scope("prototype")
public class OilController {

	@Resource
	private OilService oilService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryOils")
	@ResponseBody
	public Map<String, List> queryOils(Date start,Date end,String date,String station,String query){
		List<Oil> list = oilService.queryOils(date, start, end, station, query);
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Double> numbers = new ArrayList<Double>();
		List<Double> avgAmounts = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Oil oil : list) {
				dates.add(oil.getMinutes());
				amounts.add(oil.getOilLitre());
				avgAmounts.add(oil.getOilMoney());
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
	public Map<String, List> queryByOils(Date start,Date end,String date,String station,String query){
		
		List<Oil> list = oilService.queryByOils(date, start, end, station, query);
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Oil oil : list) {
				dates.add(oil.getMinutes());
				amounts.add(oil.getOilLitre());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
		}
		map.put("dates", dates);
		map.put("amounts", amounts);
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
	public List<DataPack> queryzhanbi(Date start,Date end,String station,String query){
		
		List<Oil> list = oilService.queryzhanbi(start, end, station, query);
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
