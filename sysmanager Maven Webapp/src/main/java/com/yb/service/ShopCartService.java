package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.InterPack;

public interface ShopCartService {
	List<InterPack> query(List<String> station,Date start,Date end,
			String oil);
}
