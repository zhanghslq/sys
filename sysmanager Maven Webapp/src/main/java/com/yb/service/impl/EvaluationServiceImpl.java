package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.EvaluationDao;
import com.yb.entity.DataPack;
import com.yb.entity.Evaluation;
import com.yb.service.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService{

	@Autowired
	private EvaluationDao evaluationDao;
	@Override
	public List<Evaluation> queryTrend(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Evaluation> list = evaluationDao.queryTrend(date, start, end, station);
		return list;
	}

	@Override
	public Evaluation queryDistribution(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		Evaluation evaluation = evaluationDao.queryDistribution(start, end, station);
		return evaluation;
	}

	@Override
	public List<Evaluation> queryEvaluations(Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Evaluation> list = evaluationDao.queryEvaluations(start, end, station);
		return list;
	}

	@Override
	public List<Evaluation> exportTrend(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Evaluation> list = evaluationDao.exportTrend(date, start, end, station);
		return list;
	}

	@Override
	public List<Evaluation> exportDistribution(Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Evaluation> list = evaluationDao.exportDistribution(start, end, station);
		return list;
	}

	@Override
	public List<DataPack> queryRate(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = evaluationDao.queryRate(date, start, end, station);
		return list;
	}

	@Override
	public List<DataPack> exportRate(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = evaluationDao.exportRate(date, start, end, station);
		return list;
	}

}
