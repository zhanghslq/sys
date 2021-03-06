package com.yb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.StationDao;
import com.yb.entity.PermissionPack;
import com.yb.entity.Station;
import com.yb.entity.StationPack;
import com.yb.service.StationService;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationDao stationDao;
	@Cacheable(value="station")
	@Override
	public  List<String> getStationId(String username){
		List<String> queryStationByUserId = stationDao.queryStationByUserId(username);
		if(queryStationByUserId.size()==0){
			return null;
		}
		return queryStationByUserId;
	}
	
	@Override
	public StationPack queryById(String id) {
		// TODO Auto-generated method stub
		StationPack station = stationDao.queryById(id);
		return station;
	}
	@Cacheable(value="station")
	@Override
	public List<StationPack> queryAll() {
		// TODO Auto-generated method stub
		List<StationPack> list = stationDao.queryAll();
		return list;
	}
	@CacheEvict(value="station",allEntries=true)
	@Transactional
	@Override
	public void update(StationPack stationPack) {//油站的修改
		// TODO Auto-generated method stub
		stationDao.update(stationPack);
	}
	@Cacheable(value="station")
	@Override
	public List<String> queryAllCity(List<String> ids) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAllCity(ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<String> queryAdministriveRegionBy(List<String> citys,List<String> ids) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryAdministriveRegionBy(citys,ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<String> querySalesAreaBy(List<String> citys,
			List<String> regions,List<String> ids) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.querySalesAreaBy(citys, regions,ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<String> queryGasolineBy(List<String> citys,
			List<String> regions, List<String> sales,List<String> ids) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryGasolineBy(citys, regions, sales,ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<String> queryLocationBy(List<String> citys,
			List<String> regions, List<String> sales, List<String> gasoline,List<String> ids) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryLocationBy(citys, regions, sales, gasoline,ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<String> queryOpenDateBy(List<String> citys,
			List<String> regions, List<String> sales, List<String> gasoline,
			List<String> locs,List<String> ids) {
		// TODO Auto-generated method stub
		List<String> list = stationDao.queryOpenDateBy(citys, regions, sales, gasoline, locs,ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<Station> queryStationBy(List<String> citys,
			List<String> regions, List<String> sales, List<String> gasoline,
			List<String> locs, List<String> openDate,List<String> type,List<String> ids) {
		// TODO Auto-generated method stub
		List<Station> list = stationDao.queryStationBy(citys, regions, sales, gasoline, locs, openDate,type,ids);
		return list;
	}
	@Cacheable(value="station")
	@Override
	public List<String> queryTypeBy(List<String> citys, List<String> regions,
			List<String> sales, List<String> gasoline, List<String> locs,
			List<String> openDate,List<String> ids) {
		// TODO Auto-generated method stub
		List<String> queryTypeBy = stationDao.queryTypeBy(citys, regions, sales, gasoline, locs, openDate,ids);
		return queryTypeBy;
	}
	@Override
	public List<PermissionPack> queryForGrant(String username) {
		// TODO Auto-generated method stub
		List<StationPack> list = stationDao.queryByArea("北京");
		List<StationPack> list2 = stationDao.queryByArea("承德");
		List<PermissionPack> pack = new ArrayList<PermissionPack>();
		List<PermissionPack> pack2 = new ArrayList<PermissionPack>();
		for (StationPack stationPack : list) {
			pack.add(new PermissionPack(stationPack.getId(), stationPack.getName(),false,null));
		}
		List<String> queryStationByUserId = stationDao.queryStationByUserId(username);
		for (PermissionPack permissionPack : pack) {
			for (String string : queryStationByUserId) {
				if(permissionPack.getId().equals(string)){
					permissionPack.setChecked(true);
				}
			}
		}
		for (StationPack stationPack : list2) {
			pack2.add(new PermissionPack(stationPack.getId(), stationPack.getName(),false,null));
		}
		for (PermissionPack permissionPack : pack2) {
			for (String string : queryStationByUserId) {
				if(permissionPack.getId().equals(string)){
					permissionPack.setChecked(true);
				}
			}
		}
		List<PermissionPack> arrayList = new ArrayList<PermissionPack>();
		arrayList.add(new PermissionPack("test", "北京油站", false, pack));
		arrayList.add(new PermissionPack("cd", "承德油站", false, pack2));
		return arrayList;
	}
	@Transactional
	@Override
	@CacheEvict(value="station")
	public void updateGrantForUser(String uname, List<String> station) {
		// TODO Auto-generated method stub
		stationDao.deleteByUserId(uname);
		stationDao.insertUserStation(uname, station);
	}
	@Cacheable(value="station")
	@Override
	public List<StationPack> queryByArea(String area) {
		// TODO Auto-generated method stub
		List<StationPack> list = stationDao.queryByArea(area);
		return list;
	}
}
