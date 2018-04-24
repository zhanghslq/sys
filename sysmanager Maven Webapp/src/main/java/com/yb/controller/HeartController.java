package com.yb.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Heart;
import com.yb.entity.Station;
import com.yb.entity.StationLive;
import com.yb.entity.StationPack;
import com.yb.service.HeartService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/heart")
public class HeartController {
	@Resource
	private HeartService heartService;
	@Resource
	private StationService stationService;

	@ResponseBody
	@RequestMapping("/queryAll")
	public List<Heart> queryAll(){
		List<Heart> list = heartService.queryAll();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryMessage")
	@Cacheable(value="message")
	public List<String> queryMessage(String area){
		List<StationPack> queryByArea = stationService.queryByArea(area);
		List<String> stationid = new ArrayList<String>();
		for (StationPack stationPack : queryByArea) {
			stationid.add(stationPack.getId());
		}
		if(stationid.size()==0){
			stationid=null;
		}
		String message="";
		List<String> arrayList = new ArrayList<String>();
		List<Heart> list = heartService.queryByNumber(3, "server",stationid);
		if(list==null||list.size()==0){
			message="数据传输正常";
			arrayList.add(message);
		}else {
			for (Heart heart : list) {
				message=heart.getName()+" " +heart.getLastTime()+"  数据传输异常";
				arrayList.add(message);
			}
		}
		return arrayList;
	}
	@RequestMapping("/queryLive")
	@ResponseBody
	public List<String> queryLive(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station){
		List<String> stationid=new ArrayList<String>();
		if(ArryToListUtil.format(station)!=null){
			stationid=ArryToListUtil.format(station);
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
		}
		List<StationLive> list = heartService.queryLive(stationid);
		List<String> message = new ArrayList<String>();
		for (StationLive stationLive : list) {
			message.add(stationLive.getName()+":本月营业"+stationLive.getMonth()+"天，本年营业"+stationLive.getYear()+"天<br>");
		}
		return message;
	}
	
}
