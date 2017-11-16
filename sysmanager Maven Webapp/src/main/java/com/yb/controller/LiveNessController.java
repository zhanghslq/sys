package com.yb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.InterPack;
import com.yb.entity.VipLiveness;
import com.yb.service.LiveNessService;

@Controller
@Scope("prototype")
@RequestMapping("/liveNess")
public class LiveNessController {
	@Resource
	private LiveNessService liveNessService;
	
	@ResponseBody
	@RequestMapping("/queryAllDate")
	public List<String> queryAllDate(){
		List<String> list = liveNessService.queryAllDate();
		return list;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryData")
	public Map<String, List> queryData(){
		 List<VipLiveness> list = liveNessService.queryData();
		List<String> date = new ArrayList<String>();
		List<Integer> zero = new ArrayList<Integer>();
		List<Integer> one = new ArrayList<Integer>();
		List<Integer> two = new ArrayList<Integer>();
		List<Integer> three = new ArrayList<Integer>();
		List<Integer> four = new ArrayList<Integer>();
		List<Integer> five= new ArrayList<Integer>();
		List<Integer> overfive= new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (VipLiveness vipLiveness : list) {
				date.add(vipLiveness.getMonth());
				zero.add(vipLiveness.getZero());
				one.add(vipLiveness.getOne());
				two.add(vipLiveness.getTwo());
				three.add(vipLiveness.getThree());
				four.add(vipLiveness.getFour());
				five.add(vipLiveness.getFive());
				overfive.add(vipLiveness.getOverfive());
			}
		}else {
			date.add("无数据");
			zero.add(0);
			one.add(0);
			two.add(0);
			three.add(0);
			four.add(0);
			five.add(0);
			overfive.add(0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("date",date);
		map.put("zero",zero);
		map.put("one",one);
		map.put("two",two);
		map.put("three",three);
		map.put("four",four);
		map.put("five",five);
		map.put("overfive",overfive);
		return map;
	}
}
