package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.NotOilTrade;

public interface NotOilTradeService {
	public List<NotOilTrade> query(String station,String date,Date start,Date end);
}
