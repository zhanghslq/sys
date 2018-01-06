package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Role;

public interface RoleDao {
	public void insert(Role role);
	public void delete(String id);
	void deleteFromUserRole(@Param("rid")String rid);
	Role queryById(String id);
	public void update(Role role);
	public List<Role> queryAll();
	public Role queryByUserId(String id);
	
	void insertRolePermission(@Param("rid")String rid,@Param("pid")String pid);
	void deleteRolePermission(@Param("rid")String rid);
	
	Role queryByName(String name);//根据角色名查询
	
	
}
