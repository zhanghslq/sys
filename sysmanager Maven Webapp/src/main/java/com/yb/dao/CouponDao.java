package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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


public interface CouponDao {
	public List<Coupon> query(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<Couponb> queryByType(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	public List<DataPack> queryZhanbi();
	List<Couponb> queryByStation(@Param("station")List<String> station,@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	List<Couponb> exportByStation(@Param("station")List<String> station,@Param("start")Date start,@Param("end")Date end,@Param("date")String date);	
	
	List<CouponAll> queryCouponAlls(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	List<CouponByType> queryCouponOil(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	List<CouponByType> queryCouponShop(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	
	List<CouponData> exportData(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station,
			@Param("tactics")String tactics,@Param("type")String type,@Param("st")Integer st,@Param("ct")Integer ct);
	
	List<CouponData> exportDataByName(@Param("start")Date start,@Param("end")Date end,@Param("st")Integer st,@Param("ct")Integer ct);
	
	
	List<CouponAll> queryScore(@Param("start")Date start,@Param("end")Date end,@Param("date")String date);
	
	List<DataPack> queryLadder(@Param("start")Date start,@Param("end")Date end,@Param("date")String date,@Param("station")List<String> station);
	List<DataPack> exportLadder(@Param("start")Date start,@Param("end")Date end,@Param("date")String date,@Param("station")List<String> station);
	
	List<Tactics> queryTactics(@Param("start")Date start,@Param("end")Date end);
	//四个分类的合成三个维度的
	List<CouponSource> querySource(Integer city);
	List<CouponNature> queryNature(Integer city);
	List<CouponOil> queryOil(Integer city);
}
