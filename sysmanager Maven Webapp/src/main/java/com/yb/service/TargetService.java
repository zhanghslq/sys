package com.yb.service;

import java.util.List;

import com.yb.entity.DataPack;

public interface TargetService {
	public List<DataPack> queryTarget(String station);
}