package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Evaluation;



public interface EvaluationDao {
	//走势，带日期的，五个评价项的平均值，按照时间来走势
	List<Evaluation> queryTrend(@Param("date")String date,@Param("start")Date start,
			@Param("end")Date end,@Param("station")List<String> station,@Param("area")String area);
	//分布，是五个柱形图，把一段时间内的数据，相同类的评价取平均值
	Evaluation queryDistribution(@Param("start")Date start,
			@Param("end")Date end,@Param("station")List<String> station,@Param("area")String area);

	//再求一个射线图，是一个时间段,然后五个值都有，是一个数组
	List<Evaluation> queryEvaluations(@Param("start")Date start,
			@Param("end")Date end,@Param("station")List<String> station,@Param("area")String area);
}
