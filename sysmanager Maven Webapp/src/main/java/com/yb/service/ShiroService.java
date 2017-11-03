package com.yb.service;

import java.util.List;

import com.yb.entity.Permission;
import com.yb.entity.Role;

public interface ShiroService {
	public List<Permission> queryPermission(String name);
    public List<Role> queryRole(String name);
}
