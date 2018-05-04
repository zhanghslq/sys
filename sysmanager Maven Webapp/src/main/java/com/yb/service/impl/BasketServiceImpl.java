package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.BasketDao;
import com.yb.entity.Basket1;
import com.yb.entity.BasketNumber;
import com.yb.entity.BasketRules;
import com.yb.service.BasketService;

@Service
public class BasketServiceImpl implements BasketService{

	@Autowired
	private BasketDao basketDao;
	@Override
	public List<BasketNumber> queryNumber() {
		// TODO Auto-generated method stub
		List<BasketNumber> list = basketDao.queryNumber();
		return list;
	}

	@Override
	public List<Basket1> queryOne() {
		// TODO Auto-generated method stub
		List<Basket1> list = basketDao.queryOne();
		return list;
	}

	@Override
	public List<BasketRules> queryRule() {
		// TODO Auto-generated method stub
		List<BasketRules> list = basketDao.queryRule();
		return list;
	}

}
