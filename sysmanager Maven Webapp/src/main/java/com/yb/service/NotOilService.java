package com.yb.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.NotOil;

public interface NotOilService {
	List<NotOil> queryNotOils(String date,Date start,Date end,String station,String query);
	List<NotOil> queryByDepartmentName(String date,Date start,Date end,String station,String query,String departmentName);//燃油分油品的数据
	List<String> queryAllName();
	
	List<NotOil> queryRate(String date,Date start,Date end,String station,String query);
	//非油商品的Top榜
	List<DataPack> queryTop(Date start,Date end,String station,String query);
	
	
	//增长率的查询
		NotOil queryByCompare(Date start,Date end,String station,String query,String departmentName);//分品类
}
