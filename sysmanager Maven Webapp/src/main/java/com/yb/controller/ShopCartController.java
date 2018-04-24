package com.yb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RList;
import org.rosuda.JRI.Rengine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.InterPack;
import com.yb.entity.Station;
import com.yb.service.ShopCartService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DateFormatUtils;
import com.yb.util.SingleRengine;

@Controller
@RequestMapping("/shopCart")
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
	public String query(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,Double support,Double confidence,String oil){
		try {
			System.out.println("进入方法");
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
			List<InterPack> query = shopCartService.query(stationid, start, end, oil);
			System.out.println("查询成功");
			
			
			String product[]=new String[query.size()];
			int trans[]=new int[query.size()];
			for (int i = 0; i < query.size(); i++) {
				product[i]=query.get(i).getName();
				trans[i]=query.get(i).getValue();
			}
			if(support==null||support>1||support<0){
				support=0.01;
			}
			if(confidence==null||confidence>1||confidence<0){
				confidence=0.5;
			}
			//还需要判断是否已经返回结果，结果返回之后才可以进行再次查询
			    //调用R
				System.out.println( System.getProperty("java.library.path"));
				Rengine engine = SingleRengine.getRegineInstance();
			    // 等待解析类初始化完毕
			    if (!engine.waitForR()) {
			        return "!engine.waitForR()";
			    }
			    // 加载包
				engine.eval("library(Matrix)");
				engine.eval("library(arules)");
				engine.eval("library(dplyr)");
				System.out.println("加载包成功");
				System.out.println("开始执行");
			//	String filePath = "D:/ap/1.R";
				String filePath = "/opt/RFile/basket.R";
				 // 将文件全路径复制给R中的一个变量
			    engine.assign("fileName", filePath);
			    // 在R中执行文件。执行后，文件中的两个函数加载到R环境中，后续可以直接调用
			    engine.eval("source(fileName)");
			    System.err.println("R文件执行完毕");
				//支持度，置信度先定义好
				engine.eval("confidenceJava <-"+confidence);
				engine.eval("supportJava <-"+support);
				System.out.println("tets");
				engine.assign("product_code", product);
				engine.assign("transaction_id",trans);
				System.out.println("test");
				
	           
	            System.err.println("---------------2");
				engine.eval("basketJava <- data.frame(transaction_id, product_code)");
				System.out.println("生成DataFrame");
				REXP rexp = engine.eval("basketJava");
				RList list = rexp.asList();
	            String[] key = list.keys();
	            System.err.println(Arrays.toString(key));
	            if(key != null){
	                int i = 0;
	                while (i < key.length){
	                    i++;
	                }
	            }
			    // 入参为list，出参为list。调用R中函数，将结果保存到一个对象中。
			    REXP rexp2 = engine.eval("basket(basketJava, supportJava,confidenceJava)");
			    System.out.println("调用R结束");
			    // 解析rexp对象，转换数据格式
			    // list的标题
			    System.out.println(rexp2);
			    engine.stop();//关闭R
			    return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return "Exception";
		}
	}
}
