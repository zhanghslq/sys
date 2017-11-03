package com.yb.service;

import com.yb.entity.Admin;

public interface AdminService {
	public Admin queryByName(String name);
	public void regist(Admin admin);
	public void update(Admin admin);
	public void delete(String id);
	public Admin login(String name,String password);
}
