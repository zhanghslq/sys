package com.yb.dao;

import java.util.List;

import com.yb.entity.Station;


public interface StationDao {
	void update(Station station);
	Station queryById(String id);
	List<Station> queryAll();
	
	
	//放置为空
	void setTagNull(Integer id);//tagId等于id的全部置空
	void setCategoryNull(Integer id);
}
