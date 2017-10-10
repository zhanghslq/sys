package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.OilGunDao;
import com.yb.service.OilGunService;

@Service
public class OilGunServiceImpl implements OilGunService{
	@Autowired
	private OilGunDao oilGunDao;
	@Override
	public List<String> queryGun(String station) {
		// TODO Auto-generated method stub
		List<String> queryGun = oilGunDao.queryGun(station);
		return queryGun;
	}
}
