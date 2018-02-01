package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DouPack;
import com.yb.entity.VipFunnel;

public interface VipFunnelService {
	VipFunnel queryVipFunnel(String month,String area);
	List<VipFunnel> queryAllVipFunnel(String area);
	List<String> queryAllMonth(String area);
	List<DouPack> queryDrain(String date,Date start,Date end,String area);
}
