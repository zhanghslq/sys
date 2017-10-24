package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.LiveNessDao;
import com.yb.entity.VipLiveness;
import com.yb.service.LiveNessService;

@Service
public class LiveNessServiceImpl implements LiveNessService{

	@Autowired
	private LiveNessDao liveNessDao;
	@Override
	public List<String> queryAllDate() {
		// TODO Auto-generated method stub
		List<String> list = liveNessDao.queryAllDate();
		return list;
	}

	@Override
	public VipLiveness queryData(String date) {
		// TODO Auto-generated method stub
		VipLiveness list = liveNessDao.queryData(date);
		return list;
	}

}
