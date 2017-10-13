package com.yb.service;

import java.util.List;

import com.yb.entity.Station;
import com.yb.entity.StationPack;

public interface StationService {
	Station queryById(String id);
	List<Station> queryAll();
	void update(StationPack stationPack);
}
