package com.yb.service;

import java.util.List;

import com.yb.entity.Basket1;
import com.yb.entity.BasketNumber;
import com.yb.entity.BasketRules;

public interface BasketService {
	List<BasketNumber> queryNumber();
	List<Basket1> queryOne();
	List<BasketRules> queryRule();
}
