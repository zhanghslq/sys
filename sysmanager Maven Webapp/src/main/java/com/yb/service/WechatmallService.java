package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.WechatmallStatus;



public interface WechatmallService {
	public List<WechatmallStatus> queryByStationAndTime(List<String> station,
			Date start,Date end,String date);
	public List<WechatmallStatus> exportByStationAndTime(List<String> station,
			Date start,Date end,String date);
	public List<DataPack> queryTop(Date start,Date end,List<String> station);
	public List<String> queryAllStation();
}
