package com.yb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yb.entity.FirstExpend;
import com.yb.service.FirstExpendService;

@Controller
@RequestMapping("/firstExpend")
@Scope("prototype")
public class FirstExpendController {
	
	@Resource
	private FirstExpendService firstExpendService;
	
	@RequestMapping("/queryAllExpend")
	public Map<String, List<Integer>> queryAllExpend(){
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<FirstExpend> list = firstExpendService.queryAllExpend();
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
	public Map<String, List<Integer>> queryAllGap(){
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<FirstExpend> list = firstExpendService.queryAllGap();
		for (FirstExpend firstExpend : list) {
			days.add(firstExpend.getDay());
			numbers.add(firstExpend.getNumber());
		}
		Map<String,List<Integer>> map = new HashMap<>();
		map.put("numbers", numbers);
		map.put("days",days);
		return map;
	}
	
}
