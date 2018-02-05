package com.yb.service;

import java.util.List;

import com.yb.entity.DataPack;

public interface TargetService {
	public List<DataPack> queryTarget(List<String> station);
	public List<DataPack> exportTarget(List<String> station);//导出分油站
	public List<DataPack> queryTopRate(List<String> station);
	Double queryRate(List<String> station);
	Double queryTargetByMonth(List<String> station);
	Double queryTargetByYear(List<String> station);
}
