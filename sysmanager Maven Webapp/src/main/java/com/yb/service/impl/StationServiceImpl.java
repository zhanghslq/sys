package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.StationDao;
import com.yb.dao.TagDao;
import com.yb.entity.Station;
import com.yb.entity.StationPack;
import com.yb.service.StationService;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationDao stationDao;
	@Autowired
	private TagDao tagDao;
	@Override
	public Station queryById(String id) {
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
	public void update(StationPack stationPack) {//油站的修改
		// TODO Auto-generated method stub
		stationDao.update(stationPack);
		tagDao.deleteByStationId(stationPack.getId());//清除原来的数据
		if(stationPack.getTags()!=null&&stationPack.getTags().size()!=0){//标签不为空
			for (Integer tagId : stationPack.getTags()) {
				tagDao.addTagStation(stationPack.getId(), tagId);//添加中间表的数据
			}
		}
		
	}

	@Override
	public List<String> queryAllDate() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllDate();
		return list;
	}

	@Override
	public List<String> queryAllLocation() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllLocation();
		return list;
	}

	@Override
	public List<String> queryAllGasoline() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllGasoline();
		return list;
	}

	@Override
	public List<String> queryAllDiesel() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllDiesel();
		return list;
	}

	@Override
	public List<String> queryAllCity() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllCity();
		return list;
	}

}
