package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.LubeDao;
import com.yb.entity.Lube;
import com.yb.entity.LubeAndVip;
import com.yb.service.LubeService;

@Service
public class LubeServiceImpl implements LubeService{

	@Autowired
	private LubeDao lubeDao;
	
	@Override
	public List<Lube> queryLubes(String date, Date start, Date end,
			List<String>station,String people) {
		// TODO Auto-generated method stub
		List<Lube> list = lubeDao.queryLubes(date, start, end, station,people);
		return list;
	}

	@Override
	public List<LubeAndVip> queryAllAndVip(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<LubeAndVip> list = lubeDao.queryAllAndVip(date, start, end, station);
		return list;
	}

	@Override
	public List<LubeAndVip> exportAllAndVip(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<LubeAndVip> list = lubeDao.exportAllAndVip(date, start, end, station);
		return list;
	}

}
