package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Evaluationb;
import com.yb.entity.Station;
import com.yb.service.EvaluationbService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/evaluationb")
public class EvaluationbController {

	@Resource
	private EvaluationbService evaluationbService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryEvaluation")
	@ResponseBody
	public Map<String, List> queryEvaluation(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end,String area){
		List<Evaluationb> list = new ArrayList<Evaluationb>();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationbService.queryByDate(start, end, ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(openDate),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=evaluationbService.queryByDate(start, end, stationid);
		}
		List<String> name = new ArrayList<String>();
		List<Integer> yes = new ArrayList<Integer>();
		List<Integer> no = new ArrayList<Integer>();
		List<Integer> unknown = new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (Evaluationb evaluationb : list) {
				name.add(evaluationb.getPROBLEMTEXT());
				yes.add(evaluationb.getYes());
				no.add(evaluationb.getNo());
				unknown.add(evaluationb.getUnknow());
			}
		}else {
			name.add("无数据");
			yes.add(0);
			no.add(0);
			unknown.add(0);
		}
		Map<String, List> map = new HashMap<String,List>();
		map.put("name",name );
		map.put("yes", yes);
		map.put("no", no);
		map.put("unknown",unknown );
		return map;
	}
}
