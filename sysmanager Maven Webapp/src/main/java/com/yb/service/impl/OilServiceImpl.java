package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.OilDao;
import com.yb.entity.Oil;
import com.yb.entity.OilAndVip;
import com.yb.entity.Oilb;
import com.yb.service.OilService;

@Service
public class OilServiceImpl implements OilService{

	@Autowired
	private OilDao oilDao;
	@Override
	public List<Oil> queryOils(String date, Date start, Date end,
			List<String> station,String people) {//查询出来的销售金额是单笔销售额
		// TODO Auto-generated method stub
		List<Oil> list = oilDao.queryOils(date, start, end, station,people);
		
		return list;
	}
	@Override
	public List<Oilb> queryByOils(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Oilb> list = oilDao.queryByOils(date, start, end, station);
		return list;
	}
	@Override
	public List<String> queryAllName() {
		// TODO Auto-generated method stub
		List<String> list = oilDao.queryAllName();
		return list;
	}
	@Override
	public List<Oil> queryzhanbi(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		List<Oil> list = oilDao.queryzhanbi(start, end, station);
		return list;
	}
	@Override
	public Oil queryCompare(Date start, Date end, List<String> station,
			String oilName,String people) {
		// TODO Auto-generated method stub
		Oil oil = oilDao.queryCompare(start, end, station, oilName,people);
		return oil;
	}
	@Override
	public List<OilAndVip> queryAllAndVip(String date, Date start, Date end,
			List<String> station,List<Integer> weIntegers) {
		// TODO Auto-generated method stub
		List<OilAndVip> list = oilDao.queryAllAndVip(date, start, end, station,weIntegers);
		return list;
	}
	@Override
	public Oil queryOilsByType(Date start, Date end, List<String> station,
			List<String> oilNames, String people) {
		// TODO Auto-generated method stub
		Oil queryOilsByType = oilDao.queryOilsByType(start, end, station, oilNames, people);
		return queryOilsByType;
	}
	@Override
	public List<OilAndVip> queryAllAndVipByOils(String date, Date start,
			Date end, List<String> station, String oils) {
		// TODO Auto-generated method stub
		List<OilAndVip> list = oilDao.queryAllAndVipByOils(date, start, end, station, oils);
		return list;
	}
	@Override
	public List<OilAndVip> exportAllAndVip(String date, Date start, Date end,
			List<String> station,List<Integer> weIntegers) {
		// TODO Auto-generated method stub
		List<OilAndVip> list = oilDao.exportAllAndVip(date, start, end, station,weIntegers);
		return list;
	}
	@Override
	public List<Oilb> exportByOils(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<Oilb> list = oilDao.exportByOils(date, start, end, station);
		return list;
	}
	@Override
	public List<OilAndVip> exportAllAndVipByOils(String date, Date start,
			Date end, List<String> station, String oils) {
		// TODO Auto-generated method stub
		List<OilAndVip> list = oilDao.exportAllAndVipByOils(date, start, end, station, oils);
		return list;
	}
	@Override
	public List<Oil> exportCompare(Date start, Date end, List<String> station,
			String oilName, String people) {
		// TODO Auto-generated method stub
		List<Oil> list = oilDao.exportCompare(start, end, station, oilName, people);
		return list;
	}

}
