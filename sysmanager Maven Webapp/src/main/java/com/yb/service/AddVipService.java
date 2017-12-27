package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.AddVip;


public interface AddVipService{
	public List<AddVip> query(String date,Date start,Date end,String area);
}
