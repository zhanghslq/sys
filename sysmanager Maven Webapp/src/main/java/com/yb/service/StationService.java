package com.yb.service;

import java.util.List;

import com.yb.entity.PermissionPack;
import com.yb.entity.Station;
import com.yb.entity.StationPack;

public interface StationService {
	StationPack queryById(String id);
	List<StationPack> queryAll();
	void update(StationPack stationPack);
	List<StationPack> queryByArea(String area);
	
	List<String> queryAllCity(List<String> ids);
	List<String> queryAdministriveRegionBy(List<String> citys,List<String> ids);
	List<String> querySalesAreaBy(List<String> citys,List<String> regions,List<String> ids);
	List<String> queryGasolineBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> ids);
	List<String> queryLocationBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> ids);
	List<String> queryOpenDateBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> locs,List<String> ids);
	List<String> queryTypeBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> locs,
			List<String> openDate,List<String> ids);
	
	List<Station> queryStationBy(List<String> citys,List<String> regions,
			List<String> sales,List<String> gasoline,List<String> locs,
			List<String> openDate,List<String> type,List<String> ids);
	
	List<PermissionPack> queryForGrant(String username);
	
	void updateGrantForUser(String uname,List<String> station);
	public  List<String> getStationId(String username);
}
