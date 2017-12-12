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
	public List<VipTag> query(List<String> loyalty, List<String> identity,
			List<String> gender, List<String> age, List<String> type,
			List<String> coupon, List<String> recentOil,
			List<String> recentNotOil, List<String> shortOil, Integer index,
			Integer number) {
		// TODO Auto-generated method stub
		List<VipTag> list = vipTagDao.query(loyalty, identity, gender, age, type, coupon, recentOil, recentNotOil, shortOil, index, number);
		return list;
	}
	@Override
	public Integer queryTotal(List<String> loyalty, List<String> identity,
			List<String> gender, List<String> age, List<String> type,
			List<String> coupon, List<String> recentOil,
			List<String> recentNotOil, List<String> shortOil) {
		// TODO Auto-generated method stub
		Integer queryTotal = vipTagDao.queryTotal(loyalty, identity, gender, age, type, coupon, recentOil, recentNotOil, shortOil);
		return queryTotal;
	}

}
