package com.yb.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.test.MD5Utils;
import com.baizhi.test.Saltutils;
import com.yb.dao.UserCrmDao;
import com.yb.entity.Admin;
import com.yb.entity.PermissionPack;
import com.yb.service.UserCrmService;

@Service
@Transactional
public class UserCrmServiceImpl implements UserCrmService{

	@Autowired
	private UserCrmDao userCrmDao;
	@Override
	public List<Admin> queryAll() {
		// TODO Auto-generated method stub
		List<Admin> list = userCrmDao.queryAll();
		return list;
	}

	@Override
	public Admin queryByName(String name) {
		// TODO Auto-generated method stub
		Admin admin = userCrmDao.queryByName(name);
		return admin;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		userCrmDao.delete(id);
	}

	@Override
	public void update(String name, String password) {
		// TODO Auto-generated method stub
		userCrmDao.update(name, password);
	}

	@Override
	public void insert(Admin admin) {
		// TODO Auto-generated method stub
		admin.setId(UUID.randomUUID().toString());
		String salt = Saltutils.getSalt(6);
		admin.setSalt(salt);
		admin.setPassword(MD5Utils.getDigest(salt+admin.getPassword()));
		userCrmDao.insert(admin);
	}

	@Override
	public List<PermissionPack> queryPermissionByUser(String id) {
		// TODO Auto-generated method stub
		//查询的所有资源，需要查哪些资源是拥有的
		List<PermissionPack> list = userCrmDao.queryPermission();
		List<String> permissionByRoleId = userCrmDao.queryPermissionByUser(id);
		for (PermissionPack permissionPack : list) {
				for (String string : permissionByRoleId) {
					if(permissionPack.getId().equals(string)){
						permissionPack.setChecked(true);
					}
			    }
				
		}
		return list;
	}

	@Override
	public void updatePermission(String uid, List<String> pid) {
		// TODO Auto-generated method stub
		userCrmDao.deletePermissionByUserId(uid);
		userCrmDao.insertPermission(uid, pid);
	}


}
