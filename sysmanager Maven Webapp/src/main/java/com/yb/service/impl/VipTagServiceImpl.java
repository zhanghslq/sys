package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.VipTagDao;
import com.yb.entity.VipTag;
import com.yb.service.VipTagService;

@Service
public class VipTagServiceImpl implements VipTagService{

	@Autowired
	private VipTagDao vipTagDao;

	
	@Override
	public List<String> queryAllMop() {
		// TODO Auto-generated method stub
		List<String> list = vipTagDao.queryAllMop();
		return list;
	}
	@Override
	public List<String> queryAllOil() {
		// TODO Auto-generated method stub
		List<String> list = vipTagDao.queryAllOil();
		return list;
	}
	@Override
	public List<String> queryAllShop() {
		// TODO Auto-generated method stub
		List<String> list = vipTagDao.queryAllShop();
		return list;
	}
	@Override
	public List<VipTag> query(List<String> loyalty, List<String> identity,
			List<String> gender, List<String> age, List<String> type,
			List<String> coupon, List<String> recentOil,
			List<String> recentNotOil, List<String> shortOil,
			List<String> station, List<String> oilName, List<String> shopName,
			List<String> mopType, Integer index, Integer number) {
		// TODO Auto-generated method stub
		List<VipTag> query = vipTagDao.query(loyalty, identity, gender, age, type, coupon, recentOil, recentNotOil, shortOil, station, oilName, shopName, mopType, index, number);
		return query;
	}
	@Override
	public Integer queryTotal(List<String> loyalty, List<String> identity,
			List<String> gender, List<String> age, List<String> type,
			List<String> coupon, List<String> recentOil,
			List<String> recentNotOil, List<String> shortOil,
			List<String> station, List<String> oilName, List<String> shopName,
			List<String> mopType) {
		// TODO Auto-generated method stub
		Integer queryTotal = vipTagDao.queryTotal(loyalty, identity, gender, age, type, coupon, recentOil, recentNotOil, shortOil, station, oilName, shopName, mopType);
		return queryTotal;
	}

}
