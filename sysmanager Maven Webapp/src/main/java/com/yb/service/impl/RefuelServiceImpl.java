package com.yb.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.RefuelDao;
import com.yb.entity.Refuel;
import com.yb.service.RefuelService;

@Service
public class RefuelServiceImpl implements RefuelService{

	@Autowired
	private RefuelDao refuelDao;
	@Override
	public Refuel query( Date start, Date end, String station) {
		// TODO Auto-generated method stub
		Refuel refuel = refuelDao.query(start, end, station);
		return refuel;
	}

}
