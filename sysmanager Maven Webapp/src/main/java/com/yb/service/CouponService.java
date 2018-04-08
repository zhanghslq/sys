package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Coupon;
import com.yb.entity.CouponAll;
import com.yb.entity.CouponByType;
import com.yb.entity.CouponData;
import com.yb.entity.CouponNature;
import com.yb.entity.CouponOil;
import com.yb.entity.CouponSource;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;
import com.yb.entity.Tactics;

public interface CouponService {
	public List<Coupon> query(Date start,Date end,String date);
	public List<Couponb> queryByType(Date start,Date end,String date);
	public List<DataPack> queryZhanbi();
	
	List<Couponb> queryByStation(List<String> station,Date start,Date end,String date);
	List<Couponb> exportByStation(List<String> station,Date start,Date end,String date);

	
	List<CouponAll> queryCouponAlls(Date start,Date end,String date);
	List<CouponByType> queryCouponOil(Date start,Date end,String date);
	List<CouponByType> queryCouponShop(Date start,Date end,String date);
	
	List<CouponData> exportData(Date start,Date end,List<String> station,
			String tactics,String type,Integer st,Integer ed);
	List<CouponData> exportData2(Date start,Date end,List<String> station,
			String tactics,List<String>type,Integer st,Integer ed);
	List<CouponData> exportDataByName(Date start,Date end,Integer st,Integer ed);
	List<CouponAll> queryScore(Date start,Date end,String date);
	
	List<DataPack> queryLadder(Date start,Date end,String date,List<String> station);
	List<DataPack> exportLadder(Date start,Date end,String date,List<String> station);
	
	List<Tactics> queryTactics(Date start,Date end);
	
	/**
	 * 合成
	 */
	List<CouponSource> querySource(Integer city,List<Integer> type,Date start,Date end,String date);
	List<CouponNature> queryNature(Integer city,String type,String coupon,Date start,Date end,String date);
	List<CouponOil> queryOil(Integer city,String type,String coupon,Date start,Date end,String date);
	List<CouponSource> queryDisSource(Integer city,Date start,Date end,String date);
}
