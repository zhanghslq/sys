package com.yb.service;

import java.util.List;

import com.yb.entity.Station;

public interface CityService {
	List<String> queryCitys();
	List<String> queryStations(String city);
	List<Station> queryAll();
}
