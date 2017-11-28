package com.yb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/notOil")
@Scope("prototype")
public class NotOilController {
	@Resource
	private NotOilService notOilService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryNotOils")
	public Map<String, List> queryNotOils(String date,Date start,Date end,String station,String query,String people){
		List<NotOil> list = notOilService.queryNotOils(date, start, end, station, query,people);
		List<String> dates = new ArrayList<String>();
		List<Double> moneys = new ArrayList<Double>();
		List<Double> avgMoney = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				moneys.add(DoubleFormatUtil.format(notOil.getNotOilMoney()));
				avgMoney.add(DoubleFormatUtil.format(notOil.getAvgMoney()));
				numbers.add(notOil.getNotOilNumber());
			}
		}else {
			dates.add("无数据");
			moneys.add(0.0);
			avgMoney.add(0.0);
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
	@RequestMapping("/queryExceptLube")
	public Map<String,List> queryExceptLube(String date,Date start,Date end,String station,String query,String people){
		List<DataPack> list = notOilService.queryExceptLube(date, start, end, station, query, people);
		List<String> minutes = new ArrayList<String>();
		List<Double> avgMoney = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				minutes.add(dataPack.getName());
				avgMoney.add(DoubleFormatUtil.format(dataPack.getValue()));
			}
		}else {
			minutes.add("无数据");
			avgMoney.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("minutes", minutes);
		map.put("avgMoney", avgMoney);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryByDepartmentName")
	public Map<String, List> queryByDepartmentName(String date,Date start,Date end,String station,String query,String departmentName){
		List<NotOil> list = notOilService.queryByDepartmentName(date, start, end, station, query, departmentName);
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				amounts.add(notOil.getNotOilMoney());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
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
	public Map<String,List> queryRate(Date start,Date end,String date,String station,String query,String people){
		List<NotOil> list = notOilService.queryRate(date, start, end, station, query, people);
		List<String> dates = new ArrayList<String>();
		List<Double> rates = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				rates.add(DoubleFormatUtil.format(notOil.getAvgMoney()*100));
			}
		}else {
			dates.add("无数据");
			rates.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("rates", rates);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryTop")
	@ResponseBody
	public Map<String, List> queryTop(Date start,Date end,String station,String query,String people){
		List<DataPack> list = notOilService.queryTop(start, end, station, query,people);
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
		Collections.reverse(names);
		Collections.reverse(data);
		map.put("names", names);
		map.put("data", data);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/querySearch")
	public Map<String, List> querySearch(String start,String end,String station,String query,String productCode,String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = simpleDateFormat.parse(start);
		Date parse1 = simpleDateFormat.parse(end);
		List<DataPack> list = notOilService.querySearch(parse, parse1, station, query, date, productCode);
		List<String> dates = new ArrayList<String>();
		List<Double> datas = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				datas.add(dataPack.getValue());
			}
		}else {
			dates.add("无数据");
			datas.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("datas", datas);
		return map;
	}
	
}
