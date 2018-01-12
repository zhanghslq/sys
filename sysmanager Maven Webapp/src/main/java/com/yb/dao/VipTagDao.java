package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.VipTag;

public interface VipTagDao {
	/*"loyalty":jqchk("loyalty"),"identity":jqchk("identity"),"gender":jqchk("gender"),
	"age":jqchk("age"),"type":jqchk("type"),"coupon":jqchk("coupon"),
	"recentOil":jqchk("recentOil"),"recentNotOil":jqchk("recentNotOil"),"shortOil":jqchk("shortOil"),*/
	public List<VipTag> query(@Param("loyalty")List<String> loyalty,@Param("identity")List<String> identity,@Param("gender")List<String> gender,
			@Param("age")List<String> age,@Param("type")List<String> type,@Param("coupon")List<String> coupon,
			@Param("recentOil")List<String> recentOil,@Param("recentNotOil")List<String> recentNotOil,@Param("shortOil")List<String> shortOil,
			@Param("station")List<String> station,@Param("oilName")List<String> oilName,@Param("shopName")List<String> shopName,
			@Param("mopType")List<String> mopType,
			@Param("start")Integer index,@Param("amount")Integer number,@Param("ids")List<String> ids);
	public Integer queryTotal(@Param("loyalty")List<String> loyalty,@Param("identity")List<String> identity,@Param("gender")List<String> gender,
			@Param("age")List<String> age,@Param("type")List<String> type,@Param("coupon")List<String> coupon,
			@Param("recentOil")List<String> recentOil,@Param("recentNotOil")List<String> recentNotOil,@Param("shortOil")List<String> shortOil,
			@Param("station")List<String> station,@Param("oilName")List<String> oilName,@Param("shopName")List<String> shopName,
			@Param("mopType")List<String> mopType,@Param("ids")List<String> ids);
	public List<String> queryAllMop();
	public List<String> queryAllOil();
	public List<String> queryAllShop();
}