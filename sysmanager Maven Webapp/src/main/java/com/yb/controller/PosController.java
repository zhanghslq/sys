package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Pos;
import com.yb.service.PosService;

@Controller
@RequestMapping("/pos")
@Scope("prototype")
public class PosController {

	@Resource
	private PosService posService;
	
	@RequestMapping("/add")
	@ResponseBody
	public void add(String name){
		posService.add(name);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Integer id){
		posService.delete(id);
	}
	@RequestMapping("update")
	@ResponseBody
	public void update(Pos pos){
		posService.update(pos);
	}
	@RequestMapping("/queryById")
	@ResponseBody
	public Pos queryById(Integer id){
		Pos pos = posService.queryById(id);
		return pos;
	}
	@ResponseBody
	@RequestMapping("/queryAll")
	public List<Pos> queryAll(){
		List<Pos> list = posService.queryAll();
		return list;
	}
	
}
