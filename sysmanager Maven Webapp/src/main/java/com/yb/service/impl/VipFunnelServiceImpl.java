package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.VipFunnelDao;
import com.yb.entity.DouPack;
import com.yb.entity.VipFunnel;
import com.yb.service.VipFunnelService;

@Service
public class VipFunnelServiceImpl implements VipFunnelService{

	@Autowired
	private VipFunnelDao vipFunnelDao;
	@Override
	public VipFunnel queryVipFunnel(String month) {
		// TODO Auto-generated method stub
		VipFunnel funnel = vipFunnelDao.queryVipFunnel(month);
		return funnel;
	}

	@Override
	public List<String> queryAllMonth() {
		// TODO Auto-generated method stub
		List<String> list = vipFunnelDao.queryAllMonth();
		return list;
	}

	@Override
	public List<DouPack> queryDrain(String date,Date start,Date end) {
		// TODO Auto-generated method stub
		List<DouPack> list = vipFunnelDao.queryDrain(date,start,end);
		return list;
	}

}
