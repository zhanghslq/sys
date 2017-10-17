package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.LubeDao;
import com.yb.entity.Lube;
import com.yb.service.LubeService;

@Service
public class LubeServiceImpl implements LubeService{

	@Autowired
	private LubeDao lubeDao;
	
	@Override
	public List<Lube> queryLubes(String date, Date start, Date end,
			String station, String query) {
		// TODO Auto-generated method stub
		List<Lube> list = lubeDao.queryLubes(date, start, end, station, query);
		return list;
	}

}
