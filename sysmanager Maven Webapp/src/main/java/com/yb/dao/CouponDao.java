package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Coupon;
import com.yb.entity.DataPack;


public interface CouponDao {
	public List<Coupon> query(@Param("start")Date start,@Param("end")Date end);
	public List<DataPack> queryZhanbi();
}
