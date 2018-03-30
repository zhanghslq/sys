package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.EvaluationData;
import com.yb.entity.Evaluationb;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.excel.util.ExportExcelUtil;
import com.yb.service.EvaluationbService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@Controller
@Scope("prototype")
@RequestMapping("/evaluationb")
public class EvaluationbController {

	@Resource
	private EvaluationbService evaluationbService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryEvaluation")
	@ResponseBody
	public Map<String, List> queryEvaluation(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end,String area){
		List<Evaluationb> list = new ArrayList<Evaluationb>();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationbService.queryByDate(start, end, ArryToListUtil.format(station));
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
			list=evaluationbService.queryByDate(start, end, stationid);
		}
		List<String> name = new ArrayList<String>();
		List<Double> yes = new ArrayList<Double>();
		List<Double> no = new ArrayList<Double>();
		List<Double> unknown = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Evaluationb evaluationb : list) {
				name.add(evaluationb.getPROBLEMTEXT());
				yes.add(evaluationb.getYes());
				no.add(evaluationb.getNo());
				unknown.add(evaluationb.getUnknow());
			}
		}else {
			name.add("无数据");
			yes.add(0.0);
			no.add(0.0);
			unknown.add(0.0);
		}
		Map<String, List> map = new HashMap<String,List>();
		map.put("name",name );
		map.put("yes", yes);
		map.put("no", no);
		map.put("unknown",unknown );
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportQuestion")
	public void exportQuestion(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			Date start,Date end){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"问题评价信息.xls", "UTF-8");
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
        List<Evaluationb> list = new ArrayList<Evaluationb>();
        List<Evaluationb> list1 = new ArrayList<Evaluationb>();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationbService.queryByDate(start, end, ArryToListUtil.format(station));
			list1=evaluationbService.exportByDate(start, end, ArryToListUtil.format(station));
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
			list=evaluationbService.queryByDate(start, end, stationid);
			list1=evaluationbService.exportByDate(start, end, stationid);
		}
		for (Evaluationb evaluationb : list) {
			evaluationb.setStationID("加总");
		}
		list.addAll(list1);
		for (Evaluationb evaluationb : list) {
			Double count=evaluationb.getYes()+evaluationb.getNo()+evaluationb.getUnknow();
			evaluationb.setYesString(DoubleFormatUtil.formatString(evaluationb.getYes()*100/count)+"%");
			evaluationb.setNoString(DoubleFormatUtil.formatString(evaluationb.getNo()*100/count)+"%");
			evaluationb.setUnknowString(DoubleFormatUtil.formatString(evaluationb.getUnknow()*100/count)+"%");
		}
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("PROBLEMTEXT", "问题");
		titleMap.put("yes", "回答是（人数）");
		titleMap.put("yesString", "回答是（百分比）");
		titleMap.put("no", "回答否（人数）");
		titleMap.put("noString", "回答否（百分比）");
		titleMap.put("unknow", "未回答（人数）");
		titleMap.put("unknowString", "未回答（百分比）");
		titleMap.put("stationID", "油站编号");
		String sheetName = "评价信息";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
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
	@RequestMapping("/exportQuestionByData")
	public void exportQuestionByData(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			Date start,Date end){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"评价信息源数据.xlsx", "UTF-8");
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
		List<EvaluationData> list = new ArrayList<EvaluationData>();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationbService.exportData(start, end, ArryToListUtil.format(station));
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
			list=evaluationbService.exportData(start, end, stationid);
		}
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("station", "油站名称");
		titleMap.put("mobilePhone", "会员手机号");
		titleMap.put("evaluation_time", "评价时间");
		titleMap.put("hello", "员工主动问好");
		titleMap.put("clean", "免费擦玻璃服务");
		titleMap.put("sale", "促销活动推广");
		titleMap.put("goodbye", "致谢道别");
		titleMap.put("toilet", "卫生间卫生问题");
		titleMap.put("star4", "加油速度");
		titleMap.put("star3", "油站环境");
		titleMap.put("star", "整体满意度");
		titleMap.put("content", "自定义评价");
		String sheetName = "评价信息源数据";
		//应该是要返回一个hsswork然后os响应出来
		SXSSFWorkbook excelExport = ExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
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
