package com.yb.service;

import java.util.Date;

import com.yb.entity.Channel;

public interface VipChannelService {
	Channel queryChannel(Date start,Date end);
}
