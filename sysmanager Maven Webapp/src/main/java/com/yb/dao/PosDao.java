package com.yb.dao;

import java.util.List;

import com.yb.entity.Pos;

public interface PosDao {
	List<Pos> queryAll();
	Pos queryById(Integer id);
	void delete(Integer id);
	void add(String name);
	void update(Pos pos);
}
