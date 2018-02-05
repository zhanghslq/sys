package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;


public interface TargetDao {
	public List<DataPack> queryTarget(@Param("station")List<String> station);
	public List<DataPack> exportTarget(@Param("station")List<String> station);
	public List<DataPack> queryTopRate(@Param("station")List<String> station);
	//年度目标完成率
	Double queryByYear(@Param("station")List<String> station);
	Double queryTargetByMonth(@Param("station")List<String> station);
	Double queryTargetByYear(@Param("station")List<String> station);
	Double queryReal(@Param("station")List<String> station);
}
