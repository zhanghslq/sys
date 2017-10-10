package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.InterPack;
import com.yb.entity.Refuel;
import com.yb.service.RefuelService;

@Controller
@RequestMapping("/refuel")
@Scope("prototype")
public class RefuelController {

	@Resource
	private RefuelService refuelService;
	
	@ResponseBody
	@RequestMapping("/query")
	public List<InterPack> query(String station,Date start,Date end){
		Refuel refuel = refuelService.query(start, end, station);
		List<InterPack> list = new ArrayList<InterPack>();
		if(refuel!=null){
			list.add(new InterPack("<10L", refuel.getLessThanTen()));
			list.add(new InterPack("10L~20L", refuel.getTenToTwenty()));
			list.add(new InterPack("20L~30L", refuel.getTwentyToThirty()));
			list.add(new InterPack("30L~40L", refuel.getThirtyToFourty()));
			list.add(new InterPack("40L~50L", refuel.getFourtyToFifty()));
			list.add(new InterPack(">50L", refuel.getOverFifty()));
		}else {
			list.add(new InterPack("<10L", 0));
			list.add(new InterPack("10L~20L", 0));
			list.add(new InterPack("20L~30L", 0));
			list.add(new InterPack("30L~40L", 0));
			list.add(new InterPack("40L~50L", 0));
			list.add(new InterPack(">50L", 0));
		}
		return list;
	}
}
