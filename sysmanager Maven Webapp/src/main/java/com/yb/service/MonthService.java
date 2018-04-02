package com.yb.service;

import java.util.List;

public interface MonthService {
	public List<String> queryAllYear();
	public List<String> queryByYear(String year);
	public void insert(String year,String month);
}
