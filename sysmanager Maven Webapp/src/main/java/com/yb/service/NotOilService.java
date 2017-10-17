package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.NotOil;

public interface NotOilService {
	List<NotOil> queryNotOils(String date,Date start,Date end,String station,String query);
	List<NotOil> queryByDepartmentName(String date,Date start,Date end,String station,String query,String departmentName);//燃油分油品的数据
	List<String> queryAllName();
	
	List<NotOil> queryRate(String date,Date start,Date end,String station,String query);
	
}
