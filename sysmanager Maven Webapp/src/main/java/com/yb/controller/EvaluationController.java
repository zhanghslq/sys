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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Evaluation;
import com.yb.entity.Station;
import com.yb.service.EvaluationService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/evaluation")
@Scope("prototype")
/**
 * 会员的评价
 * @author Administrator
 *
 */
public class EvaluationController {
	@Resource
	private EvaluationService evaluationService;
	@Resource
	private StationService stationService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryTrends")
	@ResponseBody
	public Map<String, List> queryTrends(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end,String area){
		List<Evaluation> list = new ArrayList<Evaluation>();
		Evaluation evaluation=new Evaluation();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationService.queryTrend(date, start,end,ArryToListUtil.format(station),area);
			evaluation=evaluationService.queryDistribution(start, end, ArryToListUtil.format(station),area);
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=evaluationService.queryTrend(date, start,end,stationid,area);
			evaluation=evaluationService.queryDistribution(start,end,stationid,area);
		}
		//对集合的处理
		List<String> dates = new ArrayList<String>();
		List<Double> stars = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Evaluation eval : list) {
				dates.add(eval.getDate());
				stars.add(eval.getStar1());
			}
		}else {
			dates.add("无数据");
			stars.add(0.0);
		}
		//对单个的处理
		List<Double> datas = new ArrayList<Double>();
		if(evaluation!=null){
			datas.add(evaluation.getStar1());
			datas.add(evaluation.getStar3());
			datas.add(evaluation.getStar4());
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("stars", stars);
		map.put("datas", datas);
		return map;
	}
}