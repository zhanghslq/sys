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
	public List<DataPack> queryTarget(String station) {
		// TODO Auto-generated method stub
		 List<DataPack> list = targetDao.queryTarget(station);
		return list;
	}
	@Override
	public Double queryRate(String station) {
		// TODO Auto-generated method stub
		Double dataDouble=0.0;
		Double targetDouble = targetDao.queryByYear(station);
		Double queryReal = targetDao.queryReal(station);
		if(targetDouble!=null&&queryReal!=null){
			dataDouble=queryReal/targetDouble;
		}
		return dataDouble;
	}
}
