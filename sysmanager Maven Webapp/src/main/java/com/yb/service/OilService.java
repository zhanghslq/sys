package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Oil;


public interface OilService {
	List<Oil> queryOils(String date,Date start,Date end,String station,String query);//燃油不分油品的数据
	List<Oil> queryByOils(String date,Date start,Date end,String station,String query,String oilName);//燃油分油品的数据
	List<String> queryAllName();
}