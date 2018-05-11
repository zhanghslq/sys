package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.EvaluationData;
import com.yb.entity.Evaluationb;

public interface EvaluationbService {
	List<Evaluationb> queryByDate(Date start,Date end,List<String> station);
	List<Evaluationb> exportByDate(Date start,Date end,List<String> station);
	List<EvaluationData> exportData(Date start,Date end,List<String> station,Integer st,Integer number);
}
