package com.yb.dao;

import java.util.List;

import com.yb.entity.DataPack;


public interface TargetDao {
	public List<DataPack> queryTarget(String station);
}
