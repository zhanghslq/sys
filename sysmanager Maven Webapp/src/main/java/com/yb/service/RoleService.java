package com.yb.service;

import java.util.List;

import com.yb.entity.Role;

public interface RoleService {
	Role queryById(String id);
	public void insert(Role role);
	public void delete(String id);
	public void update(Role role);
	public List<Role> queryAll();
	public Role queryByUserId(String id);
	
	void insertRolePermission(String rid,String pid);
	void deleteRolePermission(String rid);
	
	Role queryByName(String name);
	
	void updatePermission(String rid,List<String> pid);
}
