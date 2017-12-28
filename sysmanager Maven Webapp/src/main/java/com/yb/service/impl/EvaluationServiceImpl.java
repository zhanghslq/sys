package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.EvaluationDao;
import com.yb.entity.Evaluation;
import com.yb.service.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService{

	@Autowired
	private EvaluationDao evaluationDao;
	@Override
	public List<Evaluation> queryTrend(String date, Date start, Date end,
			List<String> station,String area) {
		// TODO Auto-generated method stub
		List<Evaluation> list = evaluationDao.queryTrend(date, start, end, station,area);
		return list;
	}

	@Override
	public Evaluation queryDistribution(Date start, Date end, List<String> station,String area) {
		// TODO Auto-generated method stub
		Evaluation evaluation = evaluationDao.queryDistribution(start, end, station,area);
		return evaluation;
	}

	@Override
	public List<Evaluation> queryEvaluations(Date start, Date end,
			List<String> station,String area) {
		// TODO Auto-generated method stub
		List<Evaluation> list = evaluationDao.queryEvaluations(start, end, station,area);
		return list;
	}

}
