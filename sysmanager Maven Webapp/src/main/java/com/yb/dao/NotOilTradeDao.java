package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.NotOilTrade;

public interface NotOilTradeDao {
	public 	List<NotOilTrade> query(@Param("station")String station,@Param("date")String date,@Param("start")Date start,@Param("end")Date end);
}
