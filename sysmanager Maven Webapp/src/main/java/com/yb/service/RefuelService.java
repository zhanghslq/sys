package com.yb.service;

import java.util.Date;

import com.yb.entity.Refuel;

public interface RefuelService {
	public Refuel query(Date start,Date end,String station);
}
