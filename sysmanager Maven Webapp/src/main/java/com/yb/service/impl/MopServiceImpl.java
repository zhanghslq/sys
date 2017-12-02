package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.MopDao;
import com.yb.entity.DataPack;
import com.yb.entity.HHT;
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
			List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMop(start, end,  station);
		return list;
	}

	@Override
	public HHT queryHHT(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		HHT hht = mopDao.queryHHT(start, end,  station);
		return hht;
	}

	@Override
	public List<DataPack> queryMophht(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMophht(start, end,station);
		return list;
	}

	@Override
	public List<DataPack> queryMopipt(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMopipt(start, end, station);
		return list;
	}

}
