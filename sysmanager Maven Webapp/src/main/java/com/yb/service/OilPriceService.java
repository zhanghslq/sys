package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;

public interface OilPriceService {
	public List<String> queryAllName();//查询所有油品的名字
	
	public List<DataPack> queryPrice(Date start,Date end,
			String station,String oilName);
}
