package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.HomePack;
import com.yb.entity.Price;

public interface HomePageService {
	public List<HomePack> queryList(Date start,Date end);
	
	public List<DataPack> queryOil();
	
	public List<DataPack> queryNotOil();
	//查询所有商品种类
	public List<String> queryAll();
	//指导油价
	public List<Price> queryPriceBei(Date start,Date end);
	
	public List<Price> queryCheng(Date start,Date end);
	
}
