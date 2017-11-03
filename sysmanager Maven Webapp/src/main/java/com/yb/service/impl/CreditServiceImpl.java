package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.CreditDao;
import com.yb.entity.Credit;
import com.yb.service.CreditService;

@Service
public class CreditServiceImpl implements CreditService{

	@Autowired
	private CreditDao creditDao;
	@Override
	public List<Credit> queryCredit(String date, Date start, Date end) {
		// TODO Auto-generated method stub
		List<Credit> list = creditDao.queryCredit(date, start, end);
		return list;
	}

	@Override
	public Credit queryZhanbi() {
		// TODO Auto-generated method stub
		Credit credit = creditDao.queryZhanbi();
		return credit;
	}

}
