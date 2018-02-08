package com.yb.util;

import java.util.List;

import com.yb.service.StationService;
import com.yb.service.impl.StationServiceImpl;


public class StationUtils {
	private static StationService stationService=new StationServiceImpl();
	public static List<String> getStationByUserName(String username){
		List<String> stationId = stationService.getStationId(username);
		return stationId;
	}
}
