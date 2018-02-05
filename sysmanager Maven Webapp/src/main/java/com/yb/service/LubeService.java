package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Lube;
import com.yb.entity.LubeAndVip;

public interface LubeService {
	List<Lube> queryLubes(String date,Date start,Date end,
			List<String> station,String people);
	List<LubeAndVip> queryAllAndVip(String date,Date start,Date end,
			List<String> station);
	List<LubeAndVip> exportAllAndVip(String date,Date start,Date end,
			List<String> station);
}
