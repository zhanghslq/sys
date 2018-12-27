package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Admin;

public interface ShiroDao {

	public List<String> queryPermission(@Param("name") String name);//需要查询是否授权
	//根据名称查询用户
	Admin queryByName(String name);
	//插入token
	void insertToken(@Param("id")String id,@Param("name")String name);
	//根据id查询
	String queryTokenById(@Param("id")String id);
}