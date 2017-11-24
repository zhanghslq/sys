package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.OilDao;
import com.yb.entity.Oil;
import com.yb.entity.Oilb;
import com.yb.service.OilService;

@Service
public class OilServiceImpl implements OilService{

	@Autowired
	private OilDao oilDao;
	@Override
	public List<Oil> queryOils(String date, Date start, Date end,
			String station, String query,String people) {//查询出来的销售金额是单笔销售额
		// TODO Auto-generated method stub
		List<Oil> list = oilDao.queryOils(date, start, end, station, query,people);
		
		return list;
	}
	@Override
	public List<Oilb> queryByOils(String date, Date start, Date end,
			String station, String query) {
		// TODO Auto-generated method stub
		List<Oilb> list = oilDao.queryByOils(date, start, end, station, query);
		return list;
	}
	@Override
	public List<String> queryAllName() {
		// TODO Auto-generated method stub
		List<String> list = oilDao.queryAllName();
		return list;
	}
	@Override
	public List<Oil> queryzhanbi(Date start, Date end, String station,
			String query) {
		// TODO Auto-generated method stub
		List<Oil> list = oilDao.queryzhanbi(start, end, station, query);
		return list;
	}
	@Override
	public Oil queryCompare(Date start, Date end, String station, String query,
			String oilName,String people) {
		// TODO Auto-generated method stub
		Oil oil = oilDao.queryCompare(start, end, station, query, oilName,people);
		return oil;
	}

}
