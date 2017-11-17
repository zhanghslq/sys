package com.yb.service;

import java.util.List;

import com.yb.entity.DouPack;
import com.yb.entity.VipFunnel;

public interface VipFunnelService {
	VipFunnel queryVipFunnel(String month);
	List<String> queryAllMonth();
	List<DouPack> queryDrain();
}
