package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;

public interface OilPriceDao {
	
	public List<String> queryAllName();//查询所有油品的名字
	
	public List<DataPack> queryPrice(@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("oilName")String oilName);
}
