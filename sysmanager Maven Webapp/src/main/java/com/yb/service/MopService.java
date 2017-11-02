package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;

public interface MopService {
	List<String> queryAllMop();
	List<DataPack> queryMop(Date start,Date end,String query,String station);
	HHT queryHHT(Date start,Date end,String query,String station);
}
