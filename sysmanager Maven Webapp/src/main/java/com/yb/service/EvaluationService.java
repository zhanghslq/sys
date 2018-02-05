package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Evaluation;

public interface EvaluationService {
	//走势，带日期的，五个评价项的平均值，按照时间来走势
		List<Evaluation> queryTrend(String date,Date start,
				Date end,List<String> station);
		//分布，是五个柱形图，把一段时间内的数据，相同类的评价取平均值
		Evaluation queryDistribution(Date start,Date end,List<String> station);
		List<Evaluation> exportTrend(String date,Date start,
				Date end,List<String> station);
		List<Evaluation> exportDistribution(Date start,Date end,List<String> station);

		//再求一个射线图，是一个时间段,然后五个值都有，是一个数组
		List<Evaluation> queryEvaluations(Date start,Date end,List<String> station);
}
