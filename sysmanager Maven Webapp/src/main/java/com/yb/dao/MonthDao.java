package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MonthDao {
	public List<String> queryAllYear();
	public List<String> queryByYear(@Param("year")String year);
	public void insert(@Param("year")String year,@Param("month")String month);
}
