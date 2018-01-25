package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.LiveNessDao;
import com.yb.entity.VipLiveness;
import com.yb.service.LiveNessService;

@Service
public class LiveNessServiceImpl implements LiveNessService{

	@Autowired
	private LiveNessDao liveNessDao;
	@Override
	public List<String> queryAllDate(String area) {
		// TODO Auto-generated method stub
		List<String> list = liveNessDao.queryAllDate(area);
		return list;
	}

	@Override
	public List<VipLiveness> queryData(String area) {
		// TODO Auto-generated method stub
		 List<VipLiveness> list = liveNessDao.queryData(area);
		return list;
	}

	@Override
	public VipLiveness queryDataByDate(String month,String area) {
		// TODO Auto-generated method stub
		VipLiveness list = liveNessDao.queryDataByDate(month,area);
		return list;
	}

	@Override
	public List<String> queryAllYearDate(String area) {
		// TODO Auto-generated method stub
		List<String> list = liveNessDao.queryAllYearDate(area);
		return list;
	}

	@Override
	public VipLiveness queryLiveNessByYear(String area, String year) {
		// TODO Auto-generated method stub
		VipLiveness liveness = liveNessDao.queryLiveNessByYear(area, year);
		return liveness;
	}

	@Override
	public List<VipLiveness> queryLivessByStation(String station) {
		// TODO Auto-generated method stub
		List<VipLiveness> list = liveNessDao.queryLivessByStation(station);
		return list;
	}

}
