package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Lube;
import com.yb.entity.LubeAndVip;

public interface LubeDao {
	List<Lube> queryLubes(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	List<LubeAndVip> queryAllAndVip(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	List<LubeAndVip> exportAllAndVip(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
}
