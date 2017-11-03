package com.yb.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.test.MD5Utils;
import com.baizhi.test.Saltutils;
import com.yb.dao.AdminDao;
import com.yb.entity.Admin;
import com.yb.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	@Override
	public Admin queryByName(String name) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.queryByName(name);
		return admin;
	}
	
	@Override
	public void regist(Admin admin) {
		// TODO Auto-generated method stub
	    admin.setId(UUID.randomUUID().toString());
        String password = admin.getPassword();
        String salt = Saltutils.getSalt(6);
        admin.setSalt(salt);
        String digest = MD5Utils.getDigest(salt+password);
        admin.setPassword(digest);
        adminDao.insert(admin);
	}

	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		adminDao.delete(id);
	}
	
}
