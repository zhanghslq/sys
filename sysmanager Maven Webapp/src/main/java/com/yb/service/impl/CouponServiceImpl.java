package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.CouponDao;
import com.yb.entity.Coupon;
import com.yb.entity.CouponAll;
import com.yb.entity.CouponByType;
import com.yb.entity.CouponData;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;
import com.yb.entity.Tactics;
import com.yb.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponDao couponDao;
	@Override
	public List<Coupon> query(Date start, Date end,String date) {
		// TODO Auto-generated method stub
		List<Coupon> list = couponDao.query(start, end,date);
		return list;
	}

	@Override
	public List<DataPack> queryZhanbi() {
		// TODO Auto-generated method stub
		List<DataPack> list = couponDao.queryZhanbi();
		return list;
	}

	@Override
	public List<Couponb> queryByType(Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<Couponb> list = couponDao.queryByType(start, end, date);
		return list;
	}

	@Override
	public List<Couponb> queryByStation(List<String> station, Date start,
			Date end, String date) {
		// TODO Auto-generated method stub
		List<Couponb> list = couponDao.queryByStation(station, start, end, date);
		return list;
	}

	@Override
	public List<Couponb> exportByStation(List<String> station, Date start,
			Date end, String date) {
		// TODO Auto-generated method stub
		List<Couponb> list = couponDao.exportByStation(station, start, end, date);
		return list;
	}

	@Override
	public List<CouponAll> queryCouponAlls(Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<CouponAll> list = couponDao.queryCouponAlls(start, end, date);
		return list;
	}

	@Override
	public List<CouponByType> queryCouponOil(Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<CouponByType> list = couponDao.queryCouponOil(start, end, date);
		return list;
	}

	@Override
	public List<CouponByType> queryCouponShop(Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<CouponByType> list = couponDao.queryCouponShop(start, end, date);
		return list;
	}

	@Override
	public List<CouponData> exportData(Date start, Date end, List<String> station,
			String tactics, String type,Integer st,Integer ed) {
		// TODO Auto-generated method stub
		List<CouponData> list = couponDao.exportData(start, end, station, tactics, type,st,ed);
		return list;
	}

	@Override
	public List<CouponAll> queryScore(Date start, Date end, String date) {
		// TODO Auto-generated method stub
		List<CouponAll> list = couponDao.queryScore(start, end, date);
		return list;
	}

	@Override
	public List<DataPack> queryLadder(Date start, Date end, String date,
			List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = couponDao.queryLadder(start, end, date, station);
		return list;
	}

	@Override
	public List<DataPack> exportLadder(Date start, Date end, String date,
			List<String> station) {
		// TODO Auto-generated method stub
		List<DataPack> list = couponDao.exportLadder(start, end, date, station);
		return list;
	}

	@Override
	public List<Tactics> queryTactics(Date start, Date end) {
		// TODO Auto-generated method stub
		List<Tactics> list = couponDao.queryTactics(start, end);
		return list;
	}

	@Override
	public List<CouponData> exportDataByName(Date start, Date end,Integer st,Integer ed) {
		// TODO Auto-generated method stub
		List<CouponData> list = couponDao.exportDataByName(start, end,st,ed);
		return list;
	}

}
