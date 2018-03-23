package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.HomePack;
import com.yb.entity.Price;

public interface HomePageDao {
	//降水量和油品销售量，非油销售额的对比
	public List<HomePack> queryList(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station);
	public List<HomePack> queryOilList(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station);
	public List<HomePack> queryNotOilList(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station);
	//各种商品占比,不需要时间
	public List<DataPack> queryOil();
	public List<DataPack> queryNotOil();
	//查询所有商品种类
	public List<String> queryAll();
	//指导油价
	public List<Price> queryPriceBei(@Param("start")Date start,@Param("end")Date end);
	
	public List<Price> queryCheng(@Param("start")Date start,@Param("end")Date end);
}
