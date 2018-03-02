package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Coupon;
import com.yb.entity.CouponAll;
import com.yb.entity.CouponByType;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;


public interface CouponDao {
	public List<Coupon> query(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<Couponb> queryByType(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<DataPack> queryZhanbi();
	List<Couponb> queryByStation(@Param("station")List<String> station,@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	List<Couponb> exportByStation(@Param("station")List<String> station,@Param("start")Date start,@Param("end")Date end,@Param("date")String date);	
	
	List<CouponAll> queryCouponAlls(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	List<CouponByType> queryCouponOil(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	List<CouponByType> queryCouponShop(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
}
