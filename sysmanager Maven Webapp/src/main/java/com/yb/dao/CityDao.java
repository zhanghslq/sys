package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Station;

public interface CityDao {
	List<String> queryAllCitys();
	List<String> queryStations(@Param("ids")List<String> ids);
	List<Station> queryAll(@Param("ids")List <String> ids);
}
