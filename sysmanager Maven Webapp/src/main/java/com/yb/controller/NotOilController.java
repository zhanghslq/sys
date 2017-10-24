package com.yb.controller;

import java.math.BigDecimal;
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
import com.yb.entity.NotOil;
import com.yb.service.NotOilService;

@Controller
@RequestMapping("/notOil")
@Scope("prototype")
public class NotOilController {
	@Resource
	private NotOilService notOilService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryNotOils")
	public Map<String, List> queryNotOils(String date,Date start,Date end,String station,String query){
		List<NotOil> list = notOilService.queryNotOils(date, start, end, station, query);
		List<String> dates = new ArrayList<String>();
		List<BigDecimal> moneys = new ArrayList<BigDecimal>();
		List<BigDecimal> avgMoney = new ArrayList<BigDecimal>();
		List<Integer> numbers = new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				moneys.add(notOil.getNotOilMoney());
				avgMoney.add(notOil.getAvgMoney());
				numbers.add(notOil.getNotOilNumber());
			}
		}else {
			dates.add("无数据");
			moneys.add(BigDecimal.valueOf(0));
			avgMoney.add(BigDecimal.valueOf(0));
			numbers.add(0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("moneys", moneys);
		map.put("avgMoney", avgMoney);
		map.put("numbers", numbers);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryByDepartmentName")
	public Map<String, List> queryByDepartmentName(String date,Date start,Date end,String station,String query,String departmentName){
		List<NotOil> list = notOilService.queryByDepartmentName(date, start, end, station, query, departmentName);
		List<String> dates = new ArrayList<String>();
		List<BigDecimal> amounts = new ArrayList<BigDecimal>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				amounts.add(notOil.getNotOilMoney());
			}
		}else {
			dates.add("无数据");
			amounts.add(BigDecimal.valueOf(0));
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("amounts", amounts);
		return map;
		
	}
	@ResponseBody
	@RequestMapping("queryAllName")
	public List<String> queryAllName(){
		List<String> list = notOilService.queryAllName();
		return list;
		
	}
	/**
	 * 便利店的开单率
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryRate")
	@ResponseBody
	public Map<String,List> queryRate(Date start,Date end,String date,String station,String query){
		List<NotOil> list = notOilService.queryRate(date, start, end, station, query);
		List<String> dates = new ArrayList<String>();
		List<BigDecimal> rates = new ArrayList<BigDecimal>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				rates.add(notOil.getAvgMoney());
			}
		}else {
			dates.add("无数据");
			rates.add(BigDecimal.valueOf(0));
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("rates", rates);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryTop")
	@ResponseBody
	public Map<String, List> queryTop(Date start,Date end,String station,String query){
		List<DataPack> list = notOilService.queryTop(start, end, station, query);
		List<String> names = new ArrayList<String>();
		List<Double> data = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				names.add(dataPack.getName());
				data.add(dataPack.getValue());
			}
		}else {
			names.add("无数据");
			data.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("names", names);
		map.put("data", data);
		return map;
	}
}
