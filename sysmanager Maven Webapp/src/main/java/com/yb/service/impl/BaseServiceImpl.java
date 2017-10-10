package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.BaseDao;
import com.yb.entity.AmountPack;
import com.yb.entity.TradeAmount;
import com.yb.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService{

	@Autowired
	private BaseDao baseDao;
	

	@Override
	public List<AmountPack> zoushi(String date,String station, String oilName, Date start,
			Date end) {
		// TODO Auto-generated method stub
		List<AmountPack> list = baseDao.zoushi(date,station, oilName, start, end);
		return list;
	}

	@Override
	public List<TradeAmount> zhanbi(String station, Date start) {
		// TODO Auto-generated method stub
		List<TradeAmount> list = baseDao.zhanbi(station, start);
		return list;
	}

}
