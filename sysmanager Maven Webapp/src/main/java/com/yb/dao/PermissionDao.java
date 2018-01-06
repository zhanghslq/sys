package com.yb.dao;

import java.util.List;

import com.yb.entity.PermissionPack;

public interface PermissionDao {
	List<PermissionPack> queryAll();
	PermissionPack queryByParentId(String id);
	
	List<String> queryPermissionByRoleId(String rid);
}
