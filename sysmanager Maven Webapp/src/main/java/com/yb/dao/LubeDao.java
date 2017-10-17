package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Lube;

public interface LubeDao {
	List<Lube> queryLubes(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);
}
