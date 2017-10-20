package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.OilPriceDao;
import com.yb.entity.DataPack;
import com.yb.service.OilPriceService;

@Service
public class OilPriceServiceImpl implements OilPriceService{

	@Autowired
	private OilPriceDao oilPriceDao;
	@Override
	public List<String> queryAllName() {
		// TODO Auto-generated method stub
		List<String> list = oilPriceDao.queryAllName();
		return list;
	}

	@Override
	public List<DataPack> queryPrice(Date start, Date end, String station,
			String oilName) {
		// TODO Auto-generated method stub
		List<DataPack> list = oilPriceDao.queryPrice(start, end, station, oilName);
		return list;
	}

}
