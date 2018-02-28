package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.WechatmallStatus;



public interface WechatmallDao {
	public List<WechatmallStatus> queryByStationAndTime(@Param("station")List<String> station,
			@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<WechatmallStatus> exportByStationAndTime(@Param("station")List<String> station,
			@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<DataPack> queryTop(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station);
	public List<DataPack> queryTopAll(@Param("start")Date start,@Param("end")Date end);
	
	public List<String> queryAllStation();
}
