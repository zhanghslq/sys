package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OilGunDao {
	
	public List<String> queryGun(@Param("station")String station);

}
