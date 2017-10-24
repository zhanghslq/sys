package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.MopDao;
import com.yb.entity.DataPack;
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
	public List<DataPack> queryMop(Date start, Date end, String query,
			String station) {
		// TODO Auto-generated method stub
		List<DataPack> list = mopDao.queryMop(start, end, query, station);
		return list;
	}

}
