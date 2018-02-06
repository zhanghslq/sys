package com.yb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Station;
import com.yb.service.CityService;
import com.yb.service.StationService;

@Controller
@Scope("prototype")
@RequestMapping("/city")
public class CityController {
	
	@Resource
	private CityService cityService;
	@Resource
	private StationService stationService;
	@ResponseBody
	@RequestMapping("/queryCitys")
	public List<String> queryCitys(){
		List<String> queryCitys = cityService.queryCitys();
		return queryCitys;
	}
	@ResponseBody
	@RequestMapping("/queryStations")
	public List<String> queryStations(){
		List<String> queryStations = cityService.queryStations(stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
		return queryStations;
	}
	@ResponseBody
	@RequestMapping("queryAll")
	public List<Station> queryAll(){
		List<Station> list = cityService.queryAll(stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
		return list;
	}
}