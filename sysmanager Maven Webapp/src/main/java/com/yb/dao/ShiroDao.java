package com.yb.dao;

import java.util.List;

import com.yb.entity.Permission;
import com.yb.entity.Role;

public interface ShiroDao {
	
		public List<Permission> queryPermission(String name);
	    public List<Role> queryRole(String name);

}