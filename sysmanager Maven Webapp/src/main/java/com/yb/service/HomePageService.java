package com.yb.service;

import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.HomePack;
import com.yb.entity.Price;

public interface HomePageService {
	public List<HomePack> queryList();
	
	public List<DataPack> queryOil();
	
	public List<DataPack> queryNotOil();
	//查询所有商品种类
	public List<String> queryAll();
	//指导油价
	public List<Price> queryPriceBei();
	
	public List<Price> queryCheng();
	
}
