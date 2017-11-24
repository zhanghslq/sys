package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Oil;
import com.yb.entity.Oilb;

public interface OilDao {
	List<Oil> queryOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query,@Param("people")String people);//燃油不分油品的数据
	//燃油分油品的销售量,修改为查询全部的
	List<Oilb> queryByOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);
	
	
	List<String> queryAllName();
	//各标号油价占比
	List<Oil> queryzhanbi(@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);
	
	//同比环比的查询
	Oil queryCompare(@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query,
			@Param("oilName")String oilName,@Param("people")String people);
}
