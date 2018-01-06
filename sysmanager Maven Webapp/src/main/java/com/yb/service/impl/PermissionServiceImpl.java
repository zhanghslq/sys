package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.PermissionDao;
import com.yb.entity.PermissionPack;
import com.yb.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;
	@Override
	public List<PermissionPack> queryAll(String rid) {
		// TODO Auto-generated method stub
		List<PermissionPack> list = permissionDao.queryAll();//查出来的全部资源
		List<String> permissionByRoleId = permissionDao.queryPermissionByRoleId(rid);
		for (PermissionPack permissionPack : list) {
				for (String string : permissionByRoleId) {
					if(permissionPack.getText().equals(string)){
						permissionPack.setChecked(true);
					}
			}
			List<PermissionPack> children = permissionPack.getChildren();
			for (PermissionPack permissionPack2 : children) {//有子节点的时候，对于子节点的
				for (String string : permissionByRoleId) {
					if(permissionPack2.getText().equals(string)){
						permissionPack2.setChecked(true);
					}
				}
			}
		}
		return list;
	}

}
