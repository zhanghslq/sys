package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Permission;
import com.yb.entity.Role;

public interface ShiroDao {
		public List<Permission> queryPermission(String name);
	    public List<Role> queryRole(String name);
	    void addRole(Role role);
	    void addUserRole(@Param("uid")String uid,@Param("rid")String rid);
	    void addRolePermission(@Param("rid")String rid,@Param("pid")String pid);
}