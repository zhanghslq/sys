package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DouPack;
import com.yb.entity.VipFunnel;

public interface VipFunnelDao {
	//漏斗图
	VipFunnel queryVipFunnel(String month);
	List<String> queryAllMonth();
	
	//流失会员人数及占比
	List<DouPack> queryDrain(@Param("date")String date,@Param("start")Date start,@Param("end")Date end);
}
