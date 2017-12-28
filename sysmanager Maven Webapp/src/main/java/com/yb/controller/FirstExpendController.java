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

import com.yb.entity.FirstExpend;
import com.yb.entity.VipRechargePack;
import com.yb.service.FirstExpendService;

@Controller
@RequestMapping("/firstExpend")
@Scope("prototype")
public class FirstExpendController {
	
	@Resource
	private FirstExpendService firstExpendService;
	
	@RequestMapping("/queryAllExpend")
	@ResponseBody
	public Map<String, List<Integer>> queryAllExpend(String area){
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<FirstExpend> list = firstExpendService.queryAllExpend(area);
		for (FirstExpend firstExpend : list) {
			days.add(firstExpend.getDay());
			numbers.add(firstExpend.getNumber());
		}
		Map<String,List<Integer>> map = new HashMap<>();
		map.put("numbers", numbers);
		map.put("days",days);
		return map;
	}
	@RequestMapping("/queryAllGap")
	@ResponseBody
	public Map<String, List<Integer>> queryAllGap(String area){
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<FirstExpend> list = firstExpendService.queryAllGap(area);
		for (FirstExpend firstExpend : list) {
			days.add(firstExpend.getDay());
			numbers.add(firstExpend.getNumber());
		}
		Map<String,List<Integer>> map = new HashMap<>();
		map.put("numbers", numbers);
		map.put("days",days);
		return map;
	}
	@RequestMapping("queryLastDeal")
	@ResponseBody
	public Map<String, List<Integer>> queryLastDeal(String area){
		List<FirstExpend> list= firstExpendService.queryLastDeal(area);
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
			for (FirstExpend firstExpend : list) {
				days.add(firstExpend.getDay());
				numbers.add(firstExpend.getNumber());
			}
		Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
		map.put("days", days);
		map.put("numbers", numbers);
		return map;
	}
	@RequestMapping("/vipDealMonth")
	@ResponseBody
	public List<List<Double>> queryDealMonth(Date start,Date end,String area){
		List<VipRechargePack> list = firstExpendService.queryDealMonth(start, end,area);
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
