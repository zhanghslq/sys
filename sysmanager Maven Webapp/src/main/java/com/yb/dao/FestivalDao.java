package com.yb.dao;

import java.util.List;

import com.yb.entity.Festival;

public interface FestivalDao {
	public void add(Festival festival);
	public void delete(Integer id);
	public void update(Festival festival);
	public Festival queryByName(String name);
	public List<Festival> queryAll();
	public Festival queryById(Integer id);
}
