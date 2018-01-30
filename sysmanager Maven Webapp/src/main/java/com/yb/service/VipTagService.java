package com.yb.service;

import java.util.List;

import com.yb.entity.VipTag;

public interface VipTagService {
	public List<VipTag> query(List<String> loyalty,List<String> identity,List<String> gender,
			List<String> age,List<String> type,List<String> coupon,
			List<String> recentOil,List<String> recentNotOil,List<String> shortOil,
			List<String> station,List<String> oilName,List<String> shopName,
			List<String> mopType,
			Integer index,Integer number,List<String> ids,List<String> manyStation);
	public Integer queryTotal(List<String> loyalty,List<String> identity,List<String> gender,
			List<String> age,List<String> type,List<String> coupon,
			List<String> recentOil,List<String> recentNotOil,List<String> shortOil,
			List<String> station,List<String> oilName,List<String> shopName,
			List<String> mopType,List<String> ids,List<String> manyStation);
	public List<String> queryAllMop();
	public List<String> queryAllOil();
	public List<String> queryAllShop();
}
