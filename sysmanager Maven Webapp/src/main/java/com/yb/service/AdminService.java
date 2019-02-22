package com.yb.service;

import java.util.List;

import com.yb.entity.Admin;

public interface AdminService {
	public Admin queryById(String id);
	public Admin queryByName(String name);
	public void regist(Admin admin);
	public void update(String name,String password);
	public void delete(String id);
	public List<Admin> queryAll(Integer start,Integer rows);//查询所有用户
	public void updateRole(String id,String role);

	Integer queryTotal();
}
