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

import com.yb.entity.NotOil;
import com.yb.entity.Oil;
import com.yb.service.NotOilService;
import com.yb.service.OilService;

@RequestMapping("/compare")
@Scope("prototype")
@Controller
public class CompareController {
	@Resource
	private OilService oilService;
	@Resource
	private NotOilService notOilService;
	
	//private DecimalFormat df=new DecimalFormat("#.##");
	
	@ResponseBody
	@RequestMapping("/queryOil")
	public List<Double> queryOil(Date newstart,Date newend,Date oldstart,
			Date oldend,String query,String station,String oilName){
		Oil oldOil = oilService.queryCompare(oldstart, oldend, station, query, oilName);
		Oil newOil = oilService.queryCompare(newstart, newend, station, query, oilName);
		List<Double> data = new ArrayList<Double>();
		if(oldOil!=null&&newOil!=null){
			Double oldLitre = oldOil.getOilLitre();
			Double oldNumber = oldOil.getOilNumber();
			Double oldavgLitre=oldLitre/oldNumber;
			Double newLitre = newOil.getOilLitre();
			Double newNumber = newOil.getOilNumber();
			Double newavgLitre=newLitre/newNumber;
			data.add((newLitre-oldLitre)/oldLitre);
			data.add((newNumber-oldNumber)/oldNumber);
			data.add((newavgLitre-oldavgLitre)/oldavgLitre);
			
		}else {
			data.add(0.0);
			data.add(0.0);
			data.add(0.0);
		}
		return data;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryShop")
	public List queryShop(Date oldstart,Date oldend,Date newstart,Date newend,
			String query,String station,String departmentName){
		NotOil oldNotOil = notOilService.queryByCompare(oldstart, oldend, station, query, departmentName);
		NotOil newNotOil = notOilService.queryByCompare(newstart, newend, station, query, departmentName);
		List<Object> data = new ArrayList<>();
		if(oldNotOil!=null&&newNotOil!=null){
			Double oldAvgMoney = oldNotOil.getAvgMoney();
			Double oldMoney = oldNotOil.getNotOilMoney();
			Double oldNumber = Double.valueOf(String.valueOf(oldNotOil.getNotOilNumber()));
			Double newAvgMoney = newNotOil.getAvgMoney();
			Double newMoney = newNotOil.getNotOilMoney();
			Double newNumber = Double.valueOf(String.valueOf(newNotOil.getNotOilNumber()));
			Double avgMoney = (newAvgMoney-oldAvgMoney)/(oldAvgMoney);
			Double money = (newMoney-oldMoney)/(oldMoney);
			Double number=(newNumber-oldNumber)/oldNumber;
			data.add(money);
			data.add(number);
			data.add(avgMoney);
		}else {
			data.add(0);
			data.add(0);
			data.add(0);
		}
		return data;
	}
	@RequestMapping("/queryRateCompare")
	@ResponseBody
	public Map<String, List<Double>> queryRateCompare(Date oldstart,Date oldend,Date newstart,Date newend,String station,String query){
		Double oldrate = notOilService.queryRateCompare(oldstart, oldend, station, query);
		Double newrate = notOilService.queryRateCompare(newstart, newend, station, query);
		List<Double> data = new ArrayList<Double>();
		List<Double> before = new ArrayList<Double>();
		List<Double> after = new ArrayList<Double>();
		if(oldrate!=null&&newrate!=null){
			before.add(oldrate);
			after.add(newrate);
			data.add((newrate-oldrate)/oldrate);
		}else {
			data.add(0.00);
			after.add(0.00);
			before.add(0.00);
		}
		Map<String,List<Double>> map = new HashMap<String,List<Double>>();
		map.put("before", before);
		map.put("after", after);
		map.put("data", data);
		return map;
	}
}
