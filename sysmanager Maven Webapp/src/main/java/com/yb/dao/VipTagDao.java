package com.yb.dao;

import java.util.List;

import com.yb.entity.RefreshData;
import org.apache.ibatis.annotations.Param;

import com.yb.entity.Description;
import com.yb.entity.VipTag;

public interface VipTagDao {
	public List<VipTag> query(@Param("loyalty")List<String> loyalty,@Param("identity")List<String> identity,@Param("gender")List<String> gender,
			@Param("age")List<String> age,@Param("type")List<String> type,@Param("coupon")List<String> coupon,
			@Param("recentOil")List<String> recentOil,@Param("recentNotOil")List<String> recentNotOil,@Param("shortOil")List<String> shortOil,
			@Param("station")List<String> station,@Param("oilName")List<String> oilName,@Param("shopName")List<String> shopName,
			@Param("mopType")List<String> mopType,
			@Param("start")Integer index,@Param("amount")Integer number,@Param("ids")List<String> ids,@Param("manyStation")List<String> manyStation,@Param("area")String area);
	public Integer queryTotal(@Param("loyalty")List<String> loyalty,@Param("identity")List<String> identity,@Param("gender")List<String> gender,
			@Param("age")List<String> age,@Param("type")List<String> type,@Param("coupon")List<String> coupon,
			@Param("recentOil")List<String> recentOil,@Param("recentNotOil")List<String> recentNotOil,@Param("shortOil")List<String> shortOil,
			@Param("station")List<String> station,@Param("oilName")List<String> oilName,@Param("shopName")List<String> shopName,
			@Param("mopType")List<String> mopType,@Param("ids")List<String> ids,@Param("manyStation")List<String> manyStation,@Param("area")String area);
	public List<String> queryAllMop();
	public List<String> queryAllOil();

	/**
	 * 查询
	 * @return 返回字符串
	 */
	public List<String> queryAllShop();
	List<Description> queryAllDescription();
	List<RefreshData> queryAllRefreshData();
	Integer queryVipTototal(@Param("date")String date,@Param("station")List<String> station,@Param("oilName")List<String> oilName,@Param("shopName")List<String> shopName,@Param("area")String area,@Param("oilNumber")List<Integer> oilNumber);
	List<VipTag> queryVip(@Param("date")String date,@Param("station")List<String> station,@Param("oilName")List<String> oilName,@Param("shopName")List<String> shopName,
			@Param("start")Integer start,@Param("size")Integer size,@Param("area")String area,@Param("oilNumber")List<Integer> oilNumber);
	
}