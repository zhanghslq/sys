package com.yb.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.rosuda.JRI.Rengine;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.InterPack;
import com.yb.entity.LongPack;
import com.yb.entity.Station;
import com.yb.entity.StringPack;
import com.yb.service.ShopCartService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DateFormatUtils;
import com.yb.util.RUtil;
import com.yb.util.SingleRengine;

@Controller
@RequestMapping("/shopCart")
@Scope("prototype")
public class ShopCartController {
	@Resource
	private StationService stationService;
	@Resource
	private ShopCartService shopCartService;
	/**
	 * 先根据用户输入，来取出需要的basket.all的数据
	 * 調用R的函數，更新数据库数据，然后
	 * 支持度和置信度选填0~1之间
	 */
	@ResponseBody
	@RequestMapping("/query")
	@Cacheable("shopCart")
	public Map<String, Object> query(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,Double support,Double confidence,String oil,String department){
		try {
			System.out.println("进入方法");
			Integer status = shopCartService.getStatus();
			if(status==1){
				shopCartService.updateStatus(0);
			}else {
				return null;
			}
			//求出来的油站
			if(oil==null){
				oil="all";
			}
			if(start==null){
				start=DateFormatUtils.getMonthStart();
			}
			if(end==null){
				end =new Date();
			}
			List<String> stationid = new ArrayList<String>();
			if(ArryToListUtil.format(station)!=null){
				stationid=ArryToListUtil.format(station);
			}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
				List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
						ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
						ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
				if(queryStationBy!=null){
					for (Station station2 : queryStationBy) {
						stationid.add(station2.getId());
					}
				}
			}
			System.out.println("开始查询");
			List<InterPack> query = shopCartService.query(stationid, start, end, oil,department);
			System.out.println("查询成功");
			if(query.size()!=0){
				String product[]=new String[query.size()];
				int trans[]=new int[query.size()];
				for (int i = 0; i < query.size(); i++) {
					product[i]=query.get(i).getName();
					trans[i]=query.get(i).getValue();
				}
				System.out.println(product.toString());
				if(support==null||support>1||support<0){
					support=0.01;
				}
				if(confidence==null||confidence>1||confidence<0){
					confidence=0.5;
				}
				//还需要判断是否已经返回结果，结果返回之后才可以进行再次查询
				    //调用R
					Rengine engine = SingleRengine.getRegineInstance();
				    // 等待解析类初始化完毕
				    if (!engine.waitForR()) {
				    	System.out.println("exception");
				    	shopCartService.updateStatus(1);
				        return null;
				    }
				    // 加载包
					System.out.println("开始执行");
					String filePath = "D:/ap/1.R";
					//String filePath = "/opt/RFile/basket.R";
					 // 将文件全路径复制给R中的一个变量
				    engine.assign("fileName", filePath);
				    // 在R中执行文件。执行后，文件中的两个函数加载到R环境中，后续可以直接调用
				    engine.eval("source(fileName)");
				    System.err.println("R文件执行完毕");
					//支持度，置信度先定义好
					engine.eval("confidenceJava <-"+confidence);
					engine.eval("supportJava <-"+support);
					engine.assign("transaction_id",trans);
					
					engine.assign("department_id", product);
					
					engine.eval("basketJava <- data.frame(transaction_id, department_id)");
					System.out.println("生成DataFrame");
				    // 入参为list，出参为list。调用R中函数，将结果保存到一个对象中。
				    engine.eval("basket(basketJava, supportJava,confidenceJava)");
				    // REXP rexp2 = engine.eval("basket()");
				    System.out.println("调用R结束");
				    //先读入Department
				    List<StringPack> list=null;
				    if("product".equals(department)){
				    	list=shopCartService.queryProductName();
				    }else {
				    	list = shopCartService.queryDepartment();
					}
				    Map<String, String> dep = new HashMap<String,String>();
				    for (StringPack interPack : list) {
						dep.put(interPack.getValue(), interPack.getName());
					}
				    //读入结果,basket_1
				    FileReader fileReader = new FileReader("basket_1.csv");
				    BufferedReader reader = new BufferedReader(fileReader);//换成你的文件名   
		            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
		            List<String> names = new ArrayList<String>();
		            List<Double> data = new ArrayList<Double>();
				    String line = null;
		            while((line=reader.readLine())!=null){    
		                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
		                names.add(dep.get(item[0].toString()));
		                Double two = Double.parseDouble(item[1]);//这就是你要的数据了   
		                data.add(two);
		            }
		            reader.close();
		            fileReader.close();
		            Collections.reverse(data);
		            List<LongPack> number = RUtil.getNumber();
		            List<List<Double>> rule = RUtil.getRule();
		            //存放返回数据
		            Map<String,Object> map = new HashMap<String,Object>();
		            map.put("names", names);
		            map.put("data", data);
		            map.put("datasNumber", number);
		            map.put("rule", rule);
		            shopCartService.updateStatus(1);
				    return map;
			}else {
				shopCartService.updateStatus(1);
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			shopCartService.updateStatus(1);
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/queryLink")
	public Map<String, Object> queryLink(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String productCode,String oil,String department){
		try {
			System.out.println("进入方法");
			Integer status = shopCartService.getStatus();
			if(status==1){
				shopCartService.updateStatus(0);
			}else {
				return null;
			}
			//求出来的油站
			if(oil==null){
				oil="all";
			}
			if(start==null){
				start=DateFormatUtils.getMonthStart();
			}
			if(end==null){
				end =new Date();
			}
			if(productCode==null){
				productCode="561";
			}
			List<String> stationid = new ArrayList<String>();
			if(ArryToListUtil.format(station)!=null){
				stationid=ArryToListUtil.format(station);
			}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
				List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
						ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
						ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
				if(queryStationBy!=null){
					for (Station station2 : queryStationBy) {
						stationid.add(station2.getId());
					}
				}
			}
			System.out.println("开始查询");
			List<InterPack> query = shopCartService.query(stationid, start, end, oil,department);
			System.out.println("查询成功");
			if(query.size()!=0){
				String product[]=new String[query.size()];
				int trans[]=new int[query.size()];
				for (int i = 0; i < query.size(); i++) {
					product[i]=query.get(i).getName();
					trans[i]=query.get(i).getValue();
				}
				
				//还需要判断是否已经返回结果，结果返回之后才可以进行再次查询
				//调用R
				Rengine engine = SingleRengine.getRegineInstance();
				// 等待解析类初始化完毕
				if (!engine.waitForR()) {
					System.out.println("exception");
					shopCartService.updateStatus(1);
					return null;
				}
				// 加载包
				System.out.println("开始执行");
				String filePath = "D:/ap/link.R";
				//String filePath = "/opt/RFile/link.R";
				// 将文件全路径复制给R中的一个变量
				engine.assign("fileName", filePath);
				// 在R中执行文件。执行后，文件中的两个函数加载到R环境中，后续可以直接调用
				engine.eval("source(fileName)");
				System.err.println("R文件执行完毕");
				//支持度，置信度先定义好
				engine.assign("productJava",productCode);
				engine.assign("transaction_id",trans);
				engine.assign("department_id", product);
				engine.eval("basketJava <- data.frame(transaction_id, department_id)");
				System.out.println("生成DataFrame");
				// 入参为list，出参为list。调用R中函数，将结果保存到一个对象中。
				engine.eval("link(basketJava,productJava )");
				// REXP rexp2 = engine.eval("basket()");
				System.out.println("调用R结束");
				//先读入Department
				List<StringPack> list=null;
				if("product".equals(department)){
					list=shopCartService.queryProductName();
				}else {
					list = shopCartService.queryDepartment();
				}
				Map<String, String> dep = new HashMap<String,String>();
				for (StringPack interPack : list) {
					dep.put(interPack.getValue(), interPack.getName());
				}
				//读入结果,link_number
				List<LongPack> linkNumber = RUtil.getLinkNumber();
				FileReader fileReader = new FileReader("link.csv");
			    BufferedReader reader = new BufferedReader(fileReader);//换成你的文件名   
	            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
	            List<String> names = new ArrayList<String>();
	            List<Double> support = new ArrayList<Double>();
	            List<Integer> count = new ArrayList<Integer>();
	            List<Double> v4 = new ArrayList<Double>();
			    String line = null;
	            while((line=reader.readLine())!=null){    
	                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	                names.add(dep.get(item[0].toString()));
	                Double two = Double.parseDouble(item[1]);//这就是你要的数据了   
	                support.add(two*100);
	                Integer parseInt = Integer.valueOf(item[2]);
	                count.add(parseInt);
	                double parseDouble = Double.parseDouble(item[3]);
	                v4.add(parseDouble*100);
	            }
	            reader.close();
	            fileReader.close();
				//存放返回数据
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkNumber", linkNumber);
				map.put("names",names);
				map.put("support", support);
				map.put("count", count);
				map.put("v4", v4);
				shopCartService.updateStatus(1);
				return map;
			}else {
				shopCartService.updateStatus(1);
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			shopCartService.updateStatus(1);
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/queryAllDep")
	public List<StringPack> queryAllDep(){
		List<StringPack> list = shopCartService.queryDepartment();
		return list;
	}
}
