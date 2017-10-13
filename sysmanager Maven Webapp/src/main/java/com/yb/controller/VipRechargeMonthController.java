package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.VipRechargeMonth;
import com.yb.entity.VipRechargePack;
import com.yb.service.VipRechargeMonthService;

@Controller
@RequestMapping("/vipRechargeMonth")
public class VipRechargeMonthController {
	@Resource
	private VipRechargeMonthService vipRechargeMonthService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(Date start,Date end){
		List<VipRechargeMonth> list = vipRechargeMonthService.query(start, end);
		List<Double> avgRecharges = new ArrayList<Double>();
		List<Double> rechargeTotals = new ArrayList<Double>();
		List<Integer> peoples = new ArrayList<Integer>();
		List<String> dates = new ArrayList<String>();
		if(list!=null&&list.size()!=0){
			for (VipRechargeMonth vipRechargeMonth : list) {
				peoples.add(vipRechargeMonth.getPeoples());
				avgRecharges.add(vipRechargeMonth.getAvgRecharge());
				rechargeTotals.add(vipRechargeMonth.getRechargeTotal());
				dates.add(vipRechargeMonth.getDate());
			}
		}else {
			peoples.add(0);
			avgRecharges.add(0.0);
			rechargeTotals.add(0.0);
			dates.add("无数据");
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("peoples", peoples);
		map.put("avgRecharges", avgRecharges);
		map.put("rechargeTotals", rechargeTotals);
		map.put("dates", dates);
		return map;
	}
	@ResponseBody
	@RequestMapping("/querySingle")
	public List<List<Double>> querySingle(Date start,Date end){
		List<VipRechargePack> list = vipRechargeMonthService.querySingle(start, end);
		List<List<Double>> lists = new ArrayList<List<Double>>();
		if(list!=null&&list.size()!=0){//查询结果有数据的时候
			for (VipRechargePack vipRechargePack : list) {
				List<Double> datas = new ArrayList<Double>();
				datas.add(vipRechargePack.getNumber());
				datas.add(vipRechargePack.getAmount());
				lists.add(datas);
			}
		}else {//无数据的时候
			List<Double> datas = new ArrayList<Double>();
			datas.add(0.0);
			datas.add(0.0);
			lists.add(datas);
		}
		return lists;
	}
}
