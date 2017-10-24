package com.yb.service;

import java.util.List;

import com.yb.entity.Pos;

public interface PosService {
	List<Pos> queryAll();
	Pos queryById(Integer id);
	void delete(Integer id);
	void add(String name);
	void update(Pos pos);
}
