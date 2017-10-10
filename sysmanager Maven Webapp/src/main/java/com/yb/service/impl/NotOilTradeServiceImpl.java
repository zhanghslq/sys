package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.NotOilTradeDao;
import com.yb.entity.NotOilTrade;
import com.yb.service.NotOilTradeService;

@Service
public class NotOilTradeServiceImpl implements NotOilTradeService{

	@Autowired
	private NotOilTradeDao notOilTradeDao;
	@Override
	public List<NotOilTrade> query(String station, String date, Date start,
			Date end) {
		// TODO Auto-generated method stub
		List<NotOilTrade> list = notOilTradeDao.query(station, date, start, end);
		return list;
	}
}
