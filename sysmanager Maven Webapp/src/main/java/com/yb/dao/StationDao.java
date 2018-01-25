package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Station;
import com.yb.entity.StationPack;


public interface StationDao {
	void update(StationPack stationPack);
	StationPack queryById(String id);
	List<StationPack> queryAll();
	//放置为空
	//tag的就放在中间表进行了
	//tagId等于id的全部置空
	void setCategoryNull(Integer id);
	//这是查询全部的属性列表的
	List<String> queryAllDate();
	List<String> queryAllLocation();
	List<String> queryAllGasoline();
	List<String> queryAllDiesel();
	List<String> queryAllCity();
	List<String> queryAllSalesArea();
	List<String> queryAllAdministriveRegion();
	List<String> queryAllOpenDate();
	//根据条件进行层级筛选的
	List<String> queryAdministriveRegionBy(@Param("citys")List<String> citys);
	List<String> querySalesAreaBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions);
	List<String> queryGasolineBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales);
	List<String> queryLocationBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline);
	List<String> queryOpenDateBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("locs")List<String> locs);
	List<String> queryTypeBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
	@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("locs")List<String> locs,
	@Param("openDate")List<String> openDate);
	List<Station> queryStationBy(@Param("citys")List<String> citys,@Param("regions")List<String> regions,
			@Param("sales")List<String> sales,@Param("gasoline")List<String> gasoline,@Param("locs")List<String> locs,
			@Param("openDate")List<String> openDate,@Param("type")List<String> type);
	List<String> queryStationByUserId(@Param("uid")String uid);
	
}
