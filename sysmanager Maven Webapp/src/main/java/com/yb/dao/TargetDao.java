package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;


public interface TargetDao {
	public List<DataPack> queryTarget(@Param("station")List<String> station);
	//年度目标完成率
	Double queryByYear(@Param("station")List<String> station);
	Double queryReal(@Param("station")List<String> station);
}
