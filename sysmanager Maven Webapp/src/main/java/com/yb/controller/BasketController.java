package com.yb.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Basket1;
import com.yb.entity.BasketNumber;
import com.yb.entity.BasketRules;
import com.yb.entity.DataPack;
import com.yb.entity.LongPack;
import com.yb.service.BasketService;

@Controller
@Scope("prototype")
@RequestMapping("/basket")
public class BasketController {
	@Resource
	private BasketService basketService;
	@ResponseBody
	@RequestMapping("/queryOne")
	public Map<String, Object> queryOne(){//柱状图，横坐标商品，类似top榜
		List<Basket1> list = basketService.queryOne();
		List<String> names = new ArrayList<String>();
		List<Long> number = new ArrayList<Long>();
		for (Basket1 basket1 : list) {
			names.add(basket1.getGood());
			number.add(basket1.getFreq_1());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		Collections.reverse(names);
		Collections.reverse(number);
		map.put("names", names);
		map.put("data", number);
		return map;
	}
	@ResponseBody
	@RequestMapping("/queryRule")
	public List<List<Double>> queryRule(){//点状图
		List<List<Double>> data=new ArrayList<List<Double>>();
		List<BasketRules> list = basketService.queryRule();
		for (BasketRules basketRules : list) {
			List<Double> number = new ArrayList<Double>();
			number.add(basketRules.getConfidence());
			number.add(basketRules.getSupport());
			number.add(basketRules.getLift());
			data.add(number);
		}
		return data;
	}
	@ResponseBody
	@RequestMapping("/queryNumber")
	public List<LongPack> queryNumber(){//饼图
		List<LongPack> datas = new ArrayList<LongPack>();
		List<BasketNumber> list = basketService.queryNumber();
		for (BasketNumber basketNumber : list) {
			datas.add(new LongPack("消费一件商品的", basketNumber.getNumber_1()));
			datas.add(new LongPack("消费多件商品的", basketNumber.getNumber_multi()));
		}
		return datas;
	}
	
}
