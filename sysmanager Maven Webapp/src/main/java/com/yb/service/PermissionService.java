package com.yb.service;

import java.util.List;

import com.yb.entity.PermissionPack;

public interface PermissionService {
	List<PermissionPack> queryAll(String rid);
}
