package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Channel;
import com.yb.entity.DataPack;


public interface VipChannelDao {
	Channel queryChannel(@Param("start")Date start,@Param("end")Date end,@Param("area")String area);
	List<DataPack> queryRate(@Param("start")Date start,@Param("end")Date end,@Param("query")String query,@Param("area")String area);
}
