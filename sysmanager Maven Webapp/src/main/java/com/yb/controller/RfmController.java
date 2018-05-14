package com.yb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.DouRfm;
import com.yb.service.RfmService;

@Controller
@RequestMapping("/rfm")
@Scope("prototype")
public class RfmController {

	@Resource
	private RfmService rfmService;
	
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> queryMap(String area){
		List<DataPack> list = rfmService.queryByGroup(area);
		//将分好的list数组转为Map，然后从map中取自己需要的数据
		Map<String, Double> map = new HashMap<String,Double>(list.size());
		for (DataPack dataPack : list) {
			map.put(dataPack.getName(), dataPack.getValue());
		}
		
		//去除null
		List<String> type = new ArrayList<String>(14);
		type.add("1111");
		type.add("1011");
		type.add("1101");
		type.add("1001");
		type.add("1110");
		type.add("1100");
		type.add("1010");
		type.add("1000");
		type.add("0");
		type.add("1");
		type.add("2");
		type.add("3");
		type.add("4");
		type.add("5");
		for (String string : type) {
			if(map.get(string)==null){
				map.put(string, 0.0);
			}
		}
		Double double1=map.get("1111");
		Double double2=map.get("1011");
		Double double3=map.get("1101");
		Double double4=map.get("1001");
		Double double5=map.get("1110");
		Double double6=map.get("1100");
		Double double7=map.get("1010");
		Double double8=map.get("1000");
		Double double9=map.get("0");
		Double double10=map.get("1");
		Double double11=map.get("2");
		Double double12=map.get("3");
		Double double13=map.get("4");
		Double double14=map.get("5");
		//内层
		List<DataPack> one = new ArrayList<DataPack>();
		one.add(new DataPack("90天内有消费",double1+double2+double3+double4+double5+double6+double7+double8));
		one.add(new DataPack("90天内无消费", double9+double10+double11+double12));
		//中层，一次，多次，一般，重要
		List<DataPack> two = new ArrayList<DataPack>();
		two.add(new DataPack("重要客户", double1+double2+double3+double4));
		two.add(new DataPack("一般客户", double5+double6+double7+double8));
		two.add(new DataPack("多次消费", double9+double11+double12));
		two.add(new DataPack("一次消费", double10));
		//外层大圈
		List<DataPack> three = new ArrayList<DataPack>();
		three.add(new DataPack("重要价值客户", double1));
		three.add(new DataPack("重要保持客户", double2));
		three.add(new DataPack("重要发展客户", double3));
		three.add(new DataPack("重要挽留客户", double4));
		three.add(new DataPack("一般价值客户", double5));
		three.add(new DataPack("一般发展客户", double6));
		three.add(new DataPack("一般保持客户", double7));
		three.add(new DataPack("一般挽留客户", double8));
		three.add(new DataPack("沉睡客户", double9));
		three.add(new DataPack("一次客户", double10));
		three.add(new DataPack("重点唤醒客户", double11));
		three.add(new DataPack("一般唤醒客户", double12));
		three.add(new DataPack("历史上唯一一次消费发生在30天内", double13));
		three.add(new DataPack("历史上唯一一次消费发生在31-90天", double14));
		//第二张环形图的内圈
		List<DataPack> four = new ArrayList<DataPack>();
		four.add(new DataPack("多次消费", double1+double2+double3+double4+double5+double6+double7+double8+double9+double11+double12));
		four.add(new DataPack("一次消费", double10+double13+double14));
		//第二张环形图的外圈
		List<DataPack> five = new ArrayList<DataPack>();
		five.add(new DataPack("多次消费", double1+double2+double3+double4+double5+double6+double7+double8+double9+double11+double12));
		five.add(new DataPack("历史上唯一一次消费发生在30天内", double13));
		five.add(new DataPack("历史上唯一一次消费发生在31-90天", double14));
		five.add(new DataPack("历史上唯一一次消费发生在90天之前", double10));
		//90天内有消费的柱状图
		List<String> names = new ArrayList<String>();
		List<Double> values = new ArrayList<Double>();
		names.add("重要价值客户");
		names.add("重要保持客户");
		names.add("重要发展客户");
		names.add("重要挽留客户");
		names.add("一般价值客户");
		names.add("一般发展客户");
		names.add("一般保持客户");
		names.add("一般挽留客户");
		values.add(double1);
		values.add(double2);
		values.add(double3);
		values.add(double4);
		values.add(double5);
		values.add(double6);
		values.add(double7);
		values.add(double8);
		//三个月的点状图
		List<List<Double>> threeData = new ArrayList<List<Double>>();
		List<DouRfm> queryThreeRfms = rfmService.queryThreeRfms(area);
		for (DouRfm douRfm : queryThreeRfms) {
			List<Double> dataArrayList = new ArrayList<Double>();
			dataArrayList.add(douRfm.getR());
			dataArrayList.add(douRfm.getRate());
			dataArrayList.add(douRfm.getMoney());
			threeData.add(dataArrayList);
		}
		//历史点状图
		List<List<Double>> historyData = new ArrayList<List<Double>>();
		List<DouRfm> queryHistoryRfms = rfmService.queryHistoryRfms(area);
		for (DouRfm douRfm : queryHistoryRfms) {
			List<Double> dataArrayList = new ArrayList<Double>();
			dataArrayList.add(douRfm.getR());
			dataArrayList.add(douRfm.getRate());
			dataArrayList.add(douRfm.getMoney());
			historyData.add(dataArrayList);
		}
		//返回结果的集合
		Map<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("one", one);
		hashMap.put("two", two);
		hashMap.put("three", three);
		hashMap.put("four", four);
		hashMap.put("five", five);
		hashMap.put("names", names);
		hashMap.put("values", values);
		hashMap.put("threeData",threeData );
		hashMap.put("historyData",historyData );
		return hashMap;
	}
}
