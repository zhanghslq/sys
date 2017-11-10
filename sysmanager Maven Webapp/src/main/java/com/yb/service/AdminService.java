package com.yb.service;

import com.yb.entity.Admin;

public interface AdminService {
	public Admin queryByName(String name);
	public void regist(Admin admin);
	public void update(String name,String password);
	public void delete(String id);
}
