package com.yb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.PermissionPack;
import com.yb.entity.Station;
import com.yb.entity.StationPack;
import com.yb.service.CategoryService;
import com.yb.service.StationService;
import com.yb.service.TagService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/station")
@RequiresAuthentication
public class StationController {
	@Resource
	private StationService stationService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private TagService tagService;
	
	@ResponseBody
	@RequestMapping("/update")
	@RequiresAuthentication
	public void update(StationPack stationPack){
		//stationService.update(station);
		stationService.update(stationPack);
	}
	@ResponseBody
	@RequestMapping("/queryAll")
	public List<StationPack> queryAll(){
		List<StationPack> list = stationService.queryAll();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryByArea")
	public List<StationPack> queryByArea(String area){
		if(area==null){
			area="北京";
		}
		if("BJSHELL".equals(area)){
			area="北京";
		}
		if("CDSHELL".equals(area)){
			area="承德";
		}
		List<StationPack> list = stationService.queryByArea(area);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/queryById")
	public StationPack queryById(String id){
		StationPack station = stationService.queryById(id);
		return station;
	}
	public List<String> getStationByUserName(){
		List<String> stationId = stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString());
		if(stationId!=null){
			if(stationId.size()==0){
				return null;
			}else {
				return stationId;
			}
		}
		return stationId;
	}
	@ResponseBody
	@RequestMapping("/queryAllCity")
	public List<String> queryAllCity(){
		List<String> list = stationService.queryAllCity(getStationByUserName());
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
		List<String> list = stationService.queryAdministriveRegionBy(asList,getStationByUserName());
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
		List<String> list = stationService.querySalesAreaBy(cityss, regionss,getStationByUserName());
		return list;
	}

	@ResponseBody
	@RequestMapping("/queryGasolineBy")
	public List<String> queryGasolineBy(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales) {
		// TODO Auto-generated method stub
		List<String> list = stationService.queryGasolineBy(ArryToListUtil.format(citys), 
				ArryToListUtil.format(regions), ArryToListUtil.format(sales),getStationByUserName());
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
				ArryToListUtil.format(gasoline) ,getStationByUserName());
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
				ArryToListUtil.format(sales), ArryToListUtil.format(gasoline),ArryToListUtil.format(locs) ,getStationByUserName());
		return list;
	}

	@ResponseBody
	@RequestMapping("/queryTypeBy")
	public List<String> queryTypeBy(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate) {
		// TODO Auto-generated method stub
		List<String> list = stationService.queryTypeBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions),
				ArryToListUtil.format(sales), ArryToListUtil.format(gasoline),ArryToListUtil.format(locs) , 
				ArryToListUtil.format(openDate),getStationByUserName());
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryStationBy")
	public List<Station> queryStationBy(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String[] type) {
		// TODO Auto-generated method stub
		
		
		List<Station> list = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions),
				ArryToListUtil.format(sales), ArryToListUtil.format(gasoline),ArryToListUtil.format(locs) , 
				ArryToListUtil.format(openDate),ArryToListUtil.format(type),getStationByUserName());
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryForGrant")
	public List<PermissionPack> queryForGrant(String username){
		List<PermissionPack> list = stationService.queryForGrant(username);
		return list;
	}
	@ResponseBody
	@RequestMapping("/updateGrant")
	public String updateGrant(String uname,String sid){
		try {
			String[] strs=sid.split(",");
			List<String> list=Arrays.asList(strs);
			stationService.updateGrantForUser(uname, list);
			return "授权成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "授权失败";
		}
		
	}
}
