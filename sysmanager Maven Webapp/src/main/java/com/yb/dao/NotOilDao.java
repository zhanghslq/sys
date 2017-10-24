package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.NotOil;

public interface NotOilDao {
	List<NotOil> queryNotOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);//不分品类的销售
	List<NotOil> queryByDepartmentName(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query,@Param("departmentName")String departmentName);//分品类
	List<String> queryAllName();
	
	//便利店的开单率
	List<NotOil> queryRate(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);
	
	//非油商品的Top榜
	List<DataPack> queryTop(@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);
}
