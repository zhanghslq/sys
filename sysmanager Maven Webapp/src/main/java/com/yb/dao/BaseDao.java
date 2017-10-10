package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.AmountPack;
import com.yb.entity.TradeAmount;

public interface BaseDao {
	public List<AmountPack> zoushi(@Param("date")String date,@Param("station")String station,@Param("oilName")String oilName,@Param("start")Date start,@Param("end")Date end);//营业额走势图
	//油品占比
	public List<TradeAmount> zhanbi(@Param("station")String station,@Param("start")Date start);
	
	//销售效率，平均每单的销售量，燃油，非油，润滑油
	


}
