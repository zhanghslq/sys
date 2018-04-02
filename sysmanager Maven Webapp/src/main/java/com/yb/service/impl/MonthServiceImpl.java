package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.MonthDao;
import com.yb.service.MonthService;

@Service
public class MonthServiceImpl implements MonthService{

	@Autowired
	private MonthDao monthDao;
	@Override
	public List<String> queryAllYear() {
		// TODO Auto-generated method stub
		List<String> list = monthDao.queryAllYear();
		return list;
	}

	@Override
	public List<String> queryByYear(String year) {
		// TODO Auto-generated method stub
		List<String> list = monthDao.queryByYear(year);
		return list;
	}

	@Override
	public void insert(String year, String month) {
		// TODO Auto-generated method stub
		monthDao.insert(year, month);
	}

}
