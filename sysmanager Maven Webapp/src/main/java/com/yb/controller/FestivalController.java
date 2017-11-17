package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Festival;
import com.yb.service.FestivalService;

@Controller
@RequestMapping("/festival")
@Scope("prototype")
public class FestivalController {

	@Resource
	private FestivalService festivalService;
	
	@ResponseBody
	@RequestMapping("/add")
	public void add(Festival festival){
		festivalService.add(festival);
	}
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(Integer id){
		festivalService.delete(id);
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(Festival festival){
		festivalService.update(festival);
	}
	@RequestMapping("/queryByName")
	@ResponseBody
	public Festival queryByName(String name){
		Festival festival = festivalService.queryByName(name);
		return festival;
	}
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Festival> queryAll(){
		List<Festival> list = festivalService.queryAll();
		return list;
	}
	@RequestMapping("/queryById")
	@ResponseBody
	public Festival queryById(Integer id){
		Festival festival = festivalService.queryById(id);
		return festival;
	}
	
}
