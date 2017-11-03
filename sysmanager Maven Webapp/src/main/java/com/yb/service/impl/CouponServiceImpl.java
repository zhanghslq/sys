package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.CouponDao;
import com.yb.entity.Coupon;
import com.yb.entity.DataPack;
import com.yb.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponDao couponDao;
	@Override
	public List<Coupon> query(Date start, Date end) {
		// TODO Auto-generated method stub
		List<Coupon> list = couponDao.query(start, end);
		return list;
	}

	@Override
	public List<DataPack> queryZhanbi() {
		// TODO Auto-generated method stub
		List<DataPack> list = couponDao.queryZhanbi();
		return list;
	}

}
