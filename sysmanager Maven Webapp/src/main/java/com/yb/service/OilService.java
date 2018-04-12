package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Oil;
import com.yb.entity.OilAndVip;
import com.yb.entity.OilData;
import com.yb.entity.Oilb;


public interface OilService {
	List<Oil> queryOils(String date,Date start,Date end,List<String> station,String people);//燃油不分油品的数据
	List<Oilb> queryByOils(String date,Date start,Date end,List<String> station,List<Integer> week);//燃油分油品的数据
	List<Oilb> exportByOils(String date,Date start,Date end,List<String> station,List<Integer> week);//燃油分油品导出的数据
	
	List<OilData> exportByOilData(String date,Date start,Date end,List<String> station,List<Integer> week);//燃油分油品导出的数据
	List<OilData> queryByOilData(String date,Date start,Date end,List<String> station,List<Integer> week);//燃油分油品导出的数据
	
	List<String> queryAllName();
	List<Oil> queryzhanbi(Date start,Date end,List<String> station);//油品占比
	Oil queryCompare(Date start,Date end,List<String> station,String oilName,String people);//同比环比
	List<Oil> exportCompare(Date start,Date end,List<String> station,String oilName,String people);//同比环比导出
	Oil queryOilsByType(Date start,Date end,List<String> station,List<String> oilNames,String people);//根据柴油汽油分类进行查询
	List<OilAndVip>  queryAllAndVip(String date,Date start,Date end,List<String> station,List<Integer> week);
	List<OilAndVip>  queryAllAndVipByOils(String date,Date start,Date end,List<String> station,String oils,List<Integer> week);
	List<OilAndVip>  exportAllAndVipByOils(String date,Date start,Date end,List<String> station,String oils,List<Integer> week);
	List<OilAndVip>  exportAllAndVip(String date,Date start,Date end,List<String> station,List<Integer> weIntegers);
	
}