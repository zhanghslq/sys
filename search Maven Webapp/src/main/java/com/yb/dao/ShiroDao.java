package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Admin;

public interface ShiroDao {
	public List<String> queryPermission(String name);//需要查询是否授权
	Admin queryByName(String name);
	void insertToken(@Param("id")String id,@Param("name")String name);
	String queryTokenById(@Param("id")String id);
}