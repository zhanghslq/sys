package com.yb.service;

import java.util.List;

import com.yb.entity.Station;

public interface StationService {
	Station queryById(Integer id);
	List<Station> queryAll();
	void update(Station station);
}
