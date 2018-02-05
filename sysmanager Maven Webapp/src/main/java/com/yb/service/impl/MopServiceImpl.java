package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.MopDao;
import com.yb.entity.DataPack;
import com.yb.entity.HHT;
import com.yb.entity.Mop;
import com.yb.service.MopService;

@Service
public class MopServiceImpl implements MopService{

	@Autowired
	private MopDao mopDao;
	@Override
	public List<String> queryAllMop() {
		// TODO Auto-generated method stub
		List<String> list = mopDao.queryAllMop();
		return list;
	}

	@Override
	public List<DataPack> queryMop(Date start, Date end, 
			List<String> station,String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMop(start, end,  station,people);
		return list;
	}

	@Override
	public HHT queryHHT(Date start, Date end, List<String> station,String people) {
		// TODO Auto-generated method stub
		HHT hht = mopDao.queryHHT(start, end,  station,people);
		return hht;
	}

	@Override
	public List<DataPack> queryMophht(Date start, Date end, List<String> station,String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMophht(start, end,station,people);
		return list;
	}

	@Override
	public List<DataPack> queryMopipt(Date start, Date end, List<String> station,String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMopipt(start, end, station,people);
		return list;
	}

	@Override
	public List<Mop> queryMopList(Date start, Date end, List<String> station,
			String date, String people) {
		// TODO Auto-generated method stub
		List<Mop> list = mopDao.queryMopList(start, end, station, date, people);
		return list;
	}

	@Override
	public List<Mop> queryHHTList(Date start, Date end, List<String> station,
			String date, String people) {
		// TODO Auto-generated method stub
		List<Mop> list = mopDao.queryHHTList(start, end, station, date, people);
		return list;
	}

	@Override
	public List<Mop> queryIPTList(Date start, Date end, List<String> station,
			String date, String people) {
		// TODO Auto-generated method stub
		List<Mop> list = mopDao.queryIPTList(start, end, station, date, people);
		return list;
	}

	@Override
	public List<Mop> exportMopList(Date start, Date end, List<String> station,
			String date, String people) {
		// TODO Auto-generated method stub
		List<Mop> list = mopDao.exportMopList(start, end, station, date, people);
		return list;
	}

	@Override
	public List<Mop> exportHHTList(Date start, Date end, List<String> station,
			String date, String people) {
		// TODO Auto-generated method stub
		List<Mop> list = mopDao.exportHHTList(start, end, station, date, people);
		return list;
	}

	@Override
	public List<Mop> exportIPTList(Date start, Date end, List<String> station,
			String date, String people) {
		// TODO Auto-generated method stub
		List<Mop> list = mopDao.exportIPTList(start, end, station, date, people);
		return list;
	}

}
