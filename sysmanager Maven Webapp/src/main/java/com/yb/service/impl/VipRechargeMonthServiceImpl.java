package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.VipRechargeMonthDao;
import com.yb.entity.VipRechargeMonth;
import com.yb.entity.VipRechargePack;
import com.yb.service.VipRechargeMonthService;

@Service
public class VipRechargeMonthServiceImpl implements VipRechargeMonthService{

	@Autowired
	private VipRechargeMonthDao vipRechargeMonthDao;
	@Override
	public List<VipRechargeMonth> query(Date start, Date end,String area) {
		// TODO Auto-generated method stub
		List<VipRechargeMonth> list = vipRechargeMonthDao.query(start, end,area);
		return list;
	}

	@Override
	public List<VipRechargePack> querySingle(Date start, Date end,String area) {
		// TODO Auto-generated method stub
		List<VipRechargePack> list = vipRechargeMonthDao.querySingle(start, end,area);
		return list;
	}

}
