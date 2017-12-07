package com.yb.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.service.HoseService;

@Controller
@Scope("prototype")
@RequestMapping("/hose")
public class HoseController {
	@Resource
	private HoseService hoseService;
	
	@ResponseBody
	@RequestMapping("/query")
	public Double query(Date start,Date end,String pump,Integer hose,String station){
		Double queryHoseByPumpAndDate = hoseService.queryHoseByPumpAndDate(start, end, pump, hose,station);
		return queryHoseByPumpAndDate;
	}
}
