package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.NotOil;

public interface NotOilService {
	List<NotOil> queryNotOils(String date,Date start,Date end,String station,String query,String people);
	List<NotOil> queryByDepartmentName(String date,Date start,Date end,String station,String query,String departmentName);//燃油分油品的数据
	List<String> queryAllName();
	
	List<NotOil> queryRate(String date,Date start,Date end,String station,String query,String people);
	//非油商品的Top榜
	List<DataPack> queryTop(Date start,Date end,String station,String query,String people);
	
	
	//增长率的查询
	NotOil queryByCompare(Date start,Date end,String station,String query,String departmentName,String people);//分品类
	
	Double queryRateCompare(Date start,Date end,String station,String query);
}
