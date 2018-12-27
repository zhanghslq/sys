package com.yb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Heart;
import com.yb.entity.StationLive;

public interface HeartService {
	List<Heart> queryAll();
	List<Heart> queryByNumber(Integer number,String message,List<String> ids);
	List<StationLive> queryLive(List<String> station);

	String queryTime(String name);
}
