package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.CityDao;
import com.yb.entity.Station;
import com.yb.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	@Override
	public List<String> queryCitys() {
		// TODO Auto-generated method stub
		List<String> list = cityDao.queryAllCitys();
		return list;
	}

	@Override
	public List<String> queryStations(List<String> ids) {
		// TODO Auto-generated method stub
		List<String> queryStations = cityDao.queryStations(ids);
		return queryStations;
	}

	@Override
	public List<Station> queryAll(List <String> ids) {
		// TODO Auto-generated method stub
		List<Station> queryAll = cityDao.queryAll(ids);
		
		return queryAll;
	}
	
}
