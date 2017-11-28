package com.yb.dao;

import java.util.List;

import com.yb.entity.Station;
import com.yb.entity.StationPack;


public interface StationDao {
	void update(StationPack stationPack);
	Station queryById(String id);
	List<Station> queryAll();
	//放置为空
	//tag的就放在中间表进行了
	//tagId等于id的全部置空
	void setCategoryNull(Integer id);
	
	List<String> queryAllDate();
	List<String> queryAllLocation();
	List<String> queryAllGasoline();
	List<String> queryAllDiesel();
	List<String> queryAllCity();
}
