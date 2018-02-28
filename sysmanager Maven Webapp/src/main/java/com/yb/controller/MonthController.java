package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yb.service.MonthService;

@Controller
@RequestMapping("/month")
@Scope("prototype")
public class MonthController {

	@Resource
	private MonthService monthService;
	
	@RequestMapping("/queryAllYear")
	@ResponseBody
	public List<String> queryAllYear(){
		List<String> queryAllYear = monthService.queryAllYear();
		return queryAllYear;
	}
	@RequestMapping("/queryByYear")
	@ResponseBody
	public List<String> queryByYear(String year){
		List<String> list = monthService.queryByYear(year);
		return list;
	}
}
