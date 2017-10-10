package com.yb.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yb.service.StationService;

@Controller
@Scope("prototype")
@RequestMapping("/station")
public class StationController {
	@Resource
	private StationService stationService;

}
