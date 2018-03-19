package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Station;
import com.yb.entity.StationPack;


public interface StationDao {
	void update(StationPack stationPack);
	StationPack queryById(String id);
	List<StationPack> queryAll();
	List<StationPack> queryByArea(@Param("area")String area);
	//放置为空
	//tag的就放在中间表进行了
	//tagId等于id的全部置空
	void setCategoryNull(Integer id);
	//这是查询全部的属性列表的

	List<String> queryAllAdministriveRegion();
	List<String> queryAllCity(@Param("ids")List<String> ids);
	//根据条件进行层级筛选的
	List<String> queryAdministriveRegionBy(@Param("citys")List<String> citys,@Param("ids")List<String> ids);
	List<String> querySalesAreaBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,@Param("ids")List<String> ids);
	List<String> queryGasolineBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("ids")List<String> ids);
	List<String> queryLocationBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("ids")List<String> ids);
	List<String> queryOpenDateBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("locs")List<String> locs,@Param("ids")List<String> ids);
	List<String> queryTypeBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
	@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("locs")List<String> locs,
	@Param("openDate")List<String> openDate,@Param("ids")List<String> ids);
	List<Station> queryStationBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("locs")List<String> locs,
			@Param("openDate")List<String> openDate,@Param("type")List<String> type,@Param("ids")List<String> ids);
	/**
	 * 给用户分配油站权限 user_station
	 */
	List<String> queryStationByUserId(@Param("user")String user);
	void insertUserStation(@Param("user")String user,@Param("station")List<String> station);
	void deleteByUserId(String user);
	
}
