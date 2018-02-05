package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Evaluationb;

public interface EvaluationbDao {
	List<Evaluationb> queryByDate(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station);
	List<Evaluationb> exportByDate(@Param("start")Date start,@Param("end")Date end,@Param("station")List<String> station);
}
