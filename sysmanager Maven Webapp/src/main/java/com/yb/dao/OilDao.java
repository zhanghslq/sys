package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Oil;

public interface OilDao {
	List<Oil> queryOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);//燃油不分油品的数据
	//燃油分油品的销售量
	List<Oil> queryByOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query,@Param("oilName")String oilName);
	List<String> queryAllName();
	//各标号油价占比
	List<Oil> queryzhanbi(@Param("start")Date start,@Param("end")Date end,
			@Param("station")String station,@Param("query")String query);
}
