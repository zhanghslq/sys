package com.yb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Category;
import com.yb.entity.Query;
import com.yb.entity.Station;
import com.yb.entity.StationPack;
import com.yb.entity.Tag;
import com.yb.service.CategoryService;
import com.yb.service.StationService;
import com.yb.service.TagService;

@Controller
@Scope("prototype")
@RequestMapping("/station")
public class StationController {
	@Resource
	private StationService stationService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private TagService tagService;
	@ResponseBody
	@RequestMapping("/update")
	public void update(StationPack stationPack){
		//stationService.update(station);
		stationService.update(stationPack);
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
	@ResponseBody
	@RequestMapping("queryAllName")
	public List<Query> queryAllName(String query){
		List<Query> list = new ArrayList<Query>();
		if(query.equals("station")){//按油站查询
			List<Station> stations = stationService.queryAll();
			for (Station station : stations) {
				Query query2 = new Query();
				query2.setId(station.getId());
				query2.setName(station.getName());
				list.add(query2);
			}
		}
		if(query.equals("category")){//按照类别查询
			List<Category> queryAll = categoryService.queryAll();
			if(queryAll!=null&&queryAll.size()!=0){
				for (Category category : queryAll) {
					Query query2 = new Query();
					query2.setId(String.valueOf(category.getId()));
					query2.setName(category.getName());
					list.add(query2);
				}
			}
		}
		if(query.equals("tag")){
			List<Tag> queryAll = tagService.queryAll();
			if(queryAll!=null&&queryAll.size()!=0){
				for (Tag tag : queryAll) {
					Query query2 = new Query();
					query2.setId(String.valueOf(tag.getId()));
					query2.setName(tag.getName());
					list.add(query2);
				}
			}
		}
		if (query.equals("city")) {
			List<String> queryAllCity = stationService.queryAllCity();
			if(queryAllCity!=null&&queryAllCity.size()!=0){
				for (String string : queryAllCity) {
					Query query2 = new Query(string, string);
					list.add(query2);
				}
			}
		}
		if (query.equals("gasoline")) {
			List<String> queryAllGasoline = stationService.queryAllGasoline();
			if(queryAllGasoline!=null&& queryAllGasoline.size()!=0){
				for (String string : queryAllGasoline) {
					Query query2 = new Query(string, string);
					list.add(query2);
				}
			}
		}
		if (query.equals("diesel")) {
			List<String> queryAllDiesel = stationService.queryAllDiesel();
			if(queryAllDiesel!=null&&queryAllDiesel.size()!=0){
				for (String string : queryAllDiesel) {
					Query query2 = new Query(string, string);
					list.add(query2);
				}
			}
		}
		if (query.equals("time")) {
			List<String> queryAllDate = stationService.queryAllDate();
			if(queryAllDate!=null&&queryAllDate.size()!=0){
				for (String string : queryAllDate) {
					Query query2 = new Query(string, string);
					list.add(query2);
				}
			}
		}
		if (query.equals("location")) {
			List<String> queryAllLocation = stationService.queryAllLocation();
			if(queryAllLocation!=null&&queryAllLocation.size()!=0){
				for (String string : queryAllLocation) {
					Query query2 = new Query(string, string);
					list.add(query2);
				}
			}
		}
		return list;
	}
	
}
