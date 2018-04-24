package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.HeartDao;
import com.yb.entity.Heart;
import com.yb.entity.StationLive;
import com.yb.service.HeartService;

@Service
public class HeartServiceImpl implements HeartService{
	@Autowired
	private HeartDao heartDao;
	@Override
	public List<Heart> queryAll() {
		// TODO Auto-generated method stub
		List<Heart> list = heartDao.queryAll();
		return list;
	}
	@Override
	public List<Heart> queryByNumber(Integer number, String message,List<String> ids) {
		// TODO Auto-generated method stub
		List<Heart> list = heartDao.queryByNumber(number, message,ids);
		return list;
	}
	@Override
	public List<StationLive> queryLive(List<String> station) {
		// TODO Auto-generated method stub
		List<StationLive> list = heartDao.queryLive(station);
		return list;
	}
}
