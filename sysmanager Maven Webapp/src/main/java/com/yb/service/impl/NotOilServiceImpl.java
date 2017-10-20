package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.NotOilDao;
import com.yb.entity.NotOil;
import com.yb.entity.Oil;
import com.yb.service.NotOilService;

@Service
public class NotOilServiceImpl implements NotOilService{

	@Autowired
	private NotOilDao notOilDao;
	@Override
	public List<NotOil> queryNotOils(String date, Date start, Date end,
			String station, String query) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.queryNotOils(date, start, end, station, query);
		
		return list;
	}
	@Override
	public List<NotOil> queryByDepartmentName(String date, Date start, Date end,
			String station, String query, String departmentName) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.queryByDepartmentName(date, start, end, station, query, departmentName);
		return list;
	}
	@Override
	public List<String> queryAllName() {
		// TODO Auto-generated method stub
		List<String> list = notOilDao.queryAllName();
		return list;
	}
	@Override
	public List<NotOil> queryRate(String date, Date start, Date end,
			String station, String query) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.queryRate(date, start, end, station, query);
		return list;
	}

}