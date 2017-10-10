package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Recharge;

public interface RechargeService {
	public List<Recharge> query(String date,Date start,Date end);
}
