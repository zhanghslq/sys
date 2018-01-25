package com.yb.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.Department;
import com.yb.entity.ExceptLube;
import com.yb.entity.NotOil;
import com.yb.entity.NotOilAndVip;

public interface NotOilService {
	List<NotOil> queryNotOils(String date,Date start,Date end,List<String> station,String people);
	List<NotOilAndVip> queryAllAndVip(String date,Date start,Date end,List<String> station);
	List<NotOil> queryByDepartmentName(String date,Date start,Date end,List<String> station,String departmentName,String people);//燃油分油品的数据
	List<Department> queryAllDepartments(String date,Date start,Date end,List<String> station,String people);//燃油分油品的数据
	List<String> queryAllName();
	
	List<NotOil> queryRate(String date,Date start,Date end,List<String> station,String people);
	//非油商品的Top榜
	List<DataPack> queryTop(Date start,Date end,List<String> station,String people);
	
	List<ExceptLube> queryExceptLube(String date,Date start,Date end,List<String> station);
	
	//增长率的查询
	NotOil queryByCompare(Date start,Date end,List<String> station,String departmentName,String people);//分品类
	
	Double queryRateCompare(Date start,Date end,String station,String query);
		
	//根据商品编码查询销售额
	List<DataPack> querySearch(Date start,Date end,List<String>station,String date,String productCode);
	//千升比
	List<DataPack> queryThousandRate(String date,Date start,Date end,List<String> station,String people);
}