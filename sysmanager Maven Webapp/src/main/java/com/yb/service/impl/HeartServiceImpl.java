package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.HeartDao;
import com.yb.entity.Heart;
import com.yb.service.HeartService;

@Service
public class HeartServiceImpl implements HeartService{
	@Autowired
	private HeartDao heartDao;
	@Override
	public List<Heart> queryAll() {
		// TODO Auto-generated method stub
		List<Heart> list = heartDao.queryAll();
		return list;
	}
}
