package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Tag;
import com.yb.service.TagService;

@Controller
@RequestMapping("/tag")
@Scope("prototype")
public class TagController {
	@Resource
	private TagService tagService;
	@RequestMapping("/add")
	@ResponseBody
	public void add(Tag tag){
		tagService.add(tag);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Integer id){
		tagService.delete(id);
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(Tag tag){
		tagService.update(tag);
	}
	@ResponseBody
	@RequestMapping("/queryById")
	public Tag queryById(Integer id){
		Tag tag = tagService.queryById(id);
		return tag;
	}
	@ResponseBody
	@RequestMapping("/queryAll")
	public List<Tag> queryAll(){
		List<Tag> list = tagService.queryAll();
		return list;
	}
	
}
