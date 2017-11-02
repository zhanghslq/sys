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
	
	@RequestMapping("/queryHHT")
	@ResponseBody
	public List<DataPack> queryHHT(Date start,Date end,String query,String station){
		HHT hht = mopService.queryHHT(start, end, query, station);
		List<DataPack> list = new ArrayList<DataPack>();
		if(hht!=null){
			list.add(new DataPack("HHT支付", hht.getHhtMoney()));
			list.add(new DataPack("IPT支付",hht.getIptMoney()));
		}else {
			list.add(new DataPack("无数据", 0.0));
		}
		return list;
	}
}
