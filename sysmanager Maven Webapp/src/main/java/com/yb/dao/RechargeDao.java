package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Recharge;

public interface RechargeDao {
	public List<Recharge> query(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
}
