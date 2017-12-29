package com.yb.controller;

import java.text.SimpleDateFormat;
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
import com.yb.entity.HomePack;
import com.yb.entity.Price;
import com.yb.service.HomePageService;

@Controller
@Scope("prototype")
@RequestMapping("/homePage")
public class HomePageController {
	@Resource
	private HomePageService homePageService;
	
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryMap")
	public Map<String, List> queryMap(){
		List<HomePack> list = homePageService.queryList();
		List<String> days = new ArrayList<String>();
		List<Double> oil = new ArrayList<Double>();
		List<Double> notoil = new ArrayList<Double>();
		List<Double> avgWater = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (HomePack homePack : list) {
				days.add(homePack.getDate());
				oil.add(homePack.getOilMoney());
				notoil.add(homePack.getNotoilMoney());
				avgWater.add(homePack.getAvgWater());
			}
		}else {
			days.add("无数据 ");
			oil.add(0.0);
			notoil.add(0.0);
			avgWater.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days",days);
		map.put("oil",oil);
		map.put("notoil",notoil);
		map.put("avgWater",avgWater);
		return map;
		
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String,List> query(){
		List<DataPack> notOil = homePageService.queryNotOil();
		List<DataPack> oil = homePageService.queryOil();
		List<String> list = homePageService.queryAll();
		Map<String,List> map = new HashMap<String,List>();
		map.put("notOil",notOil );
		map.put("oil", oil);
		map.put("list",list);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryPriceBei")
	public Map<String, List> queryPriceBei(){
		List<Price> list = homePageService.queryPriceBei();
		List<String> days = new ArrayList<String>();
		List<Double> oil89 = new ArrayList<Double>();
		List<Double> oil92 = new ArrayList<Double>();
		List<Double> oil95 = new ArrayList<Double>();
		List<Double> oil0 = new ArrayList<Double>();
		for (Price price : list) {
			oil89.add(price.getOil90());
			oil92.add(price.getOil93());
			oil95.add(price.getOil97());
			oil0.add(price.getOil0());
			days.add(simpleDateFormat.format(price.getTime()));
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("oil89",oil89 );
		map.put("oil92",oil92 );
		map.put("oil95",oil95 );
		map.put("oil0",oil0 );
		map.put("days",days);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryCheng")
	public Map<String, List> queryCheng(){
		List<Price> list = homePageService.queryCheng();
		List<String> days = new ArrayList<String>();
		List<Double> oil90 = new ArrayList<Double>();
		List<Double> oil93 = new ArrayList<Double>();
		List<Double> oil97 = new ArrayList<Double>();
		List<Double> oil0 = new ArrayList<Double>();
		for (Price price : list) {
			oil90.add(price.getOil90());
			oil93.add(price.getOil93());
			oil97.add(price.getOil97());
			oil0.add(price.getOil0());
			days.add(simpleDateFormat.format(price.getTime()));
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("oil90",oil90 );
		map.put("oil93",oil93 );
		map.put("oil97",oil97 );
		map.put("oil0",oil0);
		map.put("days",days);
		return map;
	}
}
