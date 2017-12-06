package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;

public interface MopDao {
	List<String> queryAllMop();
	List<DataPack> queryMop(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	
	HHT queryHHT(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	
	//这是hht和ipt的支付方式占比
	List<DataPack> queryMophht(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
	
	List<DataPack> queryMopipt(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station);
}
