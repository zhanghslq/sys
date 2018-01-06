package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.PermissionPack;
import com.yb.service.PermissionService;
@Controller
@RequestMapping("/permission")
@Scope("prototype")
public class PermissionController {

	@Resource
	private PermissionService permissionService;
	@ResponseBody
	@RequestMapping("/queryAll")
	public List<PermissionPack> queryAll(String rid){
		List<PermissionPack> list = permissionService.queryAll(rid);
		return list;
	}
}
