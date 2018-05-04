package com.yb.service;

import java.util.List;

import com.yb.entity.Admin;
import com.yb.entity.PermissionPack;

public interface UserCrmService {
	List<Admin> queryAll();
	Admin queryByName(String name);
	void delete(String id);
	void update(String name,String password);
	void insert(Admin admin);
	
	
	//查询用户拥有的权限
	List<PermissionPack> queryPermissionByUser(String id);
	void updatePermission(String uid,List<String> pid);
}
