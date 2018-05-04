package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.InterPack;
import com.yb.entity.StringPack;

public interface ShopCartService {
	List<InterPack> query(List<String> station,Date start,Date end,
			String oil,String department);
	List<StringPack> queryProductName();
	List<StringPack> queryDepartment();
	Integer getStatus();
	void updateStatus(Integer status);
}
