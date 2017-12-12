package com.yb.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.service.HoseService;
import com.yb.util.DoubleFormatUtil;

@Controller
@Scope("prototype")
@RequestMapping("/hose")
public class HoseController {
	@Resource
	private HoseService hoseService;
	
	@ResponseBody
	@RequestMapping("/query")
	public String query(Date start,Date end,String pump,Integer hose,String station){
		Double queryHoseByPumpAndDate = hoseService.queryHoseByPumpAndDate(start, end, pump, hose,station);
		Double format=0.0;
		if(queryHoseByPumpAndDate!=null){
			format = DoubleFormatUtil.format(queryHoseByPumpAndDate*100);
		}
		return format+"%";
	}
}
