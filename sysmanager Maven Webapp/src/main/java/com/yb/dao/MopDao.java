package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;

public interface MopDao {
	List<String> queryAllMop();
	List<DataPack> queryMop(@Param("start")Date start,@Param("end")Date end,
			@Param("query")String query,@Param("station")String station);
}
