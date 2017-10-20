package com.yb.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Channel;


public interface VipChannelDao {
	Channel queryChannel(@Param("start")Date start,@Param("end")Date end);
}
