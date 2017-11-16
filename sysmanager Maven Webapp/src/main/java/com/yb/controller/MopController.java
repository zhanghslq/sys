package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;
import com.yb.service.MopService;

@Controller
@RequestMapping("/mop")
@Scope("prototype")
public class MopController {

	@Resource
	private MopService mopService;
	
	
	@ResponseBody
	@RequestMapping("/queryAllMop")
	public List<String> queryAllMop(){
		List<String> list = mopService.queryAllMop();
		return list;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryMop")
	public Map<String, List> queryMop(Date start,Date end,String query,String station){
		List<DataPack> list = mopService.queryMop(start, end, query, station);
		List<String> mop = mopService.queryAllMop();
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("mop", mop);
		map.put("data", list);
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryHHT")
	@ResponseBody
	public Map<String,List> queryHHT(Date start,Date end,String query,String station){
		HHT hht = mopService.queryHHT(start, end, query, station);
		List<DataPack> mophht = mopService.queryMophht(start, end, query, station);
		List<DataPack> mopipt = mopService.queryMopipt(start, end, query, station);
		List<DataPack> all = new ArrayList<DataPack>();
		
		List<String> mop = mopService.queryAllMop();
		if(hht!=null){
			all.add(new DataPack("HHT支付", hht.getHhtMoney()));
			all.add(new DataPack("IPT支付",hht.getIptMoney()));
		}else {
			all.add(new DataPack("无数据", 0.0));
		}
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("ipt", mopipt);
		map.put("hht", mophht);
		map.put("all", all);
		map.put("mop", mop);
		return map;
	}
}
