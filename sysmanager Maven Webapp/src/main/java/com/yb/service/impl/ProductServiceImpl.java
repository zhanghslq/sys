package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.ProductDao;
import com.yb.entity.DataPack;
import com.yb.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	@Override
	public List<DataPack> queryProduct(Date start, Date end, String station) {
		// TODO Auto-generated method stub
		List<DataPack> list = productDao.queryProduct(start, end, station);
		return list;
	}

}
