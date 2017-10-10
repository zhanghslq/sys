package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Category;
import com.yb.service.CategoryService;

@Controller
@RequestMapping("/category")
@Scope("prototype")
public class CategoryController {
	
	@Resource
	private CategoryService categoryService;
	@RequestMapping("/add")
	@ResponseBody
	public void add(Category category){
		categoryService.add(category);
	}
	@ResponseBody
	@RequestMapping("delete")
	public void delete(Integer id){
		categoryService.delete(id);
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(Category category){
		categoryService.update(category);
	}
	@RequestMapping("/queryById")
	@ResponseBody
	public Category queryById(Integer id){
		Category category = categoryService.queryById(id);
		return category;
	}
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Category> queryAll(){
		List<Category> list = categoryService.queryAll();
		return list;
	}
}
