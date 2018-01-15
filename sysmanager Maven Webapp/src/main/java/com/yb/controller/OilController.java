package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.Oil;
import com.yb.entity.OilAndVip;
import com.yb.entity.Oilb;
import com.yb.entity.Station;
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
public class OilController {

	@Resource
	private OilService oilService;
	@Resource
	private StationService stationService;
	@Resource
	private TargetService targetService;
	//暂时放置，会员的跟总的是分开放的
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryOils")
	@ResponseBody
	public Map<String, List> queryOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date,String people){
		List<Oil> list = new ArrayList<Oil>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryOils(date, start,end,ArryToListUtil.format(station),people);
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryOils(date, start,end,stationid,people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Double> numbers = new ArrayList<Double>();
		List<Double> avgAmounts = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Oil oil : list) {
				dates.add(oil.getMinutes());
				amounts.add(DoubleFormatUtil.format(oil.getOilLitre()/1000));
				avgAmounts.add(DoubleFormatUtil.format(oil.getOilMoney()));
				numbers.add(oil.getOilNumber());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
			avgAmounts.add(0.0);
			numbers.add(0.0);
		}
		map.put("dates", dates);
		map.put("amounts", amounts);
		map.put("avgAmounts", avgAmounts);
		map.put("numbers", numbers);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryAndVip")
	@ResponseBody
	public Map<String, List> queryAndVip(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date,String people){
		List<OilAndVip> list = new ArrayList<OilAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryAllAndVip(date, start, end, ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryAllAndVip(date, start,end,stationid);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgAmounts = new ArrayList<Double>();
		List<Double> vipamounts = new ArrayList<Double>();
		List<Integer> vipnumbers = new ArrayList<Integer>();
		List<Double> vipavgAmounts = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (OilAndVip oilAndVip : list) {
				dates.add(oilAndVip.getDate());
				amounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getOilLitre())/1000));
				avgAmounts.add(DoubleFormatUtil.format(oilAndVip.getAvgLitre()));
				numbers.add(oilAndVip.getOilNumber());
				vipamounts.add(DoubleFormatUtil.format(DoubleFormatUtil.format(oilAndVip.getVipOilLitre())/1000));
				vipavgAmounts.add(DoubleFormatUtil.format(oilAndVip.getVipAvgLitre()));
				vipnumbers.add(oilAndVip.getVipOilNumber());
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
		return map;
	}
	
	//油品信息导出
	@ResponseBody
	@RequestMapping("/exportOils")
	public void exportOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,@RequestParam(required=false,value="station")String [] station,
			Date start,Date end,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("燃油销售整体情况.xls", "UTF-8");
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
        List<OilAndVip> list = new ArrayList<OilAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryAllAndVip(date, start, end, ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryAllAndVip(date, start,end,stationid);
		}
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("oilNumber", "总销售笔数");
		titleMap.put("oilLitre", "总销售升数");
		titleMap.put("avgLitre", "整体平均单笔销售升数");
		titleMap.put("vipOilNumber", "会员消费笔数");
		titleMap.put("vipOilLitre", "会员消费升数");
		titleMap.put("vipAvgLitre", "会员平均单笔销售升数");
		String sheetName = "油品销量信息";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryByOils")
	@ResponseBody
	public Map<String, List> queryByOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date){
		
		List<Oilb> list = new ArrayList<Oilb>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryByOils(date, start,end,ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryByOils(date, start,end,stationid);
		}
		
		List<String> allName = oilService.queryAllName();
		List<String> dates = new ArrayList<String>();
		List<Double> litre92 = new ArrayList<Double>();
		List<Double> litre95 = new ArrayList<Double>();
		List<Double> litre97 = new ArrayList<Double>();
		List<Double> litre0 = new ArrayList<Double>();
		List<Double> litre10 = new ArrayList<Double>();
		List<Double> litre20 = new ArrayList<Double>();
		Map<String,List> map = new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Oilb oil : list) {
				dates.add(oil.getDate());
				litre0.add(DoubleFormatUtil.format(oil.getLitre0()));
				litre10.add(DoubleFormatUtil.format(oil.getLitre10()));
				litre20.add(DoubleFormatUtil.format(oil.getLitre20()));
				litre92.add(DoubleFormatUtil.format(oil.getLitre92()));
				litre95.add(DoubleFormatUtil.format(oil.getLitre95()));
				litre97.add(DoubleFormatUtil.format(oil.getLitre97()));
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
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportByOils")
	public void exportByOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,@RequestParam(required=false,value="station")String [] station,
			Date start,Date end,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("燃油分油品销售情况.xls", "UTF-8");
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
        List<Oilb> list = new ArrayList<Oilb>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryByOils(date, start,end,ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=oilService.queryByOils(date, start,end,stationid);
		}
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("Litre0", "0#柴油");
		titleMap.put("Litre10", "-10#柴油");
		titleMap.put("Litre20", "-20#柴油");
		titleMap.put("Litre92", "92#汽油");
		titleMap.put("Litre95", "95#汽油");
		titleMap.put("Litre97", "97#汽油");
		String sheetName = "分油品销售";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end){
		
		List<Oil> list = new ArrayList<Oil>();
		if(ArryToListUtil.format(station)!=null){
			list=oilService.queryzhanbi( start,end,ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
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
			}
		}else {//条件内无数据
			DataPack dataPack = new DataPack("无数据", 0.0);
			datas.add(dataPack);
		}
		return datas;
	}
	//dashboard上油品数据的查询，本月累计销量，今年累计销量，目标完成率
	//近七日的销量，当日的汽油柴油销量，油品销量占比
	@RequestMapping("/queryDashBoard")
	@ResponseBody
	public Map<String, Object> queryDashboard(){
		Double monthLitre=0.0;//当月销量
		Double yearLitre=0.0;//今年销量
		List<String> date=new ArrayList<String>();//七天的时间集合
		List<Double> litre=new ArrayList<Double>();;//七天的销售量集合
		List<DataPack> dayzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<DataPack> monthzhanbi=new ArrayList<DataPack>();//制作当日占比
		List<Double> dayRate=new ArrayList<Double>();
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), null, "all");//一个月的销量
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {//遍历出来的就是当月的销售情况
				monthLitre=oil.getOilLitre();
			}
		}
		
		List<Oil> queryOils2 = oilService.queryOils("year", DateFormatUtils.getYearStart(), new Date(), null, "all");
		if(queryOils2!=null){
			for (Oil oil : queryOils2) {
				yearLitre=oil.getOilLitre();
			}
		}
		
		Double queryRate = targetService.queryRate(null);//今年所有油站累计的销售完成率
		Double queryTargetByMonth = targetService.queryTargetByMonth(null);//本月所有油站的目标和
		Double rateMonthDouble=monthLitre/queryTargetByMonth;//本月的完成率
		//本月每天的目标
		Double dayTarget=queryTargetByMonth/DateFormatUtils.getCurrentMonthDay();
		//求的是近一周的销售量
		List<Oil> queryOils3 = oilService.queryOils("day", DateFormatUtils.getWeekStart(), new Date(), null, "all");
		if(queryOils3!=null){
			for (Oil oil : queryOils3) {
				date.add(oil.getMinutes());
				litre.add(oil.getOilLitre());
				dayRate.add(oil.getOilLitre()/dayTarget);
			}
		}
		//当日占比图
		List<Oil> queryzhanbi = oilService.queryzhanbi(DateFormatUtils.getDayStart(), new Date(), null);
		if(queryzhanbi!=null){
			for (Oil oil : queryzhanbi) {
				dayzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
			}
		}
		//本月占比图
		List<Oil> queryzhanbi2 = oilService.queryzhanbi(DateFormatUtils.getMonthStart(), new Date(), null);
		if(queryzhanbi2!=null){
			for (Oil oil : queryzhanbi2) {
				monthzhanbi.add(new DataPack(oil.getOils(), oil.getOilLitre()));
			}
		}
		List<String> gasoline = new ArrayList<String>();//汽油
		List<String> diesel = new ArrayList<String>();//柴油
		gasoline.add("92#");
		gasoline.add("95#");
		diesel.add("0#");
		diesel.add("-10#");
		diesel.add("-20#");
		//当日柴油汽油销售量
		Oil queryOilsByTypegasoline = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), null, gasoline, "all");//汽油
		Oil queryOilsByTypediesel = oilService.queryOilsByType(DateFormatUtils.getDayStart(), new Date(), null, diesel, "all");//柴油
		//本月柴油汽油销售量
		Oil queryOilsByTypegasolinemonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), null, gasoline, "all");//汽油
		Oil queryOilsByTypedieselmonth = oilService.queryOilsByType(DateFormatUtils.getMonthStart(), new Date(), null, diesel, "all");//柴油
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthLitre", monthLitre);
		map.put("yearLitre", yearLitre);
		map.put("monthRate",DoubleFormatUtil.format(rateMonthDouble)+"%");
		map.put("yearRate", DoubleFormatUtil.format(queryRate)+"%");
		map.put("date", date);
		map.put("litre", litre);
		map.put("dayGasoline",queryOilsByTypegasoline.getOilLitre());
		map.put("dayDiesel",queryOilsByTypediesel.getOilLitre());
		map.put("monthGasoline",queryOilsByTypegasolinemonth.getOilLitre());
		map.put("monthDiesel",queryOilsByTypedieselmonth.getOilLitre());
		map.put("dayzhanbi", queryzhanbi);
		map.put("monthzhanbi", queryzhanbi2);
		map.put("dayRate", dayRate);
		return map;
	}
}
