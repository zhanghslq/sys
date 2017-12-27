package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.VipChannelDao;
import com.yb.entity.Channel;
import com.yb.entity.DataPack;
import com.yb.service.VipChannelService;

@Service
public class VipChannelServiceImpl implements VipChannelService{

	@Autowired
	private VipChannelDao vipChannelDao;
	@Override
	public Channel queryChannel(Date start, Date end,String area) {
		// TODO Auto-generated method stub
		Channel channel = vipChannelDao.queryChannel(start, end,area);
		return channel;
	}
	@Override
	public List<DataPack> queryRate(Date start, Date end,String query,String area) {
		// TODO Auto-generated method stub
		 List<DataPack> list = vipChannelDao.queryRate(start, end,query,area);
		return list;
	}

}
