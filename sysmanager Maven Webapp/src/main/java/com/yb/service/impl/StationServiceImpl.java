package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.StationDao;
import com.yb.entity.Station;
import com.yb.service.StationService;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationDao stationDao;
	@Override
	public Station queryById(Integer id) {
		// TODO Auto-generated method stub
		Station station = stationDao.queryById(id);
		return station;
	}

	@Override
	public List<Station> queryAll() {
		// TODO Auto-generated method stub
		List<Station> list = stationDao.queryAll();
		return list;
	}

	@Transactional
	@Override
	public void update(Station station) {
		// TODO Auto-generated method stub
		stationDao.update(station);
	}

}
