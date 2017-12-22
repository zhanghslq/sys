package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.yb.entity.NotOil;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.NotOilService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/notOil")
@Scope("prototype")
public class NotOilController {
	@Resource
	private NotOilService notOilService;
	@Resource
	private StationService stationService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryNotOils")
	public Map<String, List> queryNotOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="station[]")String [] station,String date,Date start,Date end,String people){
		List<NotOil> list = new ArrayList<NotOil>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryNotOils(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryNotOils(date, start,end,stationid,people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> moneys = new ArrayList<Double>();
		List<Double> avgMoney = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				moneys.add(DoubleFormatUtil.format(notOil.getNotOilMoney()));
				avgMoney.add(DoubleFormatUtil.format(notOil.getAvgMoney()));
				numbers.add(notOil.getNotOilNumber());
			}
		}else {
			dates.add("无数据");
			moneys.add(0.0);
			avgMoney.add(0.0);
			numbers.add(0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("moneys", moneys);
		map.put("avgMoney", avgMoney);
		map.put("numbers", numbers);
		return map;
	}
	//导出
	//油品信息导出
		@ResponseBody
		@RequestMapping("/exportNotOils")
		public void exportNotOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
				@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
				@RequestParam(required=false,value="gasolines")String [] gasoline,
				@RequestParam(required=false,value="location")String [] locs, 
				@RequestParam(required=false,value="openDate")String [] openDate,@RequestParam(required=false,value="station")String [] station,
				Date start,Date end,String date,String people){
			String encode="";
			try {
				encode = URLEncoder.encode("非油销售整体情况.xlsx", "UTF-8");
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
	        List<NotOil> list = new ArrayList<NotOil>();
	        List<DataPack> list2 = new ArrayList<DataPack>();
			if(ArryToListUtil.format(station)!=null){
				list = notOilService.queryNotOils(date, start, end, ArryToListUtil.format(station),people);
				list2 = notOilService.queryExceptLube(date, start, end, ArryToListUtil.format(station),people);
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
				list=notOilService.queryNotOils(date, start,end,stationid,people);
				list2=notOilService.queryExceptLube(date, start,end,stationid,people);
			}
			if(list!=null){
				for (NotOil notOil : list) {
					if(list2!=null){
						for (DataPack dataPack : list2) {
							if(notOil.getMinutes().equals(dataPack.getName())){
								notOil.setExceptLube(DoubleFormatUtil.format(dataPack.getValue()));
								notOil.setAvgMoney(DoubleFormatUtil.format(notOil.getAvgMoney()));
							}
						}
					}
				}
			}
			Map<String,String> titleMap = new LinkedHashMap<String,String>();
			titleMap.put("minutes", "时间");
			titleMap.put("notOilNumber", "销售笔数");
			titleMap.put("notOilMoney", "销售金额");
			titleMap.put("avgMoney", "平均单笔销售金额");
			titleMap.put("exceptLube", "除去润滑油单笔销售额");
			String sheetName = "非油销售整体情况";
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
	@ResponseBody
	@RequestMapping("/queryExceptLube")
	public Map<String,List> queryExceptLube(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="station[]")String [] station,String date,Date start,Date end,String people){
		List<DataPack> list = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryExceptLube(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryExceptLube(date, start,end,stationid,people);
		}
		List<String> minutes = new ArrayList<String>();
		List<Double> avgMoney = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				minutes.add(dataPack.getName());
				avgMoney.add(DoubleFormatUtil.format(dataPack.getValue()));
			}
		}else {
			minutes.add("无数据");
			avgMoney.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("minutes", minutes);
		map.put("avgMoney", avgMoney);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryByDepartmentName")
	public Map<String, List> queryByDepartmentName(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end,String departmentName,String people){
		List<NotOil> list = new ArrayList<NotOil>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryByDepartmentName(date, start, end, ArryToListUtil.format(station), departmentName,people);
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
			list=notOilService.queryByDepartmentName(date, start, end, stationid, departmentName,people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> amounts = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				amounts.add(notOil.getNotOilMoney());
			}
		}else {
			dates.add("无数据");
			amounts.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("amounts", amounts);
		return map;
	}
	//导出分品类销售量
	@ResponseBody
	@RequestMapping("/exportByDepartmentName")
	public void exportByDepartmentName(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String departmentName,String people){
		if(departmentName.equals("all")){
			departmentName="总体";
		}
		String encode="";
		try {
			encode = URLEncoder.encode(departmentName+"销售情况.xlsx", "UTF-8");
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
        List<NotOil> list = new ArrayList<NotOil>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryByDepartmentName(date, start, end, ArryToListUtil.format(station), departmentName,people);
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
			list=notOilService.queryByDepartmentName(date, start, end, stationid, departmentName,people);
		}
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("minutes", "时间");
		titleMap.put("notOilMoney", "销售金额");
		String sheetName = departmentName+"销售情况";
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
	@ResponseBody
	@RequestMapping("queryAllName")
	public List<String> queryAllName(){
		List<String> list = notOilService.queryAllName();
		return list;
		
	}
	/**
	 * 便利店的开单率
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryRate")
	@ResponseBody
	public Map<String,List> queryRate(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date,String people){
		List<NotOil> list = new ArrayList<NotOil>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryRate(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryRate(date, start,end,stationid,people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> rates = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (NotOil notOil : list) {
				dates.add(notOil.getMinutes());
				rates.add(DoubleFormatUtil.format(notOil.getAvgMoney()*100));
			}
		}else {
			dates.add("无数据");
			rates.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("rates", rates);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportRate")
	public void exportRate(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String people){
		String encode="";
		try {
			encode = URLEncoder.encode("便利店开单率.xlsx", "UTF-8");
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
        List<NotOil> list = new ArrayList<NotOil>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryRate(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryRate(date, start,end,stationid,people);
		}
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("minutes", "时间");
		titleMap.put("avgMoney", "开单率");
		String sheetName = "便利店开单率";
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
	@RequestMapping("/queryTop")
	@ResponseBody
	public Map<String, List> queryTop(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people){
		List<DataPack> list = new ArrayList<DataPack>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryTop(start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryTop(start,end,stationid,people);
		}
		List<String> names = new ArrayList<String>();
		List<Double> data = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				names.add(dataPack.getName());
				data.add(dataPack.getValue());
			}
		}else {
			names.add("无数据");
			data.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		Collections.reverse(names);
		Collections.reverse(data);
		map.put("names", names);
		map.put("data", data);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/querySearch")
	public Map<String, List> querySearch(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String productCode,String date) throws ParseException{
		List<DataPack> list = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list=notOilService.querySearch(start, end, ArryToListUtil.format(station),date, productCode);
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
			list=notOilService.querySearch(start, end, stationid,date, productCode);
		}
		
		List<String> dates = new ArrayList<String>();
		List<Double> datas = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				datas.add(dataPack.getValue());
			}
		}else {
			dates.add("无数据");
			datas.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("datas", datas);
		return map;
	}
	
}
