package com.yb.dao;

import java.util.List;

import com.yb.entity.VipFunnel;

public interface VipFunnelDao {
	VipFunnel queryVipFunnel(String month);
	List<String> queryAllMonth();
}
