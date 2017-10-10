package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.service.OilGunService;

@Controller
@Scope("prototype")
@RequestMapping("/oilGun")
public class OilGunController {

	@Resource
	private OilGunService oilGunService;
	@ResponseBody
	@RequestMapping("/queryGun")
	public List<String> queryGun(String station){
		List<String> queryGun = oilGunService.queryGun(station);
		return queryGun;
	}
}
