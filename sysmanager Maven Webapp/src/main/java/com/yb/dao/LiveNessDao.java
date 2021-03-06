package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.VipLiveness;
import com.yb.entity.VipTag;

public interface LiveNessDao {
	List<String> queryAllDate(@Param("area")String area);
	List<VipLiveness> queryData(@Param("area")String area);
	VipLiveness queryDataByDate(@Param("month")String month,@Param("area")String area);
	List<String> queryAllYearDate(@Param("area")String area);
	VipLiveness queryLiveNessByYear(@Param("area")String area,@Param("year")String year);
	List<VipLiveness> queryLiveNessYear(@Param("area")String area);
	List<VipLiveness> queryLivessByStation(@Param("station")String station);
	
	List<VipTag> exportData(@Param("area")String area,@Param("year")Integer year,@Param("start")Integer start,@Param("cou")Integer cou);
}
