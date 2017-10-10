package com.yb.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Refuel;

public interface RefuelDao {
	public Refuel query(@Param("start")Date start,@Param("end")Date end,@Param("station")String station);
}
