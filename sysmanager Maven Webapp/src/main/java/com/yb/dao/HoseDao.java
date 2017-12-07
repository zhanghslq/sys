package com.yb.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface HoseDao {
	Double queryHoseByPumpAndDate(@Param("start")Date start,@Param("end")Date end,
			@Param("pump")String pump,@Param("hose")Integer hose,@Param("station")String station);
}
