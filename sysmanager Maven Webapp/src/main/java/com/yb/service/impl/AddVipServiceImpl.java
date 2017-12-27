package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.AddVipDao;
import com.yb.entity.AddVip;
import com.yb.service.AddVipService;

@Service
public class AddVipServiceImpl implements AddVipService{

	@Autowired
	private AddVipDao addVipDao;
	@Override
	public List<AddVip> query(String date, Date start, Date end,String area) {
		// TODO Auto-generated method stub
		List<AddVip> query = addVipDao.query(date, start, end,area);
		return query;
	}

}
