package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.EvaluationbDao;
import com.yb.entity.EvaluationData;
import com.yb.entity.Evaluationb;
import com.yb.service.EvaluationbService;

@Service
public class EvaluationbServiceImpl implements EvaluationbService{
	
	@Autowired
	private EvaluationbDao evaluationbDao;
	@Override
	public List<Evaluationb> queryByDate(Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Evaluationb> list = evaluationbDao.queryByDate(start, end, station);
		return list;
	}
	@Override
	public List<Evaluationb> exportByDate(Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Evaluationb> list = evaluationbDao.exportByDate(start, end, station);
		return list;
	}
	@Override
	public List<EvaluationData> exportData(Date start, Date end,
			List<String> station,Integer st,Integer number) {
		// TODO Auto-generated method stub
		List<EvaluationData> list = evaluationbDao.exportData(start, end, station,st,number);
		return list;
	}
	
}
