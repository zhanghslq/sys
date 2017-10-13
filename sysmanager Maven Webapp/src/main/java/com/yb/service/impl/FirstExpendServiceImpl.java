package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.FirstExpendDao;
import com.yb.entity.FirstExpend;
import com.yb.service.FirstExpendService;

@Service
public class FirstExpendServiceImpl implements FirstExpendService{

	@Autowired
	private FirstExpendDao firstExpendDao; 
	@Override
	public List<FirstExpend> queryAllExpend() {
		// TODO Auto-generated method stub
		List<FirstExpend> list = firstExpendDao.queryAllExpend();
		return list;
	}

	@Override
	public List<FirstExpend> queryAllGap() {
		// TODO Auto-generated method stub
		List<FirstExpend> queryAllGap = firstExpendDao.queryAllGap();
		return queryAllGap;
	}

}
