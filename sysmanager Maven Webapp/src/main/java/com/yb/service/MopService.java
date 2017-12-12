package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;
import com.yb.entity.Mop;

public interface MopService {
	List<String> queryAllMop();
	List<DataPack> queryMop(Date start,Date end,List<String> station,String people);
	HHT queryHHT(Date start,Date end,List<String> station,String people);
	
	List<DataPack> queryMophht(Date start,Date end,List<String> station,String people);
	List<DataPack> queryMopipt(Date start,Date end,List<String> station,String people);
	
	
	List<Mop> queryMopList(Date start,Date end,
			List<String> station,String date,String people);
	List<Mop> queryHHTList(Date start,Date end,
			List<String> station,String date,String people);
	List<Mop> queryIPTList(Date start,Date end,
			List<String> station,String date,String people);
	
}
