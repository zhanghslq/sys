package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;
import com.yb.entity.Mop;

public interface MopDao {
	List<String> queryAllMop();
	List<DataPack> queryMop(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	
	List<Mop> queryMopList(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("date")String date,@Param("people")String people);
	
	HHT queryHHT(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	
	//这是hht和ipt的支付方式占比
	List<DataPack> queryMophht(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	
	List<Mop> queryHHTList(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("date")String date,@Param("people")String people);
	
	List<DataPack> queryMopipt(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("people")String people);
	
	List<Mop> queryIPTList(@Param("start")Date start,@Param("end")Date end,
			@Param("station")List<String> station,@Param("date")String date,@Param("people")String people);
}
