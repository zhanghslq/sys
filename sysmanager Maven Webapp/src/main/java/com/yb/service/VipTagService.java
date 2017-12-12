package com.yb.service;

import java.util.List;

import com.yb.entity.VipTag;

public interface VipTagService {
	public List<VipTag> query(List<String> loyalty,List<String> identity,List<String> gender,
			List<String> age,List<String> type,List<String> coupon,
			List<String> recentOil,List<String> recentNotOil,List<String> shortOil,
			Integer index,Integer number);
	public Double queryTotal(List<String> loyalty,List<String> identity,List<String> gender,
			List<String> age,List<String> type,List<String> coupon,
			List<String> recentOil,List<String> recentNotOil,List<String> shortOil);
}
