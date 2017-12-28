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

import com.yb.entity.Credit;
import com.yb.entity.DataPack;
import com.yb.service.CreditService;

@Controller
@RequestMapping("/credit")
@Scope("prototype")
public class CreditController {

	@Resource
	private CreditService creditService;
	
	@RequestMapping("/queryCredits")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Map<String, List> queryCredits(String date,Date start,Date end,String area){
		List<Credit> list = creditService.queryCredit(date, start, end,area);
		List<String> days = new ArrayList<String>();
		List<Double> get = new ArrayList<Double>();
		List<Double> used = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Credit credit : list) {
				days.add(credit.getDays());
				get.add(credit.getGetCredits());
				used.add(credit.getUsedCredits());
			}
		}else {
			days.add("无数据");
			get.add(0.0);
			used.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("get", get);
		map.put("used", used);
		return map;
	}
	@RequestMapping("/queryZhanbi")
	@ResponseBody
	public List<DataPack> queryZhanbi(String area){
		Credit credit = creditService.queryZhanbi(area);
		List<DataPack> list = new ArrayList<DataPack>();
		list.add(new DataPack("已使用", credit.getUsedCredits()));
		list.add(new DataPack("未使用", credit.getGetCredits()));
		return list;
	}
}
