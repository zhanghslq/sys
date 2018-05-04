package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.ShopCartDao;
import com.yb.entity.InterPack;
import com.yb.entity.StringPack;
import com.yb.service.ShopCartService;

@Service
public class ShopCartServiceImpl implements ShopCartService{

	@Autowired
	private ShopCartDao shopCartDao;
	
	@Override
	public List<InterPack> query(List<String> station, Date start, Date end,
			String oil,String department) {
		// TODO Auto-generated method stub
		List<InterPack> list = shopCartDao.query(station, start, end, oil,department);
		return list;
	}

	@Override
	public List<StringPack> queryDepartment() {
		// TODO Auto-generated method stub
		List<StringPack> list = shopCartDao.queryDepartment();
		return list;
	}

	@Override
	public Integer getStatus() {
		// TODO Auto-generated method stub
		Integer integer = shopCartDao.getStatus();
		return integer;
	}
	@Transactional
	@Override
	public void updateStatus(Integer status) {
		// TODO Auto-generated method stub
		shopCartDao.updateStatus(status);
	}

	@Override
	public List<StringPack> queryProductName() {
		// TODO Auto-generated method stub
		List<StringPack> list = shopCartDao.queryProduct();
		return list;
	}

}
