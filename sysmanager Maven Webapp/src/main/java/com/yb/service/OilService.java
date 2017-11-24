package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Oil;
import com.yb.entity.Oilb;


public interface OilService {
	List<Oil> queryOils(String date,Date start,Date end,String station,String query,String people);//燃油不分油品的数据
	
	List<Oilb> queryByOils(String date,Date start,Date end,String station,String query);//燃油分油品的数据
	List<String> queryAllName();
	List<Oil> queryzhanbi(Date start,Date end,String station,String query);//油品占比
	
	Oil queryCompare(Date start,Date end,String station,String query,String oilName,String people);//同比环比
}