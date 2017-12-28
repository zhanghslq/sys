package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.Credit;

public interface CreditService {
	public List<Credit> queryCredit(String date,Date start,Date end,String area);
	public Credit queryZhanbi(String area);
}
