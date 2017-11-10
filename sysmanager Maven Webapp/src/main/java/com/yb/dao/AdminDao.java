package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Admin;

public interface AdminDao {
	//注册
	public Admin queryByName(String name);
	public void insert(Admin admin);
	public void delete(String id);
	public List<Admin> queryAll();//查询所有用户
	public void update(@Param("name")String name,@Param("password")String password);
	public void updateName(@Param("id")String id,@Param("name")String name);
}
