package com.yb.service;

import java.util.List;

import com.yb.entity.Admin;

public interface ShiroService {
	public List<String> queryPermission(String name);
	Admin queryByName(String name);
	void insertToken(String id,String name);
	String queryTokenById(String id);
}
