package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.FirstExpendDao;
import com.yb.entity.FirstExpend;
import com.yb.entity.VipRechargePack;
import com.yb.service.FirstExpendService;

@Service
public class FirstExpendServiceImpl implements FirstExpendService{

	@Autowired
	private FirstExpendDao firstExpendDao; 
	@Override
	public List<FirstExpend> queryAllExpend(String area) {
		// TODO Auto-generated method stub
		List<FirstExpend> list = firstExpendDao.queryAllExpend(area);
		return list;
	}

	@Override
	public List<FirstExpend> queryAllGap(String area) {
		// TODO Auto-generated method stub
		List<FirstExpend> queryAllGap = firstExpendDao.queryAllGap(area);
		return queryAllGap;
	}

	@Override
	public List<FirstExpend> queryLastDeal(String area) {
		// TODO Auto-generated method stub
		List<FirstExpend> list = firstExpendDao.queryLastDeal(area);
		return list;
	}

	@Override
	public List<VipRechargePack> queryDealMonth(Date start, Date end,String area) {
		// TODO Auto-generated method stub
		List<VipRechargePack> list = firstExpendDao.queryDealMonth(start, end,area);
		return list;
	}

}
