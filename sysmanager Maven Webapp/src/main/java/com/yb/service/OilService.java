package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Oil;
import com.yb.entity.OilAndVip;
import com.yb.entity.Oilb;


public interface OilService {
	List<Oil> queryOils(String date,Date start,Date end,List<String> station,String people);//燃油不分油品的数据
	
	List<Oilb> queryByOils(String date,Date start,Date end,List<String> station);//燃油分油品的数据
	List<String> queryAllName();
	List<Oil> queryzhanbi(Date start,Date end,List<String> station);//油品占比
	Oil queryCompare(Date start,Date end,List<String> station,String oilName,String people);//同比环比
	
	List<OilAndVip>  queryAllAndVip(String date,Date start,Date end,List<String> station);
}