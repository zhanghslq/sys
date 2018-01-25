package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.RechargeDao;
import com.yb.entity.Recharge;
import com.yb.entity.Rechargeb;
import com.yb.service.RechargeService;

@Service

public class RechargeServiceImpl implements RechargeService{

	@Autowired
	private RechargeDao rechargeDao;
	@Override
	public List<Recharge> query(String date, Date start, Date end,String area) {
		// TODO Auto-generated method stub
		List<Recharge> list = rechargeDao.query(start, end, date,area);
		return list;
	}
	@Override
	public List<Rechargeb> queryByType(String date, Date start, Date end,
			String area) {
		// TODO Auto-generated method stub
		return rechargeDao.queryByType(start, end, date, area);
	}

}
