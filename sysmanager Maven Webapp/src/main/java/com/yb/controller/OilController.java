package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.yb.entity.*;
import com.yb.service.HeartService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.OilService;
import com.yb.service.StationService;
import com.yb.service.TargetService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DateFormatUtils;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/oil")
@Scope("prototype")
@RequiresAuthentication
public class OilController {

	@Resource
	private OilService oilService;
	@Resource
	private StationService stationService;
	@Resource
	private TargetService targetService;
	@Resource
	private HeartService heartService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryAndVip")
	@ResponseBody
	public Map<String, List> queryAndVip(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			@RequestParam(required=false,value="week[]")Integer [] week,
			Date start,Date end,String date){
		List<OilAndVip> list = new ArrayList<OilAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryAllAndVip(date, start, end, ArryToListUtil.format(station),ArryToListUtil.formatInteger(week));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryAllAndVip(date, start,end,stationid,ArryToListUtil.formatInteger(week));
		}
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgAmounts = new ArrayList<Double>();
		List<Double> moneys= new ArrayList<Double>();
		List<Double> vipamounts = new ArrayList<Double>();
		List<Integer> vipnumbers = new ArrayList<Integer>();
		List<Double> vipavgAmounts = new ArrayList<Double>();
		List<Double> vipMoneys = new ArrayList<Double>();
		List<Double> notvipamounts = new ArrayList<Double>();
		List<Integer> notvipnumbers = new ArrayList<Integer>();
		List<Double> notvipavgAmounts = new ArrayList<Double>();
		List<Double> notvipMoneys = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (OilAndVip oilAndVip : list) {
				dates.add(oilAndVip.getDate());
				amounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getOilLitre())/1000));
				avgAmounts.add(DoubleFormatUtil.format(oilAndVip.getAvgLitre()));
				numbers.add(oilAndVip.getOilNumber());
				vipamounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getVipOilLitre())/1000));
				vipavgAmounts.add(DoubleFormatUtil.format(oilAndVip.getVipAvgLitre()));
				moneys.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getOilMoney()/10000)));
				vipnumbers.add(oilAndVip.getVipOilNumber());
				if(oilAndVip.getVipOilMoney()!=null){
					vipMoneys.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getVipOilMoney()/10000)));
				}else {
					vipMoneys.add(DoubleFormatUtil.format(0.0));
				}
				notvipamounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getNotVipOilLitre())/1000));
				notvipavgAmounts.add(DoubleFormatUtil.format(oilAndVip.getNotVipAvgLitre()));
				notvipMoneys.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getNotVipOilMoney())/10000));
				notvipnumbers.add(oilAndVip.getNotVipOilNumber());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
			avgAmounts.add(0.0);
			numbers.add(0);
		}
		map.put("dates", dates);
		map.put("amounts", amounts);
		map.put("avgAmounts", avgAmounts);
		map.put("numbers", numbers);
		map.put("vipamounts", vipamounts);
		map.put("vipavgAmounts", vipavgAmounts);
		map.put("vipnumbers", vipnumbers);
		map.put("moneys", moneys);
		map.put("vipMoneys", vipMoneys);
		map.put("notvipamounts",notvipamounts );
		map.put("notvipavgAmounts", notvipavgAmounts);
		map.put("notvipMoneys",notvipMoneys );
		map.put("notvipnumbers",notvipnumbers );
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryAndVipByOils")
	@ResponseBody
	public Map<String, List> queryAndVipByOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			@RequestParam(required=false,value="week[]")Integer [] week,
			Date start,Date end,String date,String oils){
		List<OilAndVip> list = new ArrayList<OilAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryAllAndVipByOils(date, start, end, ArryToListUtil.format(station),oils,ArryToListUtil.formatInteger(week));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryAllAndVipByOils(date, start,end,stationid,oils,ArryToListUtil.formatInteger(week));
		}
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgAmounts = new ArrayList<Double>();
		List<Double> moneys= new ArrayList<Double>();
		List<Double> vipamounts = new ArrayList<Double>();
		List<Integer> vipnumbers = new ArrayList<Integer>();
		List<Double> vipavgAmounts = new ArrayList<Double>();
		List<Double> vipMoneys = new ArrayList<Double>();
		List<Double> notvipamounts = new ArrayList<Double>();
		List<Integer> notvipnumbers = new ArrayList<Integer>();
		List<Double> notvipavgAmounts = new ArrayList<Double>();
		List<Double> notvipMoneys = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (OilAndVip oilAndVip : list) {
				dates.add(oilAndVip.getDate());
				amounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getOilLitre())/1000));
				avgAmounts.add(DoubleFormatUtil.format(oilAndVip.getAvgLitre()));
				numbers.add(oilAndVip.getOilNumber());
				vipamounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getVipOilLitre())/1000));
				vipavgAmounts.add(DoubleFormatUtil.format(oilAndVip.getVipAvgLitre()));
				moneys.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getOilMoney()/10000)));
				vipnumbers.add(oilAndVip.getVipOilNumber());
				if(oilAndVip.getVipOilMoney()!=null){
					vipMoneys.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getVipOilMoney()/10000)));
				}else {
					vipMoneys.add(DoubleFormatUtil.format(0.0));
				}
				notvipamounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getNotVipOilLitre())/1000));
				notvipavgAmounts.add(DoubleFormatUtil.format(oilAndVip.getNotVipAvgLitre()));
				notvipMoneys.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getNotVipOilMoney())/10000));
				notvipnumbers.add(oilAndVip.getNotVipOilNumber());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
			avgAmounts.add(0.0);
			numbers.add(0);
		}
		map.put("dates", dates);
		map.put("amounts", amounts);
		map.put("avgAmounts", avgAmounts);
		map.put("numbers", numbers);
		map.put("vipamounts", vipamounts);
		map.put("vipavgAmounts", vipavgAmounts);
		map.put("vipnumbers", vipnumbers);
		map.put("moneys", moneys);
		map.put("vipMoneys", vipMoneys);
		map.put("notvipamounts",notvipamounts );
		map.put("notvipavgAmounts", notvipavgAmounts);
		map.put("notvipMoneys",notvipMoneys );
		map.put("notvipnumbers",notvipnumbers );
		return map;
	}
	/**
	 * 油品详细里的分油品的  销售量与单车加油量   会员  非会员 and  总的
	 * @param citys
	 * @param regions
	 * @param sales
	 * @param gasoline
	 * @param locs
	 * @param openDate
	 * @param type
	 * @param station
	 * @param start
	 * @param end
	 * @param date
	 * @param oils
	 *
	 * 12/7  新添加销售笔数
	 */
	@ResponseBody
	@RequestMapping("/exportOilAndVipByOils")
	public void exportOilAndVipByOils(@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			@RequestParam(required=false,value="week")Integer [] week,
			Date start,Date end,String date,String oils,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+oils+"销售情况.xls", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oils+="#";
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		OutputStream os=null;
        try {
			os= new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //获取需要导出的集合信息
        List<OilAndVip> list = new ArrayList<OilAndVip>();
        List<OilAndVip> list2 = new ArrayList<OilAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryAllAndVipByOils(date, start, end, ArryToListUtil.format(station),oils,ArryToListUtil.formatInteger(week));
			list2=oilService.exportAllAndVipByOils(date, start, end, ArryToListUtil.format(station),oils,ArryToListUtil.formatInteger(week));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryAllAndVipByOils(date, start,end,stationid,oils,ArryToListUtil.formatInteger(week));
			list2=oilService.exportAllAndVipByOils(date, start,end,stationid,oils,ArryToListUtil.formatInteger(week));
		}
		for (OilAndVip oilAndVip : list) {
			oilAndVip.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("day", "日期");
		titleMap.put("minutes", "时间");
		titleMap.put("stationID", "油站编号");

		titleMap.put("oilLitre", "总销售升数");
		titleMap.put("oilNumber", "总销售笔数");
		titleMap.put("avgLitre", "整体平均单笔销售升数");

		titleMap.put("vipOilLitre", "会员消费升数");
		titleMap.put("vipOilNumber", "会员消费笔数");
		titleMap.put("vipAvgLitre", "会员平均单笔销售升数");

		titleMap.put("notVipOilLitre", "非会员消费升数");
		titleMap.put("notVipOilNumber", "非会员消费笔数");
		titleMap.put("notVipAvgLitre", "非会员平均单笔销售升数");





		String sheetName = "分油品销量信息";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
			try {
				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}
	/**
	 * 油品信息导出
	 * @param response
	 * @param citys
	 * @param regions
	 * @param sales
	 * @param gasoline
	 * @param locs
	 * @param openDate
	 * @param type
	 * @param station
	 * @param start
	 * @param end
	 * @param date
	 * 暂时放置，没使用queryzhanbi
	 */
	@ResponseBody
	@RequestMapping("/exportOils")
	public void exportOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,@RequestParam(required=false,value="week")Integer [] week,
			Date start,Date end,String date){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"燃油销售整体情况.xls", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		OutputStream os=null;
        try {
			os= new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        /**
         * 
         * 获取需要导出的集合信息
         * 将要更换成分油站的
         */
        List<OilAndVip> list = new ArrayList<OilAndVip>();
        List<OilAndVip> list2 = new ArrayList<OilAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryAllAndVip(date, start, end, ArryToListUtil.format(station),ArryToListUtil.formatInteger(week));
			list2=oilService.exportAllAndVip(date, start, end, ArryToListUtil.format(station),ArryToListUtil.formatInteger(week));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryAllAndVip(date, start,end,stationid,ArryToListUtil.formatInteger(week));
			list2=oilService.exportAllAndVip(date, start, end, stationid,ArryToListUtil.formatInteger(week));
		}
		for (OilAndVip oilAndVip : list) {
			oilAndVip.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("day", "日期");
		titleMap.put("minutes", "时间");
		titleMap.put("oilLitre", "总销售升数");
		titleMap.put("vipRatio", "会员销量占比");
		titleMap.put("notVipRatio", "非会员销量占比");
		titleMap.put("oilMoney", "总销售额");
		titleMap.put("oilNumber", "总销售笔数");
		titleMap.put("avgLitre", "整体平均单笔销售升数");

		
		titleMap.put("vipOilMoney", "会员销售额");

		titleMap.put("vipOilLitre", "会员消费升数");
		titleMap.put("vipOilNumber", "会员消费笔数");
		titleMap.put("vipAvgLitre", "会员平均单笔销售升数");
		
		titleMap.put("notVipOilMoney", "非会员销售额");
		titleMap.put("notVipOilLitre", "非会员消费升数");
		titleMap.put("notVipOilNumber", "非会员消费笔数");
		titleMap.put("notVipAvgLitre", "非会员平均单笔销售升数");
		titleMap.put("stationID", "油站编号");
		String sheetName = "油品销量信息";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
			try {
				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryByOils")
	@ResponseBody
	public Map<String, List> queryByOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			@RequestParam(required=false,value="week[]")Integer [] week,
			Date start,Date end,String date){
		List<Oilb> list = new ArrayList<Oilb>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryByOils(date, start,end,ArryToListUtil.format(station),ArryToListUtil.formatInteger(week));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryByOils(date, start,end,stationid,ArryToListUtil.formatInteger(week));
		}
		List<String> allName = oilService.queryAllName();
		List<String> dates = new ArrayList<String>();
		List<Double> litre92 = new ArrayList<Double>();
		List<Double> litre95 = new ArrayList<Double>();
		List<Double> litre97 = new ArrayList<Double>();
		List<Double> litre0 = new ArrayList<Double>();
		List<Double> litre10 = new ArrayList<Double>();
		List<Double> litre20 = new ArrayList<Double>();
		List<Double> litre80 = new ArrayList<Double>();
		List<Double> litre98 = new ArrayList<Double>();
		List<Double> litre35 = new ArrayList<Double>();


		Map<String,List> map = new HashMap<String,List>(16);
		if(list!=null&&list.size()!=0){
			for (Oilb oil : list) {
				dates.add(oil.getDate());
				litre0.add(DoubleFormatUtil.format(oil.getLitre0()));
				litre10.add(DoubleFormatUtil.format(oil.getLitre10()));
				litre20.add(DoubleFormatUtil.format(oil.getLitre20()));
				litre92.add(DoubleFormatUtil.format(oil.getLitre92()));
				litre95.add(DoubleFormatUtil.format(oil.getLitre95()));
				litre97.add(DoubleFormatUtil.format(oil.getLitre97()));
				litre98.add(DoubleFormatUtil.format(oil.getLitre98()));
				litre80.add(DoubleFormatUtil.format(oil.getLitre80()));
				litre35.add(DoubleFormatUtil.format(oil.getLitre35()));

			}
		}else {
			dates.add("无数据");
		}
		map.put("dates", dates);
		map.put("allName", allName);
		map.put("litre0",litre0 );
		map.put("litre10", litre10);
		map.put("litre20", litre20);
		map.put("litre92", litre92);
		map.put("litre95", litre95);
		map.put("litre97", litre97);
		map.put("litre98", litre98);
		map.put("litre80", litre80);
		map.put("litre35", litre35);
		return map;
	}
	/**
	 * 燃油分油品导出，进行分站
	 * @param response
	 * @param citys
	 * @param regions
	 * @param sales
	 * @param gasoline
	 * @param locs
	 * @param openDate
	 * @param type
	 * @param station
	 * @param start
	 * @param end
	 * @param date
	 */
	@ResponseBody
	@RequestMapping("/exportByOils")
	public void exportByOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			@RequestParam(required=false,value="week")Integer [] week,
			Date start,Date end,String date){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"燃油分油品销售情况.xls", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		OutputStream os=null;
        try {
			os= new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //获取需要导出的集合信息
		HSSFWorkbook excelExport = null;
		try {
			List<OilData> list = new ArrayList<OilData>();
			List<OilData> list2 = new ArrayList<OilData>();
			if(ArryToListUtil.format(station)!=null){
				list=oilService.queryByOilData(date, start,end,ArryToListUtil.format(station),ArryToListUtil.formatInteger(week));
				list2=oilService.exportByOilData(date, start,end,ArryToListUtil.format(station),ArryToListUtil.formatInteger(week));
			}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
				List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions),
						ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) ,
						ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
				List<String> stationid = new ArrayList<String>();
				if(queryStationBy!=null){
					for (Station station2 : queryStationBy) {
						stationid.add(station2.getId());
					}
				}
				list=oilService.queryByOilData(date, start,end,stationid,ArryToListUtil.formatInteger(week));
				list2=oilService.exportByOilData(date, start,end,stationid,ArryToListUtil.formatInteger(week));
			}
			list.addAll(list2);
			Map<String,String> titleMap = new LinkedHashMap<String,String>();
			titleMap.put("day", "日期");
			titleMap.put("minutes", "时间");
			titleMap.put("stationID", "油站编号");
			titleMap.put("viplitre0", "会员0#柴油销量");
			titleMap.put("viplitre10", "会员-10#柴油销量");
			titleMap.put("viplitre20", "会员-20#柴油销量");
			titleMap.put("viplitre92", "会员92#汽油销量");
			titleMap.put("viplitre95", "会员95#汽油销量");
			titleMap.put("viplitre97", "会员97#汽油销量");
			titleMap.put("viplitre98", "会员98#汽油销量");
			titleMap.put("viplitre80", "会员80#汽油销量");
			titleMap.put("viplitre35", "会员-35#销量");

			titleMap.put("notviplitre0", "非会员0#柴油销量");
			titleMap.put("notviplitre10", "非会员-10#柴油销量");
			titleMap.put("notviplitre20", "非会员-20#柴油销量");
			titleMap.put("notviplitre92", "非会员92#汽油销量");
			titleMap.put("notviplitre95", "非会员95#汽油销量");
			titleMap.put("notviplitre97", "非会员97#汽油销量");
			titleMap.put("notviplitre98", "非会员98#汽油销量");
			titleMap.put("notviplitre80", "非会员80#汽油销量");
			titleMap.put("notviplitre35", "非会员-35#销量");

			titleMap.put("litre0", "全部0#柴油销量");
			titleMap.put("litre10", "全部-10#柴油销量");
			titleMap.put("litre20", "全部-20#柴油销量");
			titleMap.put("litre92", "全部92#汽油销量");
			titleMap.put("litre95", "全部95#汽油销量");
			titleMap.put("litre97", "全部97#汽油销量");
			titleMap.put("litre98", "全部98#汽油销量");
			titleMap.put("litre80", "全部80#汽油销量");
			titleMap.put("litre35", "全部-35#销量");


			titleMap.put("vipnumber0", "会员0#柴油销售笔数");
			titleMap.put("vipnumber10", "会员-10#柴油销售笔数");
			titleMap.put("vipnumber20", "会员-20#柴油销售笔数");
			titleMap.put("vipnumber92", "会员92#汽油销售笔数");
			titleMap.put("vipnumber95", "会员95#汽油销售笔数");
			titleMap.put("vipnumber97", "会员97#汽油销售笔数");
			titleMap.put("vipnumber98", "会员98#汽油销售笔数");
			titleMap.put("vipnumber80", "会员80#汽油销售笔数");
			titleMap.put("vipnumber35", "会员-35#销售笔数");

			titleMap.put("notvipnumber0", "非会员0#柴油销售笔数");
			titleMap.put("notvipnumber10", "非会员-10#柴油销售笔数");
			titleMap.put("notvipnumber20", "非会员-20#柴油销售笔数");
			titleMap.put("notvipnumber92", "非会员92#汽油销售笔数");
			titleMap.put("notvipnumber95", "非会员95#汽油销售笔数");
			titleMap.put("notvipnumber97", "非会员97#汽油销售笔数");
			titleMap.put("notvipnumber98", "非会员98#汽油销售笔数");
			titleMap.put("notvipnumber80", "非会员80#汽油销售笔数");
			titleMap.put("notvipnumber35", "非会员-35#销售笔数");

			titleMap.put("number0", "全部0#柴油销售笔数");
			titleMap.put("number10", "全部-10#柴油销售笔数");
			titleMap.put("number20", "全部-20#柴油销售笔数");
			titleMap.put("number92", "全部92#汽油销售笔数");
			titleMap.put("number95", "全部95#汽油销售笔数");
			titleMap.put("number97", "全部97#汽油销售笔数");
			titleMap.put("number98", "全部98#汽油销售笔数");
			titleMap.put("number80", "全部80#汽油销售笔数");
			titleMap.put("number35", "全部-35#销售笔数");

			String sheetName = "分油品销售";
			//应该是要返回一个hsswork然后os响应出来
			excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
			try {
				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}
	@RequestMapping("/queryAllName")
	@ResponseBody
	public List<String> queryAllName(){
		List<String> list = oilService.queryAllName();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryzhanbi")
	public List<DataPack> queryzhanbi(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end){
		
		List<Oil> list = new ArrayList<Oil>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryzhanbi( start,end,ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(openDate),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryzhanbi( start,end,stationid);
		}
		
		List<DataPack> datas = new ArrayList<DataPack>();
		if(list!=null&&list.size()!=0){//有数据
			for (Oil oil : list) {
				DataPack dataPack = new DataPack(oil.getOils(), oil.getOilLitre());
				datas.add(dataPack);
				if(oil.getOils().equals("")){

				}else if(oil.getOils().equals("0#")){
					dataPack.setItemStyle(new ItemStyle("#7F7F7F"));
				}else if(oil.getOils().equals("-10#")){
					dataPack.setItemStyle(new ItemStyle("#A6A6A6"));
				}else if(oil.getOils().equals("-20#")){
					dataPack.setItemStyle(new ItemStyle("#595959"));
				}else if(oil.getOils().equals("92#")){
					dataPack.setItemStyle(new ItemStyle("#DD1D21"));
				}else if(oil.getOils().equals("95#")){
					dataPack.setItemStyle(new ItemStyle("#008433"));
				}else if(oil.getOils().equals("97#")){
					dataPack.setItemStyle(new ItemStyle("#009EB4"));
				}else if(oil.getOils().equals("98#")){
					dataPack.setItemStyle(new ItemStyle("#003C88"));
				}else if(oil.getOils().equals("80#")){
					dataPack.setItemStyle(new ItemStyle("#BA95BE"));
				}
			}
		}else {//条件内无数据
			DataPack dataPack = new DataPack("无数据", 0.0);
			datas.add(dataPack);
		}
		return datas;
	}
	//dashboard上油品数据的查询，本月累计销量，今年累计销量，目标完成率
	//近七日的销量，当日的汽油柴油销量，油品销量占比
	@RequiresAuthentication
	@RequiresPermissions("service")
	@RequestMapping("/queryDashBoardCheng")
	@ResponseBody
	@Cacheable(value="oilCheng")
	public Map<String, Object> queryDashboardCheng(){
		DecimalFormat df = new DecimalFormat("#,###.##");
		DecimalFormat df0 = new DecimalFormat("#,###");
		List<String> types=new ArrayList<String>();
		types.add("RBA");
		List<String> citys=new ArrayList<String>();
		citys.add("承德");
		/**
		 * 先求出符合条件的油站ID
		 */
        String updateTime = heartService.queryTime("res_oil");
        List<String> stationid=new ArrayList<String>();
			List<Station> queryStationBy = stationService.queryStationBy(citys,null,null,null, 
					null,null,types,stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			if(stationid.size()==0){
				stationid=null;
			}
		Double monthLitre=0.0;//当月销量
		Double yearLitre=0.0;//今年销量
		List<String> date=new ArrayList<String>();//七天的时间集合
		List<Double> litre=new ArrayList<Double>();;//七天的销售量集合
		List<DataPack> dayzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<DataPack> monthzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<Double> dayRate=new ArrayList<Double>();
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");//一个月的销量
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {//遍历出来的就是当月的销售情况
				monthLitre=oil.getOilLitre();
			}
		}
		List<Oil> queryOils2 = oilService.queryOils("year", DateFormatUtils.getYearStart(), new Date(), stationid, "all");
		if(queryOils2!=null){
			for (Oil oil : queryOils2) {
				yearLitre=oil.getOilLitre();
			}
		}
		Double queryTargetByYear = targetService.queryTargetByYear(stationid);
		Double queryRate = targetService.queryRate(stationid);//今年所有油站累计的销售完成率
		Double queryTargetByMonth = targetService.queryTargetByMonth(stationid);//本月所有油站的目标和
		Double rateMonthDouble=0.0;
		if(monthLitre!=null&&queryTargetByMonth!=null){
			rateMonthDouble=monthLitre/queryTargetByMonth;//本月的完成率
		}
		List<DataPack> monthTarget = new ArrayList<DataPack>();
		monthTarget.add(new DataPack("本月油品销量",monthLitre));
		if(monthLitre!=null&&queryTargetByMonth!=null){
			if(queryTargetByMonth<monthLitre){
				monthTarget.add(new DataPack("本月未完成销量",0.0));
			}else {
				monthTarget.add(new DataPack("本月未完成销量",queryTargetByMonth-monthLitre));
			}
		}else{
			monthTarget.add(new DataPack("本月未完成销量",0.0));
		}
		List<DataPack> yearTarget = new ArrayList<DataPack>();
		yearTarget.add(new DataPack("今年油品销量", yearLitre));
		if(queryTargetByYear!=null&&yearLitre!=null){
			if (queryTargetByYear<yearLitre) {
				yearTarget.add(new DataPack("今年未完成油品销量", 0.0));
			}else {
				yearTarget.add(new DataPack("今年未完成油品销量", queryTargetByYear-yearLitre));
			}
		}else {
			yearTarget.add(new DataPack("今年未完成油品销量", 0.0));
		}
		//本月每天的目标
		Double dayTarget=0.0;
		if(queryTargetByMonth!=null){
			dayTarget=queryTargetByMonth/DateFormatUtils.getCurrentMonthDay();
		}
		//求的是近一周的销售量
		String dayAmount="0.0";
		String daytr="0.0%";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Oil> queryOils3 = oilService.queryOils("day", DateFormatUtils.getWeekStart(), new Date(), stationid, "all");
		if(queryOils3!=null){
			for (Oil oil : queryOils3) {
				date.add(oil.getMinutes());
				litre.add(oil.getOilLitre());
				dayRate.add(DoubleFormatUtil.format(oil.getOilLitre()/dayTarget)*100);
				if(simpleDateFormat.format(new Date()).equals(oil.getMinutes())){
					dayAmount=df0.format(oil.getOilLitre());
					if(oil.getOilLitre()!=null&&dayTarget!=null){
						daytr=df0.format(oil.getOilLitre()*100/dayTarget)+"%";
					}
					
				}
			}
		}
		//当日占比图
		List<Oil> queryzhanbi = oilService.queryzhanbi(DateFormatUtils.getDayStart(), new Date(), stationid);
		if(queryzhanbi!=null){
			Double otherDouble=0.0;
			for (Oil oil : queryzhanbi) {
				if(oil.getOils().equals("92#")||oil.getOils().equals("95#")){
					dayzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
				}else {
					otherDouble+=oil.getOilLitre();
				}
			}
			dayzhanbi.add(new DataPack("其他", DoubleFormatUtil.format(otherDouble)));
		}
		//本月占比图
		List<Oil> queryzhanbi2 = oilService.queryzhanbi(DateFormatUtils.getMonthStart(), new Date(), stationid);
		if(queryzhanbi2!=null){
			Double otherDouble=0.0;
			for (Oil oil : queryzhanbi2) {
				if(oil.getOils().equals("92#")||oil.getOils().equals("95#")){
					monthzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
				}else {
					otherDouble+=oil.getOilLitre();
				}
			}
			monthzhanbi.add(new DataPack("其他", DoubleFormatUtil.format(otherDouble)));
		}
		List<String> gasoline = new ArrayList<String>();//汽油
		List<String> diesel = new ArrayList<String>();//柴油
		gasoline.add("92#");
		gasoline.add("95#");
		diesel.add("0#");
		diesel.add("-10#");
		diesel.add("-20#");
		//当日柴油汽油销售量
		Oil queryOilsByTypegasoline = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), stationid, gasoline, "all");//汽油
		Oil queryOilsByTypediesel = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), stationid, diesel, "all");//柴油
		//本月柴油汽油销售量
		Oil queryOilsByTypegasolinemonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), stationid, gasoline, "all");//汽油
		Oil queryOilsByTypedieselmonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), stationid, diesel, "all");//柴油
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthLitre", df.format(monthLitre/1000000)+"ML");
		map.put("yearLitre", df.format(yearLitre/1000000)+"ML");
		map.put("monthRate",df0.format(rateMonthDouble*100)+"%");
		map.put("yearRate", df0.format(queryRate*100)+"%");
		map.put("date", date);
		map.put("litre", litre);
		if(queryOilsByTypegasoline!=null){
			map.put("dayGasoline",DoubleFormatUtil.formatZero(queryOilsByTypegasoline.getOilLitre()));
			map.put("dayGasolineString",df0.format(queryOilsByTypegasoline.getOilLitre()));
		}else {
			map.put("dayGasoline",0.0);
			map.put("dayGasolineString",0.0);
		}
		if(queryOilsByTypediesel!=null){
			map.put("dayDiesel",DoubleFormatUtil.formatZero(queryOilsByTypediesel.getOilLitre()));
			map.put("dayDieselString",df0.format(queryOilsByTypediesel.getOilLitre()));
		}else {
			map.put("dayDiesel",0);
			map.put("dayDieselString",0);
		}
		if(queryOilsByTypegasolinemonth!=null){
			map.put("monthGasoline",DoubleFormatUtil.format(queryOilsByTypegasolinemonth.getOilLitre()/1000));
			map.put("monthGasolineString",df.format(queryOilsByTypegasolinemonth.getOilLitre()/1000));
		}else {
			map.put("monthGasoline",0);
			map.put("monthGasolineString",0);
		}
		if(queryOilsByTypedieselmonth!=null){
			map.put("monthDiesel",DoubleFormatUtil.format(queryOilsByTypedieselmonth.getOilLitre()/1000));
			map.put("monthDieselString",df.format(queryOilsByTypedieselmonth.getOilLitre()/1000));
		}else {
			map.put("monthDiesel",0);
			map.put("monthDieselString",0);
		}
		map.put("dayzhanbi", dayzhanbi);
		map.put("monthzhanbi", monthzhanbi);
		map.put("updateTime", updateTime);
		map.put("dayRate", dayRate);
		map.put("dayAmount", dayAmount+"L");
		map.put("daytr", daytr);
		map.put("monthTarget", monthTarget);
		map.put("yearTarget", yearTarget);
		return map;
	}
	@RequiresAuthentication
	@RequiresPermissions("service")
	@RequestMapping("/queryDashBoard")
	@ResponseBody
	@Cacheable(value="oil")
	public Map<String, Object> queryDashboard(){
		DecimalFormat df = new DecimalFormat("#,###.##");
		DecimalFormat df0 = new DecimalFormat("#,###");
		List<String> types=new ArrayList<String>();
		types.add("RBA");
		List<String> citys=new ArrayList<String>();
		citys.add("北京");
        String updateTime = heartService.queryTime("res_oil");

        /**
		 * 先求出符合条件的油站ID
		 */
		List<String> stationid=new ArrayList<String>();
			List<Station> queryStationBy = stationService.queryStationBy(citys,null,null,null, 
					null,null,types,stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			if(stationid.size()==0){
				stationid=null;
			}
		Double monthLitre=0.0;//当月销量
		Double yearLitre=0.0;//今年销量
		List<String> date=new ArrayList<String>();//七天的时间集合
		List<Double> litre=new ArrayList<Double>();;//七天的销售量集合
		List<DataPack> dayzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<DataPack> monthzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<Double> dayRate=new ArrayList<Double>();
		
			
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");//一个月的销量
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {//遍历出来的就是当月的销售情况
				monthLitre=oil.getOilLitre();
			}
		}
		List<Oil> queryOils2 = oilService.queryOils("year", DateFormatUtils.getYearStart(), new Date(), stationid, "all");
		if(queryOils2!=null){
			for (Oil oil : queryOils2) {
				yearLitre=oil.getOilLitre();
			}
		}
		/**
		 * 目标达成率，传参是所选油站
		 */
		Double queryTargetByYear = targetService.queryTargetByYear(stationid);
		Double queryRate = targetService.queryRate(stationid);
		Double queryTargetByMonth = targetService.queryTargetByMonth(stationid);
		Double rateMonthDouble=monthLitre/queryTargetByMonth;//本月的完成率
		
		List<DataPack> monthTarget = new ArrayList<DataPack>();
		monthTarget.add(new DataPack("本月油品销量",monthLitre));
		if(queryTargetByMonth<monthLitre){
			monthTarget.add(new DataPack("本月未完成销量",0.0));
		}else {
			monthTarget.add(new DataPack("本月未完成销量",queryTargetByMonth-monthLitre));
		}
		List<DataPack> yearTarget = new ArrayList<DataPack>();
		yearTarget.add(new DataPack("今年油品销量", yearLitre));
		if (queryTargetByYear<yearLitre) {
			yearTarget.add(new DataPack("今年未完成油品销量", 0.0));
		}else {
			yearTarget.add(new DataPack("今年未完成油品销量", queryTargetByYear-yearLitre));
		}
		//本月每天的目标
		Double dayTarget=queryTargetByMonth/DateFormatUtils.getCurrentMonthDay();
		//求的是近一周的销售量
		String dayAmount="0.0";
		String daytr="0.0%";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Oil> queryOils3 = oilService.queryOils("day", DateFormatUtils.getWeekStart(), new Date(), stationid, "all");
		if(queryOils3!=null){
			for (Oil oil : queryOils3) {
				date.add(oil.getMinutes());
				litre.add(oil.getOilLitre());
				dayRate.add(DoubleFormatUtil.format(oil.getOilLitre()/dayTarget)*100);
				if(simpleDateFormat.format(new Date()).equals(oil.getMinutes())){
					dayAmount=df0.format(oil.getOilLitre());
					if(oil.getOilLitre()!=null&&dayTarget!=null){
						daytr=df0.format(oil.getOilLitre()*100/dayTarget)+"%";
					}
					
				}
			}
		}
		//当日占比图
		List<Oil> queryzhanbi = oilService.queryzhanbi(DateFormatUtils.getDayStart(), new Date(), stationid);
		if(queryzhanbi!=null){
			Double otherDouble=0.0;
			for (Oil oil : queryzhanbi) {
				if(oil.getOils().equals("92#")||oil.getOils().equals("95#")){
					dayzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
				}else {
					otherDouble+=oil.getOilLitre();
				}
			}
			dayzhanbi.add(new DataPack("其他", DoubleFormatUtil.format(otherDouble)));
		}
		//本月占比图
		List<Oil> queryzhanbi2 = oilService.queryzhanbi(DateFormatUtils.getMonthStart(), new Date(), stationid);
		if(queryzhanbi2!=null){
			Double otherDouble=0.0;
			for (Oil oil : queryzhanbi2) {
				if(oil.getOils().equals("92#")||oil.getOils().equals("95#")){
					monthzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
				}else {
					otherDouble+=oil.getOilLitre();
				}
			}
			monthzhanbi.add(new DataPack("其他", DoubleFormatUtil.format(otherDouble)));
		}
		List<String> gasoline = new ArrayList<String>();//汽油
		List<String> diesel = new ArrayList<String>();//柴油
		gasoline.add("92#");
		gasoline.add("95#");
		diesel.add("0#");
		diesel.add("-10#");
		diesel.add("-20#");
		//当日柴油汽油销售量
		Oil queryOilsByTypegasoline = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), stationid, gasoline, "all");//汽油
		Oil queryOilsByTypediesel = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), stationid, diesel, "all");//柴油
		//本月柴油汽油销售量
		Oil queryOilsByTypegasolinemonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), stationid, gasoline, "all");//汽油
		Oil queryOilsByTypedieselmonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), stationid, diesel, "all");//柴油
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthLitre", df.format(monthLitre/1000000)+"ML");
		map.put("yearLitre", df.format(yearLitre/1000000)+"ML");
		map.put("monthRate",df0.format(rateMonthDouble*100)+"%");
		map.put("yearRate", df0.format(queryRate*100)+"%");
		map.put("date", date);
		map.put("litre", litre);
		if(queryOilsByTypegasoline!=null){
			map.put("dayGasoline",DoubleFormatUtil.formatZero(queryOilsByTypegasoline.getOilLitre()));
			map.put("dayGasolineString",df0.format(queryOilsByTypegasoline.getOilLitre()));
		}else {
			map.put("dayGasoline",0.0);
			map.put("dayGasolineString",0.0);
		}
		if(queryOilsByTypediesel!=null){
			map.put("dayDiesel",DoubleFormatUtil.formatZero(queryOilsByTypediesel.getOilLitre()));
			map.put("dayDieselString",df0.format(queryOilsByTypediesel.getOilLitre()));
		}else {
			map.put("dayDiesel",0);
			map.put("dayDieselString",0);
		}
		if(queryOilsByTypegasolinemonth!=null){
			map.put("monthGasoline",DoubleFormatUtil.format(queryOilsByTypegasolinemonth.getOilLitre()/1000));
			map.put("monthGasolineString",df.format(queryOilsByTypegasolinemonth.getOilLitre()/1000));
		}else {
			map.put("monthGasoline",0);
			map.put("monthGasolineString",0);
		}
		if(queryOilsByTypedieselmonth!=null){
			map.put("monthDiesel",DoubleFormatUtil.format(queryOilsByTypedieselmonth.getOilLitre()/1000));
			map.put("monthDieselString",df.format(queryOilsByTypedieselmonth.getOilLitre()/1000));
		}else {
			map.put("monthDiesel",0);
			map.put("monthDieselString",0);
		}
		map.put("dayzhanbi", dayzhanbi);
		map.put("updateTime", updateTime);
		map.put("monthzhanbi", monthzhanbi);
		map.put("dayRate", dayRate);
		map.put("dayAmount", dayAmount+"L");
		map.put("daytr", daytr);
		map.put("monthTarget", monthTarget);
		map.put("yearTarget", yearTarget);
		return map;
	}
	@RequiresAuthentication
	@RequiresPermissions("service")
	@RequestMapping("/queryDashBoardByStation")
	@ResponseBody
	@Cacheable(value="oil")
	public Map<String, Object> queryDashboardByStation(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station){
		DecimalFormat df = new DecimalFormat("#,###.##"); 
		DecimalFormat df0 = new DecimalFormat("#,###"); 
		List<String> stationid=new ArrayList<String>();
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
        String updateTime = heartService.queryTime("res_oil");
        Double monthLitre=0.0;//当月销量
		Double yearLitre=0.0;//今年销量
		List<String> date=new ArrayList<String>();//七天的时间集合
		List<Double> litre=new ArrayList<Double>();;//七天的销售量集合
		List<DataPack> dayzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<DataPack> monthzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<Double> dayRate=new ArrayList<Double>();
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");//一个月的销量
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {//遍历出来的就是当月的销售情况
				monthLitre=oil.getOilLitre();
			}
		}
		
		List<Oil> queryOils2 = oilService.queryOils("year", DateFormatUtils.getYearStart(), new Date(), stationid, "all");
		if(queryOils2!=null){
			for (Oil oil : queryOils2) {
				yearLitre=oil.getOilLitre();
			}
		}
		Double queryTargetByYear = targetService.queryTargetByYear(stationid);
		Double queryRate = targetService.queryRate(stationid);//今年所有油站累计的销售完成率
		Double queryTargetByMonth = targetService.queryTargetByMonth(stationid);//本月所有油站的目标和
		
		List<DataPack> monthTarget = new ArrayList<DataPack>();
		monthTarget.add(new DataPack("本月油品销量",monthLitre));
		if(queryTargetByMonth!=null&&monthLitre!=null){
			if(queryTargetByMonth>monthLitre){
				monthTarget.add(new DataPack("本月未完成销量",queryTargetByMonth-monthLitre));
			}else {
				monthTarget.add(new DataPack("本月未完成销量",0.0));
			}
		}else {
			monthTarget.add(new DataPack("本月未完成销量",0.0));
		}
		
		List<DataPack> yearTarget = new ArrayList<DataPack>();
		yearTarget.add(new DataPack("今年油品销量", yearLitre));
		if(queryTargetByYear!=null&&yearLitre!=null){
			if(queryTargetByYear>yearLitre){
				yearTarget.add(new DataPack("今年未完成油品销量", queryTargetByYear-yearLitre));
			}else {
				yearTarget.add(new DataPack("今年未完成油品销量", 0.0));
			}
		}else {
			yearTarget.add(new DataPack("今年未完成油品销量", 0.0));
		}
		
		Double rateMonthDouble=0.0;//初始赋值为0
		Double dayTarget=0.0;
		if(queryRate!=null&&queryTargetByMonth!=null){
			rateMonthDouble=monthLitre/queryTargetByMonth;//本月的完成率
			dayTarget=queryTargetByMonth/DateFormatUtils.getCurrentMonthDay();
		}
		/**
		 * 本月每天的目标
		 * 求的是近一周的销售量
		 */
		String dayAmount="0.0";
		String daytr="0.0%";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Oil> queryOils3 = oilService.queryOils("day", DateFormatUtils.getWeekStart(), new Date(), stationid, "all");
		if(queryOils3!=null){
			for (Oil oil : queryOils3) {
				date.add(oil.getMinutes());
				litre.add(oil.getOilLitre());
				dayRate.add(DoubleFormatUtil.format(oil.getOilLitre()/dayTarget)*100);
				if(simpleDateFormat.format(new Date()).equals(oil.getMinutes())){
					dayAmount=df0.format(oil.getOilLitre());
					if(oil.getOilLitre()!=null&&dayTarget!=null){
						daytr=df0.format(oil.getOilLitre()*100/dayTarget)+"%";
					}
				}
			}
		}
		//当日占比图
		List<Oil> queryzhanbi = oilService.queryzhanbi(DateFormatUtils.getDayStart(), new Date(), stationid);
		if(queryzhanbi!=null){
			Double otherDouble=0.0;
			for (Oil oil : queryzhanbi) {
				if(oil.getOils().equals("92#")||oil.getOils().equals("95#")){
					dayzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
				}else {
					otherDouble+=oil.getOilLitre();
				}
			}
			dayzhanbi.add(new DataPack("其他",DoubleFormatUtil.format(otherDouble)));
		}
		//本月占比图
		List<Oil> queryzhanbi2 = oilService.queryzhanbi(DateFormatUtils.getMonthStart(), new Date(), stationid);
		if(queryzhanbi2!=null){
			Double otherDouble=0.0;
			for (Oil oil : queryzhanbi2) {
				if(oil.getOils().equals("92#")||oil.getOils().equals("95#")){
					monthzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
				}else {
					otherDouble+=oil.getOilLitre();
				}
			}
			monthzhanbi.add(new DataPack("其他", DoubleFormatUtil.format(otherDouble)));
		}
		List<String> gasolines = new ArrayList<String>();//汽油
		List<String> diesel = new ArrayList<String>();//柴油
		gasolines.add("92#");
		gasolines.add("95#");
		gasolines.add("97#");
		diesel.add("0#");
		diesel.add("-10#");
		diesel.add("-20#");
		diesel.add("-35#");
		//当日柴油汽油销售量
		Oil queryOilsByTypegasoline = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), stationid, gasolines, "all");//汽油
		Oil queryOilsByTypediesel = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), stationid, diesel, "all");//柴油
		//本月柴油汽油销售量
		Oil queryOilsByTypegasolinemonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), stationid, gasolines, "all");//汽油
		Oil queryOilsByTypedieselmonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), stationid, diesel, "all");//柴油
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthLitre", df.format(monthLitre/1000000)+"ML");
		map.put("yearLitre",df.format(yearLitre/1000000)+"ML");
		map.put("monthRate",df0.format(rateMonthDouble*100)+"%");
		map.put("yearRate",df0.format(queryRate*100)+"%");
		map.put("date", date);
		map.put("litre", litre);
		if(queryOilsByTypegasoline!=null){
			map.put("dayGasoline",DoubleFormatUtil.formatZero(queryOilsByTypegasoline.getOilLitre()));
			map.put("dayGasolineString",df0.format(queryOilsByTypegasoline.getOilLitre()));
		}else {
			map.put("dayGasoline",0);
			map.put("dayGasolineString",0);
		}
		if(queryOilsByTypediesel!=null){
			map.put("dayDiesel",DoubleFormatUtil.formatZero(queryOilsByTypediesel.getOilLitre()));
			map.put("dayDieselString",df0.format(queryOilsByTypediesel.getOilLitre()));
		}else {
			map.put("dayDiesel",0);
			map.put("dayDieselString",0);
		}
		if(queryOilsByTypegasolinemonth!=null){
			map.put("monthGasoline",DoubleFormatUtil.format(queryOilsByTypegasolinemonth.getOilLitre()/1000));
			map.put("monthGasolineString",df.format(queryOilsByTypegasolinemonth.getOilLitre()/1000));
		}else {
			map.put("monthGasoline",0);
			map.put("monthGasolineString",0);
		}
		if(queryOilsByTypedieselmonth!=null){
			map.put("monthDiesel",DoubleFormatUtil.format(queryOilsByTypedieselmonth.getOilLitre()/1000));
			map.put("monthDieselString",df.format(queryOilsByTypedieselmonth.getOilLitre()/1000));
		}else {
			map.put("monthDiesel",0);
			map.put("monthDieselString",0);
		}
		map.put("yearTarget", yearTarget);
		map.put("monthTarget",monthTarget);
		map.put("updateTime",updateTime);
		//當日銷量
		map.put("dayAmount", dayAmount+"L");
		//當日達成率
		map.put("daytr", daytr);
		map.put("dayzhanbi", dayzhanbi);
		map.put("monthzhanbi", monthzhanbi);
		map.put("dayRate", dayRate);
		return map;
	}
}
