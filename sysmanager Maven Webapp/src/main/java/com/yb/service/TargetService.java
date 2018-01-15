package com.yb.service;

import java.util.List;

import com.yb.entity.DataPack;

public interface TargetService {
	public List<DataPack> queryTarget(List<String> station);
	public List<DataPack> queryTopRate(List<String> station);
	Double queryRate(List<String> station);
	
	Double queryTargetByMonth(List<String> station);
}
