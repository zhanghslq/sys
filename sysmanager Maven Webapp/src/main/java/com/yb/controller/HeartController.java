package com.yb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Heart;
import com.yb.entity.StationPack;
import com.yb.service.HeartService;
import com.yb.service.StationService;

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
	public List<String> queryMessage(String area){
		List<StationPack> queryByArea = stationService.queryByArea(area);
		List<String> stationid = new ArrayList<String>();
		for (StationPack stationPack : queryByArea) {
			stationid.add(stationPack.getId());
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
	
}
