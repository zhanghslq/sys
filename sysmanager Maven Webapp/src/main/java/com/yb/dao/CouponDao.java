package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Coupon;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;


public interface CouponDao {
	public List<Coupon> query(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<Couponb> queryByType(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<DataPack> queryZhanbi();
}
