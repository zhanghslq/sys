package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;

public interface ProductDao {
	List<DataPack> queryProduct(@Param("start")Date start,@Param("end")Date end,@Param("station")String station);
}
