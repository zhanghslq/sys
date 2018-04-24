package com.yb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.NotOilDao;
import com.yb.entity.DataPack;
import com.yb.entity.Department;
import com.yb.entity.ExceptLube;
import com.yb.entity.NotOil;
import com.yb.entity.NotOilAndVip;
import com.yb.service.NotOilService;

@Service
public class NotOilServiceImpl implements NotOilService{

	@Autowired
	private NotOilDao notOilDao;
	@Override
	public List<NotOil> queryNotOils(String date, Date start, Date end,
			List<String> station, String people) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.queryNotOils(date, start, end, station,people);
		return list;
	}
	@Override
	public List<String> queryAllName() {
		// TODO Auto-generated method stub
		List<String> list = notOilDao.queryAllName();
		return list;
	}
	@Override
	public List<NotOil> queryRate(String date, Date start, Date end,
			List<String> station,String people) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.queryRate(date, start, end, station, people);
		return list;
	}
	@Override
	public List<DataPack> queryTop(Date start, Date end, List<String> station,
			String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = notOilDao.queryTop(start, end, station, people);
		return list;
	}
	@Override
	public NotOil queryByCompare(Date start, Date end, List<String> station,
			 String departmentName,String people) {
		// TODO Auto-generated method stub
		NotOil  notOil= notOilDao.queryByCompare(start, end, station,  departmentName,people);
		return notOil;
	}
	@Override
	public Double queryRateCompare(Date start, Date end, String station,
			String query) {
		// TODO Auto-generated method stub
		Double compare = notOilDao.queryRateCompare(start, end, station, query);
		return compare;
	}
	@Override
	public List<ExceptLube> queryExceptLube(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<ExceptLube> list = notOilDao.queryExceptLube(date, start, end, station);
		return list;
	}
	@Override
	public List<DataPack> querySearch(Date start, Date end, List<String>station,
			String date, String productCode,String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = notOilDao.querySearch(date, start, end, station, productCode,people);
		return list;
	}
	@Override
	public List<NotOilAndVip> queryAllAndVip(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<NotOilAndVip> list = notOilDao.queryAllAndVip(date, start, end, station);
		return list;
	}
	@Override
	public List<Department> queryAllDepartments(String date, Date start,
			Date end, List<String> station, String people) {
		// TODO Auto-generated method stub
		List<Department> list = notOilDao.queryAllDepartments(date, start, end, station, people);
		return list;
	}
	@Override
	public List<DataPack> queryThousandRate(String date, Date start, Date end,
			List<String> station,String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = notOilDao.queryThousandRate(date, start, end, station,people);
		return list;
	}
	@Override
	public List<NotOilAndVip> exportAllAndVip(String date, Date start,
			Date end, List<String> station) {
		// TODO Auto-generated method stub
		List<NotOilAndVip> list = notOilDao.exportAllAndVip(date, start, end, station);
		return list;
	}
	@Override
	public List<ExceptLube> exportExceptLube(String date, Date start, Date end,
			List<String> station) {
		// TODO Auto-generated method stub
		List<ExceptLube> list = notOilDao.exportExceptLube(date, start, end, station);
		return list;
	}
	@Override
	public List<DataPack> exportTop(Date start, Date end, List<String> station,
			String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = notOilDao.exportTop(start, end, station, people);
		return list;
	}
	@Override
	public List<DataPack> exportSearch(Date start, Date end,
			List<String> station, String date, String productCode,String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = notOilDao.exportSearch(date, start, end, station, productCode,people);
		return list;
	}
	@Override
	public List<NotOil> exportRate(String date, Date start, Date end,
			List<String> station, String people) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.exportRate(date, start, end, station, people);
		return list;
	}
	@Override
	public List<DataPack> exportThousandRate(String date, Date start, Date end,
			List<String> station, String people) {
		// TODO Auto-generated method stub
		List<DataPack> list = notOilDao.exportThousandRate(date, start, end, station, people);
		return list;
	}
	@Override
	public List<NotOil> exportByCompare(Date start, Date end,
			List<String> station, String departmentName, String people) {
		// TODO Auto-generated method stub
		List<NotOil> list = notOilDao.exportByCompare(start, end, station, departmentName, people);
		return list;
	}
	@Override
	public List<Department> exportAllDepartments(String date, Date start,
			Date end, List<String> station, String people) {
		// TODO Auto-generated method stub
		List<Department> list = notOilDao.exportAllDepartments(date, start, end, station, people);
		return list;
	}

}
