package com.yb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Category;
import com.yb.entity.Query;
import com.yb.entity.Station;
import com.yb.entity.StationPack;
import com.yb.entity.Tag;
import com.yb.service.CategoryService;
import com.yb.service.StationService;
import com.yb.service.TagService;
import com.yb.util.ArryToListUtil;

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
	@ResponseBody
	@RequestMapping("/queryAllCity")
	public List<String> queryAllCity(){
		List<String> list = stationService.queryAllCity();
		return list;
	}
	
	@ResponseBody()
	@RequestMapping("/queryAllRegion")
	public List<String> queryAllRegion(){
		List<String> list = stationService.queryAllAdministriveRegion();
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping("/queryAdministriveRegionBy")
	public List<String> queryAdministriveRegionBy(@RequestParam(required=false,value="citys[]")String[] citys) {
		// TODO Auto-generated method stub
		List<String> asList=new ArrayList<>();
		if(citys!=null&&citys.length!=0){
			asList = Arrays.asList(citys);
		}else {
			asList=null;
		}
		List<String> list = stationService.queryAdministriveRegionBy(asList);
		return list;
	}

	@ResponseBody
	@RequestMapping("/querySalesAreaBy")
	public List<String> querySalesAreaBy(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String[] regions) {
		// TODO Auto-generated method stub
		List<String> cityss=new ArrayList<>();
		List<String> regionss=new ArrayList<>();
		if(citys!=null&&citys.length!=0){
			cityss = Arrays.asList(citys);
		}else {
			cityss=null;
		}
		if(regions!=null&&regions.length!=0){
			regionss = Arrays.asList(regions);
		}else {
			regionss=null;
		}
		List<String> list = stationService.querySalesAreaBy(cityss, regionss);
		return list;
	}

	@ResponseBody
	@RequestMapping("/queryGasolineBy")
	public List<String> queryGasolineBy(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales) {
		// TODO Auto-generated method stub
		List<String> list = stationService.queryGasolineBy(ArryToListUtil.format(citys), 
				ArryToListUtil.format(regions), ArryToListUtil.format(sales));
		return list;
	}

	@ResponseBody
	@RequestMapping("/queryLocationBy")
	public List<String> queryLocationBy(@RequestParam(required=false,value="citys[]")String [] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales, 
			@RequestParam(required=false,value="gasoline[]")String [] gasoline) {
		// TODO Auto-generated method stub
		List<String> list = stationService.queryLocationBy(ArryToListUtil.format(citys),
				ArryToListUtil.format(regions),ArryToListUtil.format(sales) ,
				ArryToListUtil.format(gasoline) );
		return list;
	}

	@RequestMapping("/queryOpenDateBy")
	@ResponseBody
	public List<String> queryOpenDateBy(@RequestParam(required=false,value="citys[]")String [] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs) {
		// TODO Auto-generated method stub
		List<String> list = stationService.queryOpenDateBy(ArryToListUtil.format(citys),ArryToListUtil.format(regions) ,
				ArryToListUtil.format(sales), ArryToListUtil.format(gasoline),ArryToListUtil.format(locs) );
		return list;
	}

	@ResponseBody
	@RequestMapping("/queryStationBy")
	public List<Station> queryStationBy(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate) {
		// TODO Auto-generated method stub
		List<Station> list = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions),
				ArryToListUtil.format(sales), ArryToListUtil.format(gasoline),ArryToListUtil.format(locs) , 
				ArryToListUtil.format(openDate));
		return list;
	}
	
}
