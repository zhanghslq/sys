package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.WechatmallDao;
import com.yb.entity.DataPack;
import com.yb.entity.WechatmallStatus;
import com.yb.service.WechatmallService;

@Service
public class WechatmallServiceImpl implements WechatmallService{

	@Autowired
	private WechatmallDao wechatmallDao;
	@Override
	public List<WechatmallStatus> queryByStationAndTime(List<String> station,
			Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<WechatmallStatus> list = wechatmallDao.queryByStationAndTime(station, start, end, date);
		return list;
	}

	@Override
	public List<DataPack> queryTop(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = wechatmallDao.queryTop(start, end, station);
		return list;
	}

	@Override
	public List<String> queryAllStation() {
		// TODO Auto-generated method stub
		List<String> list = wechatmallDao.queryAllStation();
		return list;
	}

	@Override
	public List<WechatmallStatus> exportByStationAndTime(List<String> station,
			Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<WechatmallStatus> list = wechatmallDao.exportByStationAndTime(station, start, end, date);
		return list;
	}

	@Override
	public List<DataPack> queryTopAll(Date start, Date end) {
		// TODO Auto-generated method stub
		List<DataPack> list = wechatmallDao.queryTopAll(start, end);
		return list;
	}

	@Override
	public List<DataPack> exportTop(Date start, Date end, List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = wechatmallDao.exportTop(start, end, station);
		return list;
	}

}
