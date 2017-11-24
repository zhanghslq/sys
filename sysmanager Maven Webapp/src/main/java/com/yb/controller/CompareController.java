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
import com.yb.util.DoubleFormatUtil;

@RequestMapping("/compare")
@Scope("prototype")
@Controller
public class CompareController {
	@Resource
	private OilService oilService;
	@Resource
	private NotOilService notOilService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryOil")
	public Map<String, List> queryOil(Date newstart,Date newend,Date oldstart,
			Date oldend,String query,String station,String oilName,String people){
		Oil oldOil = oilService.queryCompare(oldstart, oldend, station, query, oilName,people);
		Oil newOil = oilService.queryCompare(newstart, newend, station, query, oilName,people);
		List<Double> litre = new ArrayList<Double>();
		List<Double> beforelitre = new ArrayList<Double>();
		List<Double> afterlitre = new ArrayList<Double>();
		List<Double> beforeavgLitre = new ArrayList<Double>();
		List<Double> afteravgLitre = new ArrayList<Double>();
		List<Double> avgLitre = new ArrayList<Double>();
		List<Double> beforenumber = new ArrayList<Double>();
		List<Double> afternumber = new ArrayList<Double>();
		List<Double> number = new ArrayList<Double>();
		if(oldOil!=null&&newOil!=null){
			Double oldLitre = oldOil.getOilLitre();
			Double oldNumber = oldOil.getOilNumber();
			Double oldavgLitre=oldLitre/oldNumber;
			
			Double newLitre = newOil.getOilLitre();
			Double newNumber = newOil.getOilNumber();
			Double newavgLitre=newLitre/newNumber;
			
			beforelitre.add(DoubleFormatUtil.format(oldLitre/1000));
			afterlitre.add(DoubleFormatUtil.format(newLitre/1000));
			litre.add(DoubleFormatUtil.format((newLitre-oldLitre)/oldLitre*100));
			beforeavgLitre.add(DoubleFormatUtil.format(oldavgLitre));
			afteravgLitre.add(DoubleFormatUtil.format(newavgLitre));
			avgLitre.add(DoubleFormatUtil.format((newavgLitre-oldavgLitre)/oldavgLitre*100));
			beforenumber.add(oldNumber);
			afternumber.add(newNumber);
			number.add(DoubleFormatUtil.format((newNumber-oldNumber)/oldNumber*100));
		}else {
			beforelitre.add(0.0);
			afterlitre.add(0.0);
			beforeavgLitre.add(0.0);
			afteravgLitre.add(0.0);
			beforenumber.add(0.0);
			afternumber.add(0.0);
			litre.add(0.0);
			avgLitre.add(0.0);
			number.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("beforelitre", beforelitre);
		map.put("afterlitre", afterlitre);
		map.put("litre", litre);
		map.put("beforeavgLitre", beforeavgLitre);
		map.put("afteravgLitre", afteravgLitre);
		map.put("avgLitre", avgLitre);
		map.put("beforenumber", beforenumber);
		map.put("afternumber", afternumber);
		map.put("number", number);
		return map;
	}
	@ResponseBody
	@RequestMapping("/queryShop")
	public Map<String, List<Double>> queryShop(Date oldstart,Date oldend,Date newstart,Date newend,
			String query,String station,String departmentName,String people){
		NotOil oldNotOil = notOilService.queryByCompare(oldstart, oldend, station, query, departmentName,people);
		NotOil newNotOil = notOilService.queryByCompare(newstart, newend, station, query, departmentName,people);
		List<Double> aftermoneys = new ArrayList<Double>();
		List<Double> beforemoneys = new ArrayList<Double>();
		List<Double> moneys = new ArrayList<Double>();
		List<Double> beforeavgMoneys = new ArrayList<Double>();
		List<Double> afteravgMoneys = new ArrayList<Double>();
		List<Double> avgMoneys = new ArrayList<Double>();
		List<Double> beforenumbers = new ArrayList<Double>();
		List<Double> afternumbers = new ArrayList<Double>();
		List<Double> numbers = new ArrayList<Double>();
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
			
			beforemoneys.add(DoubleFormatUtil.format(oldMoney));
			aftermoneys.add(DoubleFormatUtil.format(newMoney));
			moneys.add(DoubleFormatUtil.format(money*100));
			beforeavgMoneys.add(DoubleFormatUtil.format(oldAvgMoney));
			afteravgMoneys.add(DoubleFormatUtil.format(newAvgMoney));
			avgMoneys.add(DoubleFormatUtil.format(avgMoney*100));
			beforenumbers.add(oldNumber);
			afternumbers.add(newNumber);
			numbers.add(DoubleFormatUtil.format(number*100));
		}else {
			beforemoneys.add(0.0);
			aftermoneys.add(0.0);
			beforeavgMoneys.add(0.0);
			afteravgMoneys.add(0.0);
			beforenumbers.add(0.0);
			afternumbers.add(0.0);
			moneys.add(0.0);
			avgMoneys.add(0.0);
			numbers.add(0.0);
		}
		Map<String,List<Double>> map = new HashMap<String,List<Double>>();
		map.put("beforemoneys", beforemoneys);
		map.put("aftermoneys", aftermoneys);
		map.put("moneys", moneys);
		map.put("beforeavgMoneys", beforeavgMoneys);
		map.put("afteravgMoneys", afteravgMoneys);
		map.put("avgMoneys", avgMoneys);
		map.put("beforenumbers", beforenumbers);
		map.put("afternumbers", afternumbers);
		map.put("numbers", numbers);
		return map;
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
