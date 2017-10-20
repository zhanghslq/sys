package com.yb.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.VipChannelDao;
import com.yb.entity.Channel;
import com.yb.service.VipChannelService;

@Service
public class VipChannelServiceImpl implements VipChannelService{

	@Autowired
	private VipChannelDao vipChannelDao;
	@Override
	public Channel queryChannel(Date start, Date end) {
		// TODO Auto-generated method stub
		Channel channel = vipChannelDao.queryChannel(start, end);
		return channel;
	}

}
