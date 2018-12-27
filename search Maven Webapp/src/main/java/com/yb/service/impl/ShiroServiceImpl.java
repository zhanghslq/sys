package com.yb.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.ShiroDao;
import com.yb.entity.Admin;
import com.yb.service.ShiroService;

/**
 * Created by Administrator on 2017/6/20 0020.
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private ShiroDao shiroDao;

    public List<String> queryPermission(String name) {
        List<String> permissions = shiroDao.queryPermission(name);
        return permissions;
    }

	@Override
	public Admin queryByName(String name) {
		// TODO Auto-generated method stub
		Admin admin = shiroDao.queryByName(name);
		return admin;
	}

	@Transactional
	@Override
	public void insertToken(String id, String name) {
		// TODO Auto-generated method stub
		shiroDao.insertToken(id, name);
		
	}

	
	@Override
	public String queryTokenById(String id) {
		// TODO Auto-generated method stub
		String string = shiroDao.queryTokenById(id);
		return string;
	}
}
