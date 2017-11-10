package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.HomePageDao;
import com.yb.entity.DataPack;
import com.yb.entity.HomePack;
import com.yb.entity.Price;
import com.yb.service.HomePageService;

@Service
public class HomePageServiceImpl implements HomePageService{

	@Autowired
	private HomePageDao homePageDao;
	
	@Override
	public List<HomePack> queryList() {
		// TODO Auto-generated method stub
		List<HomePack> list = homePageDao.queryList();
		
		return list;
	}

	@Override
	public List<DataPack> queryOil() {
		// TODO Auto-generated method stub
		List<DataPack> queryOil = homePageDao.queryOil();
		
		return queryOil;
	}

	@Override
	public List<DataPack> queryNotOil() {
		// TODO Auto-generated method stub
		List<DataPack> list = homePageDao.queryNotOil();
		return list;
	}

	@Override
	public List<String> queryAll() {
		// TODO Auto-generated method stub
		List<String> list = homePageDao.queryAll();
		return list;
	}

	@Override
	public List<Price> queryPriceBei() {
		// TODO Auto-generated method stub
		List<Price> list = homePageDao.queryPriceBei();
		return list;
	}

	@Override
	public List<Price> queryCheng() {
		// TODO Auto-generated method stub
		List<Price> list = homePageDao.queryCheng();
		return list;
	}

}
