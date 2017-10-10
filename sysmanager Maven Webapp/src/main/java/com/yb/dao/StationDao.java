package com.yb.dao;

import java.util.List;

import com.yb.entity.Station;


public interface StationDao {
	void update(Station station);
	Station queryById(Integer id);
	List<Station> queryAll();
}
