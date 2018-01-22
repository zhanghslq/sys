package com.yb.service;

import java.util.List;

import com.yb.entity.Station;
import com.yb.entity.StationPack;

public interface StationService {
	StationPack queryById(String id);
	List<StationPack> queryAll();
	void update(StationPack stationPack);
	
	List<String> queryAllDate();
	List<String> queryAllLocation();
	List<String> queryAllGasoline();
	List<String> queryAllDiesel();
	List<String> queryAllCity();
	List<String> queryAllSalesArea();
	List<String> queryAllAdministriveRegion();
	List<String> queryAllOpenDate();
	
	List<String> queryAdministriveRegionBy(List<String> citys);
	List<String> querySalesAreaBy(List<String> citys,List<String> regions);
	List<String> queryGasolineBy(List<String> citys,List<String> regions,
			List<String> sales);
	List<String> queryLocationBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline);
	List<String> queryOpenDateBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> locs);
	List<String> queryTypeBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> locs,
			List<String> openDate);
	
	List<Station> queryStationBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> locs,
			List<String> openDate,List<String> type);
}
