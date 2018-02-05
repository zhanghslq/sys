package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Oil;
import com.yb.entity.OilAndVip;
import com.yb.entity.Oilb;

public interface OilDao {
	List<Oil> queryOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);//燃油不分油品的数据
	//燃油分油品的销售量,修改为查询全部的
	List<Oilb> queryByOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	List<Oilb> exportByOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	
	
	List<String> queryAllName();
	//各标号油价占比
	List<Oil> queryzhanbi(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	
	//同比环比的查询
	Oil queryCompare(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String>station,
			@Param("oilName")String oilName,@Param("people")String people);
	List<Oil> exportCompare(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String>station,
			@Param("oilName")String oilName,@Param("people")String people);
	
	
	List<OilAndVip> queryAllAndVip(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	List<OilAndVip> queryAllAndVipByOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("oils")String oils);
	List<OilAndVip> exportAllAndVipByOils(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("oils")String oils);
	
	Oil queryOilsByType(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String>station,
			@Param("oilNames")List<String> oilNames,@Param("people")String people);
	List<OilAndVip> exportAllAndVip(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	
}
