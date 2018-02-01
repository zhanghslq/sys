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

import com.yb.entity.CompareOil;
import com.yb.entity.NotOil;
import com.yb.entity.Oil;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.NotOilService;
import com.yb.service.OilService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@RequestMapping("/compare")
@Scope("prototype")
@Controller
public class CompareController {
	@Resource
	private OilService oilService;
	@Resource
	private StationService stationService;
	@Resource
	private NotOilService notOilService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryOil")
	public Map<String, List> queryOil(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date newstart,Date newend,Date oldstart,
			Date oldend,String oilName,String people){
		Oil oldOil=null;
		Oil newOil=null;
		if(ArryToListUtil.format(station)!=null){
			oldOil = oilService.queryCompare(oldstart, oldend, ArryToListUtil.format(station),oilName,people);
			newOil = oilService.queryCompare(newstart, newend, ArryToListUtil.format(station),oilName,people);
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
			oldOil = oilService.queryCompare(oldstart, oldend, stationid,oilName,people);
			newOil = oilService.queryCompare(newstart, newend, stationid,oilName,people);
		}
		
		List<Double> litre = new ArrayList<Double>();
		List<Double> beforelitre = new ArrayList<Double>();
		List<Double> afterlitre = new ArrayList<Double>();
		List<Double> beforeavgLitre = new ArrayList<Double>();
		List<Double> afteravgLitre = new ArrayList<Double>();
		List<Double> avgLitre = new ArrayList<Double>();
		List<Double> beforenumber = new ArrayList<Double>();
		List<Double> afternumber = new ArrayList<Double>();
		List<Double> number = new ArrayList<Double>();
		if(oldOil!=null&&newOil!=null){
			Double oldLitre = oldOil.getOilLitre();
			Double oldNumber = oldOil.getOilNumber();
			Double oldavgLitre=oldLitre/oldNumber;
			
			Double newLitre = newOil.getOilLitre();
			Double newNumber = newOil.getOilNumber();
			Double newavgLitre=newLitre/newNumber;
			
			beforelitre.add(DoubleFormatUtil.format(oldLitre/1000));
			afterlitre.add(DoubleFormatUtil.format(newLitre/1000));
			litre.add(DoubleFormatUtil.format((newLitre-oldLitre)/oldLitre*100));
			beforeavgLitre.add(DoubleFormatUtil.format(oldavgLitre));
			afteravgLitre.add(DoubleFormatUtil.format(newavgLitre));
			avgLitre.add(DoubleFormatUtil.format((newavgLitre-oldavgLitre)/oldavgLitre*100));
			beforenumber.add(oldNumber);
			afternumber.add(newNumber);
			number.add(DoubleFormatUtil.format((newNumber-oldNumber)/oldNumber*100));
		}else {
			beforelitre.add(0.0);
			afterlitre.add(0.0);
			beforeavgLitre.add(0.0);
			afteravgLitre.add(0.0);
			beforenumber.add(0.0);
			afternumber.add(0.0);
			litre.add(0.0);
			avgLitre.add(0.0);
			number.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("beforelitre", beforelitre);
		map.put("afterlitre", afterlitre);
		map.put("litre", litre);
		map.put("beforeavgLitre", beforeavgLitre);
		map.put("afteravgLitre", afteravgLitre);
		map.put("avgLitre", avgLitre);
		map.put("beforenumber", beforenumber);
		map.put("afternumber", afternumber);
		map.put("number", number);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/exportOil")
	public void exportOil(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="openDate")String [] type,@RequestParam(required=false,value="station")String [] station,
			Date newstart,Date newend,Date oldstart,
			Date oldend,String oilName,String people){
		String encode="";
		try {
			encode = URLEncoder.encode("油品对比销售情况.xls", "UTF-8");
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
		Oil oldOil=null;
		Oil newOil=null;
		if(ArryToListUtil.format(station)!=null){
			oldOil = oilService.queryCompare(oldstart, oldend, ArryToListUtil.format(station),oilName,people);
			newOil = oilService.queryCompare(newstart, newend, ArryToListUtil.format(station),oilName,people);
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
			oldOil = oilService.queryCompare(oldstart, oldend, stationid,oilName,people);
			newOil = oilService.queryCompare(newstart, newend, stationid,oilName,people);
		}
		CompareOil compareOil=null;
		if(oldOil!=null&&newOil!=null){
			Double oldLitre = oldOil.getOilLitre();
			Double oldNumber = oldOil.getOilNumber();
			Double oldavgLitre=oldLitre/oldNumber;
			Double newLitre = newOil.getOilLitre();
			Double newNumber = newOil.getOilNumber();
			Double newavgLitre=newLitre/newNumber;
			compareOil=new CompareOil(oldLitre, newLitre, (newLitre-oldLitre)/oldLitre, oldNumber,
					newNumber, (newNumber-oldNumber)/oldNumber, oldavgLitre, newavgLitre, (newavgLitre-oldavgLitre)/oldavgLitre);
		}else {
			compareOil=new CompareOil();
		}
		List<CompareOil> list = new ArrayList<CompareOil>();
		list.add(compareOil);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("beforeLitre", "前总销量");
		titleMap.put("afterLitre", "后总销量");
		titleMap.put("litreRate", "销量增长率");
		titleMap.put("beforeNumber", "前销售笔数");
		titleMap.put("afterNumber", "后销售笔数");
		titleMap.put("numberRate", "销售笔数增长率");
		titleMap.put("beforeAvgLitre", "前平均销量");
		titleMap.put("afterAvgLitre", "后平均销量");
		titleMap.put("avgLitreRate", "平均销量增长率");
		String sheetName = "销量对比信息";
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
	@RequestMapping("/queryShop")
	public Map<String, List<Double>> queryShop(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date oldstart,Date oldend,Date newstart,Date newend,
			String departmentName,String people){
		NotOil oldNotOil=null;
		NotOil newNotOil=null;
		if(ArryToListUtil.format(station)!=null){
			oldNotOil = notOilService.queryByCompare(oldstart, oldend, ArryToListUtil.format(station), departmentName,people);
			newNotOil = notOilService.queryByCompare(newstart, newend, ArryToListUtil.format(station), departmentName,people);
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
			oldNotOil = notOilService.queryByCompare(oldstart, oldend, stationid, departmentName,people);
			newNotOil = notOilService.queryByCompare(newstart, newend, stationid, departmentName,people);
		}
		
		List<Double> aftermoneys = new ArrayList<Double>();
		List<Double> beforemoneys = new ArrayList<Double>();
		List<Double> moneys = new ArrayList<Double>();
		List<Double> beforeavgMoneys = new ArrayList<Double>();
		List<Double> afteravgMoneys = new ArrayList<Double>();
		List<Double> avgMoneys = new ArrayList<Double>();
		List<Double> beforenumbers = new ArrayList<Double>();
		List<Double> afternumbers = new ArrayList<Double>();
		List<Double> numbers = new ArrayList<Double>();
		if(oldNotOil!=null&&newNotOil!=null){
			Double oldAvgMoney = oldNotOil.getAvgMoney();
			Double oldMoney = oldNotOil.getNotOilMoney();
			Double oldNumber = Double.valueOf(String.valueOf(oldNotOil.getNotOilNumber()));
			Double newAvgMoney = newNotOil.getAvgMoney();
			Double newMoney = newNotOil.getNotOilMoney();
			Double newNumber = Double.valueOf(String.valueOf(newNotOil.getNotOilNumber()));
			Double avgMoney = (newAvgMoney-oldAvgMoney)/(oldAvgMoney);
			Double money = (newMoney-oldMoney)/(oldMoney);
			Double number=(newNumber-oldNumber)/oldNumber;
			
			beforemoneys.add(DoubleFormatUtil.format(oldMoney));
			aftermoneys.add(DoubleFormatUtil.format(newMoney));
			moneys.add(DoubleFormatUtil.format(money*100));
			beforeavgMoneys.add(DoubleFormatUtil.format(oldAvgMoney));
			afteravgMoneys.add(DoubleFormatUtil.format(newAvgMoney));
			avgMoneys.add(DoubleFormatUtil.format(avgMoney*100));
			beforenumbers.add(oldNumber);
			afternumbers.add(newNumber);
			numbers.add(DoubleFormatUtil.format(number*100));
		}else {
			beforemoneys.add(0.0);
			aftermoneys.add(0.0);
			beforeavgMoneys.add(0.0);
			afteravgMoneys.add(0.0);
			beforenumbers.add(0.0);
			afternumbers.add(0.0);
			moneys.add(0.0);
			avgMoneys.add(0.0);
			numbers.add(0.0);
		}
		Map<String,List<Double>> map = new HashMap<String,List<Double>>();
		map.put("beforemoneys", beforemoneys);
		map.put("aftermoneys", aftermoneys);
		map.put("moneys", moneys);
		map.put("beforeavgMoneys", beforeavgMoneys);
		map.put("afteravgMoneys", afteravgMoneys);
		map.put("avgMoneys", avgMoneys);
		map.put("beforenumbers", beforenumbers);
		map.put("afternumbers", afternumbers);
		map.put("numbers", numbers);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportShop")
	public void exportShop(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="openDate")String [] type,@RequestParam(required=false,value="station")String [] station,
			Date newstart,Date newend,Date oldstart,
			Date oldend,String oilName,String people,String departmentName){
		String encode="";
		try {
			encode = URLEncoder.encode("便利店对比销售情况.xls", "UTF-8");
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
        NotOil oldNotOil=null;
		NotOil newNotOil=null;
		if(ArryToListUtil.format(station)!=null){
			oldNotOil = notOilService.queryByCompare(oldstart, oldend, ArryToListUtil.format(station), departmentName,people);
			newNotOil = notOilService.queryByCompare(newstart, newend, ArryToListUtil.format(station), departmentName,people);
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
			oldNotOil = notOilService.queryByCompare(oldstart, oldend, stationid, departmentName,people);
			newNotOil = notOilService.queryByCompare(newstart, newend, stationid, departmentName,people);
		}
		
		CompareOil compareOil=null;
		if(oldNotOil!=null&&newNotOil!=null){
			Double oldLitre = oldNotOil.getNotOilMoney();
			Double oldNumber = Double.valueOf(String.valueOf(oldNotOil.getNotOilNumber()));
			Double oldavgLitre=oldLitre/oldNumber;
			Double newLitre = newNotOil.getNotOilMoney();
			Double newNumber = Double.valueOf(String.valueOf(newNotOil.getNotOilNumber()));
			Double newavgLitre=newLitre/newNumber;
			compareOil=new CompareOil(oldLitre, newLitre, (newLitre-oldLitre)/oldLitre, oldNumber,
					newNumber, (newNumber-oldNumber)/oldNumber, oldavgLitre, newavgLitre, (newavgLitre-oldavgLitre)/oldavgLitre);
		}else {
			compareOil=new CompareOil();
		}
		List<CompareOil> list = new ArrayList<CompareOil>();
		list.add(compareOil);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("beforeLitre", "前总销售额");
		titleMap.put("afterLitre", "后总销售额");
		titleMap.put("litreRate", "销售额增长率");
		titleMap.put("beforeNumber", "前销售笔数");
		titleMap.put("afterNumber", "后销售笔数");
		titleMap.put("numberRate", "销售笔数增长率");
		titleMap.put("beforeAvgLitre", "前平均销售额");
		titleMap.put("afterAvgLitre", "后平均销售额");
		titleMap.put("avgLitreRate", "平均销售额增长率");
		String sheetName = "便利店销售额对比信息";
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
	
	@RequestMapping("/queryRateCompare")
	@ResponseBody
	public Map<String, List<Double>> queryRateCompare(Date oldstart,Date oldend,Date newstart,Date newend,String station,String query){
		Double oldrate = notOilService.queryRateCompare(oldstart, oldend, station, query);
		Double newrate = notOilService.queryRateCompare(newstart, newend, station, query);
		List<Double> data = new ArrayList<Double>();
		List<Double> before = new ArrayList<Double>();
		List<Double> after = new ArrayList<Double>();
		if(oldrate!=null&&newrate!=null){
			before.add(oldrate);
			after.add(newrate);
			data.add((newrate-oldrate)/oldrate);
		}else {
			data.add(0.00);
			after.add(0.00);
			before.add(0.00);
		}
		Map<String,List<Double>> map = new HashMap<String,List<Double>>();
		map.put("before", before);
		map.put("after", after);
		map.put("data", data);
		return map;
	}
}
