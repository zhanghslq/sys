package com.yb.dao;

import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.HomePack;
import com.yb.entity.Price;

public interface HomePageDao {
	//降水量和油品销售量，非油销售额的对比
	public List<HomePack> queryList();
	//各种商品占比,不需要时间
	public List<DataPack> queryOil();
	public List<DataPack> queryNotOil();
	//查询所有商品种类
	public List<String> queryAll();
	//指导油价
	public List<Price> queryPriceBei();
	
	public List<Price> queryCheng();
}
