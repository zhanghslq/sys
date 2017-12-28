package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Credit;

public interface CreditDao {
	public List<Credit> queryCredit(@Param("date")String date,@Param("start")Date start,@Param("end")Date end,@Param("area")String area);
	public Credit queryZhanbi(@Param("area")String area);
}
