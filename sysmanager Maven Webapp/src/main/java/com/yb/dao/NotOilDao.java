package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.NotOil;

public interface NotOilDao {
	List<NotOil> queryNotOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);//不分品类的销售
	
	List<NotOil> queryByDepartmentName(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("departmentName")String departmentName,@Param("people")String people);//分品类
	List<String> queryAllName();
	
	//便利店的开单率
	List<NotOil> queryRate(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	
	//非油商品的Top榜
	List<DataPack> queryTop(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	
	
	//增长率的查询
	NotOil queryByCompare(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,
			@Param("departmentName")String departmentName,@Param("people")String people);//分品类
	
	//便利店的开单率的对比
	Double queryRateCompare(@Param("start")Date start,@Param("end")Date end,
				@Param("station")String station,@Param("query")String query);
	
	List<DataPack> queryExceptLube(@Param("date")String date, @Param("start")Date start, @Param("end")Date end,
			@Param("station")String station, @Param("query")String query,@Param("people")String people);
	//根据商品编码查询商品在一段时间的销售额额
	List<DataPack> querySearch(@Param("date")String date, @Param("start")Date start, @Param("end")Date end,
			@Param("station")List<String> station,@Param("productCode")String productCode);
}
