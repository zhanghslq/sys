package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Lube;

public interface LubeService {
	List<Lube> queryLubes(String date,Date start,Date end,
			String station,String query,String people);
}
