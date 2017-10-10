package com.yb.dao;

import java.util.Date;

import com.yb.entity.LubeAmount;

public interface LubeAmountDao {
	public LubeAmount queryByDate(Date date,String station);
	
}
