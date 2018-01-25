package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Recharge;
import com.yb.entity.Rechargeb;

public interface RechargeDao {
	public List<Recharge> query(@Param("start")Date start,@Param("end")Date end,@Param("date")String date,@Param("area")String area);
	public List<Rechargeb> queryByType(@Param("start")Date start,@Param("end")Date end,@Param("date")String date,@Param("area")String area);
}
