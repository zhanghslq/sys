package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.ShopCartDao;
import com.yb.entity.InterPack;
import com.yb.service.ShopCartService;

@Service
public class ShopCartServiceImpl implements ShopCartService{

	@Autowired
	private ShopCartDao shopCartDao;
	
	@Override
	public List<InterPack> query(List<String> station, Date start, Date end,
			String oil) {
		// TODO Auto-generated method stub
		List<InterPack> list = shopCartDao.query(station, start, end, oil);
		return list;
	}

}
