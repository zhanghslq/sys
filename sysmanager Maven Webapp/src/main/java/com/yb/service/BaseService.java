package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.AmountPack;
import com.yb.entity.TradeAmount;

public interface BaseService {
	public List<AmountPack> zoushi(String date,String station,String oilName,Date start,Date end);
	public List<TradeAmount> zhanbi(String station,Date start);
}
