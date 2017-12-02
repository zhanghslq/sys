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

import com.yb.entity.Evaluation;
import com.yb.service.EvaluationService;

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
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryTrends")
	@ResponseBody
	public Map<String, List> queryTrends(String date,Date start,Date end,String station,String query){
		List<Evaluation> list = evaluationService.queryTrend(date, start, end, station, query);
		List<String> dates = new ArrayList<String>();
		List<Double> stars = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Evaluation evaluation : list) {
				dates.add(evaluation.getDate());
				stars.add(evaluation.getStar1());
			}
		}else {
			dates.add("无数据");
			stars.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("stars", stars);
		return map;
	}
	@RequestMapping("/queryDistribution")
	@ResponseBody
	public List<Double> queryDistribution(Date start,Date end,String station,String query){
		Evaluation evaluation = evaluationService.queryDistribution(start, end, station, query);
		List<Double> datas = new ArrayList<Double>();
		if(evaluation!=null){
			datas.add(evaluation.getStar1());
			datas.add(evaluation.getStar2());
			datas.add(evaluation.getStar3());
			datas.add(evaluation.getStar4());
			datas.add(evaluation.getStar5());
		}
		return datas;
	}
	@ResponseBody
	@RequestMapping("/queryEvaluations")
	public List<List<Double>> queryEvaluations(Date start,Date end,String station,String query){
		List<List<Double>> resuList = new ArrayList<List<Double>>();
		List<Evaluation> list = evaluationService.queryEvaluations(start, end, station, query);
		if(list!=null&&list.size()!=0){
			for (Evaluation evaluation : list) {
				List<Double> data = new ArrayList<Double>();
				data.add(evaluation.getStar1());
				data.add(evaluation.getStar2());
				data.add(evaluation.getStar3());
				data.add(evaluation.getStar4());
				data.add(evaluation.getStar5());
				resuList.add(data);
			}
		}else {
			List<Double> arrayList = new ArrayList<Double>();
			arrayList.add(0.0);
			resuList.add(arrayList);
		}
		return resuList;
	}
}