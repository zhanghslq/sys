package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.service.ProductService;

@Controller
@RequestMapping("/product")
@Scope("prototype")
public class ProductController {

	@Resource
	private ProductService productService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryProduct")
	@ResponseBody
	public Map<String, List> queryProduct(Date start,Date end,String station){
		List<DataPack> list = productService.queryProduct(start, end, station);
		List<String> dates = new ArrayList<String>();
		List<Double> data = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				data.add(dataPack.getValue());
			}
		}else {
			dates.add("无数据");
			data.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("data", data);
		return map;
	}
}
