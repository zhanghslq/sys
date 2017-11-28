package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.DouPack;
import com.yb.entity.InterPack;
import com.yb.entity.VipFunnel;
import com.yb.service.VipFunnelService;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/vipFunnel")
@Scope("prototype")
public class VipFunnelController {
	@Resource
	private VipFunnelService vipFunnelService;
	@RequestMapping("/queryAllMonth")
	@ResponseBody
	public List<String> queryAllMonth(){
		List<String> list = vipFunnelService.queryAllMonth();
		return list;
	}
	@RequestMapping("/queryVipFunnel")
	@ResponseBody
	public List<InterPack> queryVipFunnel(String month){
		VipFunnel funnel = vipFunnelService.queryVipFunnel(month);
		List<InterPack> list = new ArrayList<InterPack>();
		if(funnel!=null){
			list.add(new InterPack("至少消费一次的", funnel.getAtLeastOne()));
			list.add(new InterPack("活跃会员",funnel.getLiveness()));
			list.add(new InterPack("会员总数",funnel.getSum()));
		}else {
			list.add(new InterPack("至少消费一次的", 0));
			list.add(new InterPack("活跃会员",0));
			list.add(new InterPack("会员总数",0));
		}
		return list;
	}
	//流失会员人数及占比
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryDrain")
	public Map<String, List> queryDrain(String date,Date start,Date end){
		List<DouPack> list = vipFunnelService.queryDrain(date,start,end);
		List<String> month = new ArrayList<String>();
		List<Double> drain = new ArrayList<Double>();
		List<Double> rate = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DouPack douPack : list) {
				drain.add(douPack.getDrainNum());
				rate.add(DoubleFormatUtil.format(douPack.getOther()*100));
				month.add(douPack.getMonth());
			}
		}else {
			drain.add(0.0);
			rate.add(0.0);
			month.add("无数据");
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("drain", drain);
		map.put("rate", rate);
		map.put("month", month);
		return map;
	}
	
	
}
