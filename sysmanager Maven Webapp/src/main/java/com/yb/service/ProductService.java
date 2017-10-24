package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;

public interface ProductService {
	List<DataPack> queryProduct(Date start,Date end,String station);
}
