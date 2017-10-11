package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Station;
import com.yb.service.StationService;

@Controller
@Scope("prototype")
@RequestMapping("/station")
public class StationController {
	@Resource
	private StationService stationService;
	
	@ResponseBody
	@RequestMapping("/update")
	public void update(Station station){
		stationService.update(station);
	}
	@ResponseBody
	@RequestMapping("queryAll")
	public List<Station> queryAll(){
		List<Station> list = stationService.queryAll();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryById")
	public Station queryById(String id){
		Station station = stationService.queryById(id);
		return station;
	}
}
