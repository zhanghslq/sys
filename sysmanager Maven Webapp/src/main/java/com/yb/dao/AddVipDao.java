package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.AddVip;


public interface AddVipDao {
	public List<AddVip> query(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,@Param("area")String area);
}
