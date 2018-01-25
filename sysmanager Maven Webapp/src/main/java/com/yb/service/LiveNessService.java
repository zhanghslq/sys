package com.yb.service;

import java.util.List;

import com.yb.entity.VipLiveness;

public interface LiveNessService {
	List<String> queryAllDate(String area);
	List<VipLiveness> queryData(String area);
	VipLiveness queryDataByDate(String month,String area);
	List<String> queryAllYearDate(String area);
	
	VipLiveness queryLiveNessByYear(String area,String year);
	List<VipLiveness> queryLivessByStation(String station);
}
