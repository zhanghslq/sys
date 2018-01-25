package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Recharge;
import com.yb.entity.Rechargeb;

public interface RechargeService {
	public List<Recharge> query(String date,Date start,Date end,String area);
	public List<Rechargeb> queryByType(String date,Date start,Date end,String area);
	
}
