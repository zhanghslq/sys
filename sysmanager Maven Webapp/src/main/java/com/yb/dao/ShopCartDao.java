package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.InterPack;

public interface ShopCartDao {
	List<InterPack> query(@Param("station")List<String> station,@Param("start")Date start,@Param("end")Date end,
			@Param("oil")String oil);
}
