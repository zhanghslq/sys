package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Coupon;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;

public interface CouponService {
	public List<Coupon> query(Date start,Date end,String date);
	public List<Couponb> queryByType(Date start,Date end,String date);
	public List<DataPack> queryZhanbi();
	
	List<Couponb> queryByStation(List<String> station,Date start,Date end,String date);

}
