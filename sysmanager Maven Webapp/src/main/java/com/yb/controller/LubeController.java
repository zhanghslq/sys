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
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Lube;
import com.yb.entity.LubeAndVip;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.LubeService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/lube")
@Scope("prototype")
public class LubeController {

	@Resource
	private LubeService lubeService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryLubes")
	public Map<String,List> queryLubes(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String[] type,
			@RequestParam(required=false,value="station[]")String[] station,
			String date,Date start,Date end,String people){
		List<Lube> list = new ArrayList<Lube>();
		if(ArryToListUtil.format(station)!=null){
			list = lubeService.queryLubes(date, start, end, ArryToListUtil.format(station),people);
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
			list=lubeService.queryLubes(date, start,end,stationid,people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> moneys = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgMoney = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Lube lube : list) {
				dates.add(lube.getMinutes());
				moneys.add(lube.getLubeMoney());
				numbers.add(lube.getLubeNumber());
				avgMoney.add(lube.getAvgMoney());
			}
		}else {
			dates.add("无数据");
			moneys.add(0.0);
			numbers.add(0);
			avgMoney.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("moneys", moneys);
		map.put("numbers", numbers);
		map.put("avgMoney", avgMoney);
		return map;
	}
	//查询全部的跟会员的
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryAllAndVip")
	public Map<String,List> queryAllAndVip(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String[] type,
			@RequestParam(required=false,value="station[]")String[] station,
			String date,Date start,Date end){
		List<LubeAndVip> list = new ArrayList<LubeAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list = lubeService.queryAllAndVip(date, start, end,  ArryToListUtil.format(station));
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
			list=lubeService.queryAllAndVip(date, start, end, stationid);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> moneys = new ArrayList<Double>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Double> avgMoney = new ArrayList<Double>();
		List<Double> vipmoneys = new ArrayList<Double>();
		List<Integer> vipnumbers = new ArrayList<Integer>();
		List<Double> vipavgMoney = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (LubeAndVip lubeAndVip : list) {
				dates.add(lubeAndVip.getMinutes());
				if(lubeAndVip.getLubeMoney()!=null){
					moneys.add(lubeAndVip.getLubeMoney());
					numbers.add(lubeAndVip.getLubeNumber());
					avgMoney.add(lubeAndVip.getAvgMoney());
				}else {
					moneys.add(0.0);
					numbers.add(0);
					avgMoney.add(0.0);
				}
				if(lubeAndVip.getVipLubeMoney()!=null){
					vipmoneys.add(lubeAndVip.getVipLubeMoney());
					vipnumbers.add(lubeAndVip.getVipLubeNumber());
					vipavgMoney.add(lubeAndVip.getVipAvgMoney());
				}else {
					vipmoneys.add(0.0);
					vipnumbers.add(0);
					vipavgMoney.add(0.0);
				}
			}
		}else {
			dates.add("无数据");
			moneys.add(0.0);
			numbers.add(0);
			avgMoney.add(0.0);
			vipmoneys.add(0.0);
			vipnumbers.add(0);
			vipavgMoney.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("moneys", moneys);
		map.put("numbers", numbers);
		map.put("avgMoney", avgMoney);
		map.put("vipmoneys", vipmoneys);
		map.put("vipnumbers", vipnumbers);
		map.put("vipavgMoney", vipavgMoney);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportLube")
	public void exportByDepartmentName(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String people){
		
		String encode="";
		try {
			encode = URLEncoder.encode("润滑油销售情况.xls", "UTF-8");
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
        List<LubeAndVip> list = new ArrayList<LubeAndVip>();
		if(ArryToListUtil.format(station)!=null){
			list = lubeService.queryAllAndVip(date, start, end,  ArryToListUtil.format(station));
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
			list=lubeService.queryAllAndVip(date, start, end, stationid);
		}
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("minutes", "时间");
		titleMap.put("lubeNumber", "销售笔数");
		titleMap.put("lubeMoney", "销售金额");
		titleMap.put("avgMoney", "平均单笔销售金额");
		titleMap.put("vipLubeNumber", "会员消费笔数");
		titleMap.put("vipLubeMoney", "会员消费金额");
		titleMap.put("vipAvgMoney", "会员平均单笔销售金额");
		String sheetName = "润滑油销售情况";
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
	
}
