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

import com.yb.entity.AddVip;
import com.yb.service.AddVipService;

@Controller
@RequestMapping("/addVip")
@Scope("prototype")
public class AddVipController {
	//全网新增会员
	@Resource
	private AddVipService addVipService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(String date,Date start,Date end){
		List<AddVip> list = addVipService.query(date, start, end);
		List<String> dates = new ArrayList<String>();
		List<Integer> addNumbers = new ArrayList<Integer>();
		List<Integer> totalPeoples = new ArrayList<Integer>();
		for (AddVip addVip : list) {
			dates.add(addVip.getDate());
			addNumbers.add(addVip.getNumber());
			totalPeoples.add(addVip.getTotalPeople());
		}
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("addNumbers", addNumbers);
		map.put("totalPeoples", totalPeoples);
		return map;
	}
}
