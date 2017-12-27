package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.StationDao;
import com.yb.entity.Station;
import com.yb.entity.StationPack;
import com.yb.service.StationService;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationDao stationDao;
	@Override
	public StationPack queryById(String id) {
		// TODO Auto-generated method stub
		StationPack station = stationDao.queryById(id);
		return station;
	}

	@Override
	public List<StationPack> queryAll() {
		// TODO Auto-generated method stub
		List<StationPack> list = stationDao.queryAll();
		return list;
	}

	@Transactional
	@Override
	public void update(StationPack stationPack) {//油站的修改
		// TODO Auto-generated method stub
		stationDao.update(stationPack);
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

	@Override
	public List<String> queryAdministriveRegionBy(List<String> citys) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAdministriveRegionBy(citys);
		return list;
	}

	@Override
	public List<String> querySalesAreaBy(List<String> citys,
			List<String> regions) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.querySalesAreaBy(citys, regions);
		return list;
	}

	@Override
	public List<String> queryGasolineBy(List<String> citys,
			List<String> regions, List<String> sales) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryGasolineBy(citys, regions, sales);
		return list;
	}

	@Override
	public List<String> queryLocationBy(List<String> citys,
			List<String> regions, List<String> sales, List<String> gasoline) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryLocationBy(citys, regions, sales, gasoline);
		return list;
	}

	@Override
	public List<String> queryOpenDateBy(List<String> citys,
			List<String> regions, List<String> sales, List<String> gasoline,
			List<String> locs) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryOpenDateBy(citys, regions, sales, gasoline, locs);
		return list;
	}

	@Override
	public List<Station> queryStationBy(List<String> citys,
			List<String> regions, List<String> sales, List<String> gasoline,
			List<String> locs, List<String> openDate) {
		// TODO Auto-generated method stub
		List<Station> list = stationDao.queryStationBy(citys, regions, sales, gasoline, locs, openDate);
		return list;
	}

	@Override
	public List<String> queryAllSalesArea() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllSalesArea();
		return list;
	}

	@Override
	public List<String> queryAllAdministriveRegion() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllAdministriveRegion();
		return list;
	}

	@Override
	public List<String> queryAllOpenDate() {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllOpenDate();
		return list;
	}

}
