package com.yb.dao;

import java.util.List;

import com.yb.entity.Station;

public interface CityDao {
	List<String> queryAllCitys();
	List<String> queryStations(String city);
	List<Station> queryAll();
}
