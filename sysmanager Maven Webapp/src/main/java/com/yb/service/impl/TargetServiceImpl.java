package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.TargetDao;
import com.yb.entity.DataPack;
import com.yb.service.TargetService;

@Service
public class TargetServiceImpl implements TargetService{

	@Autowired
	private TargetDao targetDao;
	@Override
	public List<DataPack> queryTarget(List<String> station) {
		// TODO Auto-generated method stub
		 List<DataPack> list = targetDao.queryTarget(station);
		return list;
	}
	@Override
	public Double queryRate(List<String> station) {
		// TODO Auto-generated method stub
		Double dataDouble=0.0;
		Double targetDouble = targetDao.queryByYear(station);
		Double queryReal = targetDao.queryReal(station);
		if(targetDouble!=null&&queryReal!=null){
			dataDouble=queryReal/targetDouble;
		}
		return dataDouble;
	}
	@Override
	public Double queryTargetByMonth(List<String> station) {
		// TODO Auto-generated method stub
		Double queryTargetByMonth = targetDao.queryTargetByMonth(station);
		return queryTargetByMonth;
	}
	@Override
	public List<DataPack> queryTopRate(List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> queryTopRate = targetDao.queryTopRate(station);
		return queryTopRate;
	}
	@Override
	public Double queryTargetByYear(List<String> station) {
		// TODO Auto-generated method stub
		Double double1 = targetDao.queryTargetByYear(station);
		return double1;
	}
	@Override
	public List<DataPack> exportTarget(List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = targetDao.exportTarget(station);
		return list;
	}
}
