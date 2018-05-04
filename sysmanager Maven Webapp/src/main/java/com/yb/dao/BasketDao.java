package com.yb.dao;

import java.util.List;

import com.yb.entity.Basket1;
import com.yb.entity.BasketNumber;
import com.yb.entity.BasketRules;

public interface BasketDao {
	List<BasketNumber> queryNumber();
	List<Basket1> queryOne();
	List<BasketRules> queryRule();
}
