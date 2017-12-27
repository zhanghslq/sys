package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Channel;
import com.yb.entity.DataPack;

public interface VipChannelService {
	Channel queryChannel(Date start,Date end,String area);
	List<DataPack> queryRate(Date start,Date end,String query,String area);
}
