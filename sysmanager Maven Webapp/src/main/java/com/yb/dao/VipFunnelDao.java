package com.yb.dao;

import java.util.List;

import com.yb.entity.DouPack;
import com.yb.entity.VipFunnel;

public interface VipFunnelDao {
	//漏斗图
	VipFunnel queryVipFunnel(String month);
	List<String> queryAllMonth();
	
	
	//流失会员人数及占比
	List<DouPack> queryDrain();
}
