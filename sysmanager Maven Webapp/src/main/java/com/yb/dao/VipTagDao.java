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
			@Param("index")Integer index,@Param("number")Integer number);
	public Integer queryTotal(@Param("loyalty")List<String> loyalty,@Param("identity")List<String> identity,@Param("gender")List<String> gender,
			@Param("age")List<String> age,@Param("type")List<String> type,@Param("coupon")List<String> coupon,
			@Param("recentOil")List<String> recentOil,@Param("recentNotOil")List<String> recentNotOil,@Param("shortOil")List<String> shortOil);
}