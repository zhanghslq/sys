package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Heart;
import com.yb.entity.StationLive;

public interface HeartDao {
	List<Heart> queryAll();
	List<Heart> queryByNumber(@Param("number")Integer number,@Param("message")String message,@Param("ids")List<String> ids);
	//查询油站运营天数的
	List<StationLive> queryLive(@Param("station")List<String> station);
}
