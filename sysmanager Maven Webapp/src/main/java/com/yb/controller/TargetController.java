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

import com.yb.entity.DataPack;
import com.yb.service.TargetService;

@Scope("prototype")
@RequestMapping("/target")
@Controller
public class TargetController {

	@Resource
	private TargetService targetService;
	
	@RequestMapping("/queryTarget")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Map<String, List> queryTarget(String station){
		List<DataPack> list = targetService.queryTarget(station);
		List<String> days = new ArrayList<String>();
		List<Double> data=new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				days.add(dataPack.getName());
				data.add(dataPack.getValue());
			}
		}else {
			days.add("无数据");
			data.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("data", data);
		return map;
	}
}
