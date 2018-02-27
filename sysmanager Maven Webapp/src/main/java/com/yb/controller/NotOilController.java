package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import com.yb.entity.DataPack;
import com.yb.entity.Department;
import com.yb.entity.ExceptLube;
import com.yb.entity.NotOil;
import com.yb.entity.NotOilAndVip;
import com.yb.entity.Oil;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.NotOilService;
import com.yb.service.OilService;
import com.yb.service.StationService;
import com.yb.service.TargetService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DateFormatUtils;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/notOil")
@Scope("prototype")
/**
 * 
 * @author lenovo
 *
 */
public class NotOilController {
	@Resource
	private NotOilService notOilService;
	@Resource
	private StationService stationService;
	@Resource
	private OilService oilService;
	@Resource
	private TargetService targetService;
	/**
	 * 原来的只有总的查询，
	 * @param citys
	 * @param regions
	 * @param sales
	 * @param gasoline
	 * @param locs
	 * @param openDate
	 * @param type
	 * @param station
	 * @param date
	 * @param start
	 * @param end
	 * @param people
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryNotOils")
	public Map<String, List> queryNotOils(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,String date,Date start,Date end,String people){
		List<NotOil> list = new ArrayList<NotOil>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryNotOils(date, start, end, ArryToListUtil.format(station),people);
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
		Map<String,List> map = new HashMap<String,List>(10);
		map.put("dates", dates);
		map.put("moneys", moneys);
		map.put("avgMoney", avgMoney);
		map.put("numbers", numbers);
		return map;
	}
	/**
	 * 原来的只有总的查询，
	 * @param citys
	 * @param regions
	 * @param sales
	 * @param gasoline
	 * @param locs
	 * @param openDate
	 * @param type
	 * @param station
	 * @param date 展示单位
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
		@SuppressWarnings("rawtypes")
		@ResponseBody
		@RequestMapping("/queryAllAndVip")
		public Map<String, List> queryAllAndVip(@RequestParam(required=false,value="citys[]")String[] citys,
				@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
				@RequestParam(required=false,value="gasoline[]")String [] gasoline,
				@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
				@RequestParam(required=false,value="type[]")String [] type,
				@RequestParam(required=false,value="station[]")String [] station,
				String date,Date start,Date end){
			List<NotOilAndVip> list = new ArrayList<NotOilAndVip>();
			if(ArryToListUtil.format(station)!=null){
				list = notOilService.queryAllAndVip(date, start, end, ArryToListUtil.format(station));
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
				list=notOilService.queryAllAndVip(date, start, end, stationid);
			}
			List<String> dates = new ArrayList<String>();
			List<Double> moneys = new ArrayList<Double>();
			List<Double> avgMoney = new ArrayList<Double>();
			List<Integer> numbers = new ArrayList<Integer>();
			List<Double> vipmoneys = new ArrayList<Double>();
			List<Double> vipavgMoney = new ArrayList<Double>();
			List<Integer> vipnumbers = new ArrayList<Integer>();
			List<Double> notvipmoneys = new ArrayList<Double>();
			List<Double> notvipavgMoney = new ArrayList<Double>();
			List<Integer> notvipnumbers = new ArrayList<Integer>();
			if(list!=null&&list.size()!=0){
				for (NotOilAndVip notOilAndVip : list) {
					dates.add(notOilAndVip.getMinutes());
					moneys.add(DoubleFormatUtil.format(notOilAndVip.getNotOilMoney()));
					avgMoney.add(DoubleFormatUtil.format(notOilAndVip.getAvgMoney()));
					numbers.add(notOilAndVip.getNotOilNumber());
					vipmoneys.add(DoubleFormatUtil.format(notOilAndVip.getVipNotOilMoney()));
					vipavgMoney.add(DoubleFormatUtil.format(notOilAndVip.getVipAvgMoney()));
					vipnumbers.add(notOilAndVip.getVipNotOilNumber());
					notvipavgMoney.add(DoubleFormatUtil.format(notOilAndVip.getNotVipAvgMoney()));
					notvipmoneys.add(DoubleFormatUtil.format(notOilAndVip.getNotVipNotOilMoney()));
					notvipnumbers.add(notOilAndVip.getNotVipNotOilNumber());
				}
			}else {
				dates.add("无数据");
				moneys.add(0.0);
				avgMoney.add(0.0);
				numbers.add(0);
				vipmoneys.add(0.0);
				vipavgMoney.add(0.0);
				vipnumbers.add(0);
			}
			Map<String,List> map = new HashMap<String,List>();
			map.put("dates", dates);
			map.put("moneys", moneys);
			map.put("avgMoney", avgMoney);
			map.put("numbers", numbers);
			map.put("vipmoneys", vipmoneys);
			map.put("vipavgMoney", vipavgMoney);
			map.put("vipnumbers", vipnumbers);
			map.put("notvipavgMoney", notvipavgMoney);
			map.put("notvipmoneys", notvipmoneys);
			map.put("notvipnumbers", notvipnumbers);
			return map;
		}
		//导出
		@ResponseBody
		@RequestMapping("/exportNotOils")
		public void exportNotOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
				@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
				@RequestParam(required=false,value="gasolines")String [] gasoline,
				@RequestParam(required=false,value="location")String [] locs, 
				@RequestParam(required=false,value="openDate")String [] openDate,
				@RequestParam(required=false,value="type")String [] type,
				@RequestParam(required=false,value="station")String [] station,
				Date start,Date end,String date,String people){
			String encode="";
			try {
				encode = URLEncoder.encode("非油销售整体情况.xls", "UTF-8");
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
	         * 获取需要导出的集合信息
	         * list4分油站的出去润滑油的单笔信息
	         * list3分油站的非油的信息
	         */
	        List<NotOilAndVip> list = new ArrayList<NotOilAndVip>();
	        List<NotOilAndVip> list3 = new ArrayList<NotOilAndVip>();
	        List<ExceptLube> list2 = new ArrayList<ExceptLube>();
	        List<ExceptLube> list4 = new ArrayList<ExceptLube>();
			if(ArryToListUtil.format(station)!=null){
				list = notOilService.queryAllAndVip(date, start, end,ArryToListUtil.format(station));
				list3 = notOilService.exportAllAndVip(date, start, end,ArryToListUtil.format(station));
				list2 = notOilService.queryExceptLube(date, start, end, ArryToListUtil.format(station));
				list4 = notOilService.exportExceptLube(date, start, end, ArryToListUtil.format(station));
				
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
				list=notOilService.queryAllAndVip(date, start, end, stationid);
				list2=notOilService.queryExceptLube(date, start,end,stationid);
				list3 = notOilService.exportAllAndVip(date, start, end,stationid);
				list4 = notOilService.exportExceptLube(date, start, end, stationid);
			}
			if(list!=null){
				for (NotOilAndVip notOilAndVip : list) {
					if(list2!=null){
						for (ExceptLube exceptLube : list2) {
							if(notOilAndVip.getMinutes().equals(exceptLube.getMinutes())){
								notOilAndVip.setExceptLube(DoubleFormatUtil.format(exceptLube.getMoney()));
								notOilAndVip.setVipExceptLube(DoubleFormatUtil.format(exceptLube.getVipMoney()));
								notOilAndVip.setAvgMoney(DoubleFormatUtil.format(notOilAndVip.getAvgMoney()));
							}
						}
					}
					notOilAndVip.setStationID("加总");
				}
			}
			/**
			 * 安油站导出，增加出去润滑油的结果
			 */
			if(list3!=null){
				for (NotOilAndVip notOilAndVip : list3) {
					if(list2!=null){
						for (ExceptLube exceptLube : list4) {
							if(notOilAndVip.getMinutes().equals(exceptLube.getMinutes())&&notOilAndVip.getStationID().equals(exceptLube.getStationID())){
								notOilAndVip.setExceptLube(DoubleFormatUtil.format(exceptLube.getMoney()));
								notOilAndVip.setVipExceptLube(DoubleFormatUtil.format(exceptLube.getVipMoney()));
								notOilAndVip.setAvgMoney(DoubleFormatUtil.format(notOilAndVip.getAvgMoney()));
							}
						}
					}
				}
			}
			list.addAll(list3);
			Map<String,String> titleMap = new LinkedHashMap<String,String>();
			titleMap.put("minutes", "时间");
			titleMap.put("notOilNumber", "销售笔数");
			titleMap.put("notOilMoney", "销售金额");
			titleMap.put("avgMoney", "平均单笔销售金额");
			titleMap.put("exceptLube", "除去润滑油单笔销售额");
			titleMap.put("vipNotOilNumber", "会员笔数");
			titleMap.put("vipNotOilMoney", "会员消费金额");
			titleMap.put("vipAvgMoney", "会员平均单笔销售金额");
			titleMap.put("vipExceptLube", "会员除去润滑油单笔销售额");
			titleMap.put("stationID", "油站编号");
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
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end){
		List<ExceptLube> list = new ArrayList<ExceptLube>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryExceptLube(date, start, end, ArryToListUtil.format(station));
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
			list=notOilService.queryExceptLube(date, start,end,stationid);
		}
		List<String> minutes = new ArrayList<String>();
		List<Double> avgMoney = new ArrayList<Double>();
		List<Double> vipavgMoney = new ArrayList<Double>();
		List<Double> notvipavgMoney = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (ExceptLube exceptLube : list) {
				minutes.add(exceptLube.getMinutes());
				avgMoney.add(DoubleFormatUtil.format(exceptLube.getMoney()));
				vipavgMoney.add(DoubleFormatUtil.format(exceptLube.getVipMoney()));
				notvipavgMoney.add(DoubleFormatUtil.format(exceptLube.getNotVipMoney()));
			}
		}else {
			minutes.add("无数据");
			avgMoney.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("minutes", minutes);
		map.put("avgMoney", avgMoney);
		map.put("vipavgMoney", vipavgMoney);
		map.put("notvipavgMoney", notvipavgMoney);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryByDepartmentName")
	public Map<String, List> queryByDepartmentName(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end,String people){
		List<Department> list = new ArrayList<Department>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryAllDepartments(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryAllDepartments(date, start, end, stationid, people);
		}
		List<String> dates = new ArrayList<String>();
		List<Double> instoreMoney = new ArrayList<Double>();
		List<Double> fastfoodMoney = new ArrayList<Double>();
		List<Double> perishableMoney = new ArrayList<Double>();
		List<Double> lubeMoney = new ArrayList<Double>();
		List<Double> cigaretteMoney = new ArrayList<Double>();
		List<Double> dailyMoney = new ArrayList<Double>();
		List<Double> teamcardMoney = new ArrayList<Double>();
		List<Double> alcoholicMoney = new ArrayList<Double>();
		List<Double> snackMoney = new ArrayList<Double>();
		List<Double> nonalcoholicMoney = new ArrayList<Double>();
		List<Double> nonfoodMoney = new ArrayList<Double>();
		
		if(list!=null&&list.size()!=0){
			for (Department department : list) {
				dates.add(department.getMinutes());
				instoreMoney.add(department.getInstoreMoney());
				fastfoodMoney.add(department.getFastfoodMoney());
				perishableMoney.add(department.getPerishableMoney());
				lubeMoney.add(department.getLubeMoney());
				cigaretteMoney.add(department.getCigaretteMoney());
				dailyMoney.add(department.getDailyMoney());
				teamcardMoney.add(department.getTeamcardMoney());
				alcoholicMoney.add(department.getAlcoholicMoney());
				snackMoney.add(department.getSnackMoney());
				nonalcoholicMoney.add(department.getNonalcoholicMoney());
				nonfoodMoney.add(department.getNonfoodMoney());
			}
		}else {
			dates.add("无数据");
			instoreMoney.add(0.0);
			fastfoodMoney.add(0.0);
			perishableMoney.add(0.0);
			lubeMoney.add(0.0);
			cigaretteMoney.add(0.0);
			dailyMoney.add(0.0);
			teamcardMoney.add(0.0);
			alcoholicMoney.add(0.0);
			snackMoney.add(0.0);
			nonalcoholicMoney.add(0.0);
			nonfoodMoney.add(0.0);
		}
		List<String> queryAllName = notOilService.queryAllName();
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("name", queryAllName);
		map.put("fastfoodMoney",fastfoodMoney );
		map.put("perishableMoney",perishableMoney );
		map.put("lubeMoney",lubeMoney );
		map.put("cigaretteMoney",cigaretteMoney );
		map.put("dailyMoney",dailyMoney );
		map.put("teamcardMoney",teamcardMoney );
		map.put("alcoholicMoney",alcoholicMoney );
		map.put("snackMoney",snackMoney );
		map.put("nonalcoholicMoney",nonalcoholicMoney );
		map.put("nonfoodMoney",nonfoodMoney );
		map.put("instoreMoney", instoreMoney);
		return map;
	}
	//导出分品类销售量
	@ResponseBody
	@RequestMapping("/exportByDepartmentName")
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
			encode = URLEncoder.encode("分品类销售情况.xls", "UTF-8");
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
        List<Department> list = new ArrayList<Department>();
        List<Department> list2 = new ArrayList<Department>();
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryAllDepartments(date, start, end, ArryToListUtil.format(station),people);
			list2 = notOilService.exportAllDepartments(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryAllDepartments(date, start, end, stationid, people);
			list2=notOilService.exportAllDepartments(date, start, end, stationid, people);
		}
		/*private String stationID;
		private Double instoreMoney;//店内服务
		private Double fastfoodMoney;//快餐食品
		private Double perishableMoney;//易腐食品
		private Double lubeMoney;//润滑油
		private Double cigaretteMoney;//烟草
		private Double dailyMoney;//生活日用品
		private Double teamcardMoney;//车队卡
		private Double alcoholicMoney;//酒精饮料
		private Double snackMoney;//零食
		private Double nonalcoholicMoney;//非酒精饮料
		private Double nonfoodMoney;//非食品
*/		for (Department department : list) {
			department.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		
		titleMap.put("minutes", "时间");
		titleMap.put("instoreMoney", "店内服务");
		titleMap.put("fastfoodMoney", "快餐食品");
		titleMap.put("perishableMoney", "易腐食品");
		titleMap.put("lubeMoney", "润滑油");
		titleMap.put("cigaretteMoney", "烟草");
		titleMap.put("dailyMoney", "生活日用品");
		titleMap.put("teamcardMoney", "车队卡");
		titleMap.put("alcoholicMoney", "酒精饮料");
		titleMap.put("snackMoney", "零食");
		titleMap.put("nonalcoholicMoney", "非酒精饮料");
		titleMap.put("nonfoodMoney", "非食品");
		titleMap.put("stationID", "油站编号");
		String sheetName = "分品类销售情况";
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
	//导出分品类销售量
	@ResponseBody
	@RequestMapping("/exportTop10")
	public void exportTop10(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String people){
		String encode="";
		try {
			encode = URLEncoder.encode("便利店Top10.xls", "UTF-8");
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
		List<DataPack> list = new ArrayList<DataPack>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryTop(start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryTop(start,end,stationid,people);
		}
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "商品名");
		titleMap.put("value", "销售金额");
		String sheetName = "便利店Top10";
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
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String date,String people){
		List<NotOil> list = new ArrayList<NotOil>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryRate(date, start, end, ArryToListUtil.format(station),people);
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
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String people){
		String encode="";
		try {
			encode = URLEncoder.encode("便利店开单率.xls", "UTF-8");
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
        List<NotOil> list2 = new ArrayList<NotOil>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryRate(date, start, end, ArryToListUtil.format(station),people);
			list2 = notOilService.exportRate(date, start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryRate(date, start,end,stationid,people);
			list2=notOilService.exportRate(date, start,end,stationid,people);
		}
		for (NotOil notOil : list) {
			notOil.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("minutes", "时间");
		titleMap.put("avgMoney", "开单率");
		titleMap.put("stationID", "加总");
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
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people){
		List<DataPack> list = new ArrayList<DataPack>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryTop(start, end, ArryToListUtil.format(station),people);
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
	/**
	 * 导出便利店Top榜   
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
	 * @param people
	 * @param response
	 */
	@RequestMapping("/exportTop")
	@ResponseBody
	public void exportTop(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people,HttpServletResponse response){
		
		String encode="";
		try {
			encode = URLEncoder.encode("便利店Top10.xls", "UTF-8");
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
        List<DataPack> list = new ArrayList<DataPack>();//准备存放数据
		if(ArryToListUtil.format(station)!=null){
			list = notOilService.queryTop(start, end, ArryToListUtil.format(station),people);
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
			list=notOilService.queryTop(start,end,stationid,people);
		}
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value", "销售额");
		String sheetName = "便利店Top10";
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
	@RequestMapping("/querySearch")
	public Map<String, List> querySearch(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, @RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String productCode,String date) throws ParseException{
		List<DataPack> list = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list=notOilService.querySearch(start, end, ArryToListUtil.format(station),date, productCode);
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
	@ResponseBody
	@RequestMapping("/exportSearch")
	public void exportSearch(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String productCode){
		String encode="";
		try {
			encode = URLEncoder.encode(productCode+"销售情况.xls", "UTF-8");
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
        List<DataPack> list = new ArrayList<DataPack>();
        List<DataPack> list2 = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list=notOilService.querySearch(start, end, ArryToListUtil.format(station),date, productCode);
			list2=notOilService.exportSearch(start, end, ArryToListUtil.format(station),date, productCode);
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
			list=notOilService.querySearch(start, end, stationid,date, productCode);
			list2=notOilService.exportSearch(start, end, stationid,date, productCode);
		}
		for (DataPack dataPack : list) {
			dataPack.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value", "销售额");
		titleMap.put("stationID", "油站编号");
		String sheetName = "销售情况";
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
	//dashboard上的便利店的销售情况
	//本月销售额，今年销量，当月销售额千升比，目标暂无
	//近七日的销售额，目标暂无
	//当月累计达成率Top3
	@ResponseBody
	@RequestMapping("/queryDashBoard")
	public Map<String, Object> queryDashBoard(){
		DecimalFormat df0 = new DecimalFormat("#,###"); 
		List<String> types=new ArrayList<String>();
		types.add("RBA");
		List<String> citys=new ArrayList<String>();
		citys.add("北京");
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Double monthSales=0.0;//月销售额
		Double yearSales=0.0;//年销售额
		Double thousandRateDouble=0.0;//销售额千升比
		String dayMoney="0.0";//当日销售额
		List<String> dates=new ArrayList<String>();//近一周的日期
		List<Double> moneys = new ArrayList<Double>();//近一周对应的数据
		List<NotOil> queryNotOils = notOilService.queryNotOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");//只有一条数据，当月销售额
		if(queryNotOils!=null&&queryNotOils.size()!=0){
			for (NotOil notOil : queryNotOils) {
				monthSales=notOil.getNotOilMoney();
			}
		}
		List<NotOil> queryNotOils2 = notOilService.queryNotOils("year", DateFormatUtils.getYearStart(), new Date(), stationid, "all");//只有一条数据，当月销售额
		if(queryNotOils2!=null){
			for (NotOil notOil : queryNotOils2) {
				yearSales=notOil.getNotOilMoney();
			}
		}
		Double oilLitre=0.0;
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {
				oilLitre=oil.getOilLitre();
				thousandRateDouble=monthSales/oilLitre*1000;
			}
		}
		List<NotOil> queryNotOils3 = notOilService.queryNotOils("day", DateFormatUtils.getWeekStart(), new Date(), stationid, "all");//近一周的销售数据
		if(queryNotOils3!=null){
			for (NotOil notOil : queryNotOils3) {
				dates.add(notOil.getMinutes());
				moneys.add(notOil.getNotOilMoney());
				if(simpleDateFormat.format(new Date()).equals(notOil.getMinutes())){
					dayMoney="￥"+df0.format(notOil.getNotOilMoney());
				}
			}
		}
		List<DataPack> topRate = targetService.queryTopRate(stationid);//销量完成率的Top3
		//continue
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthSales", df0.format(monthSales/10000)+"W");
        map.put("yearSales", df0.format(yearSales/10000)+"W");
		map.put("thousandRate", df0.format(thousandRateDouble));
		map.put("dates", dates);
		map.put("moneys",moneys);
		map.put("topRate", topRate);
		map.put("dayMoney", dayMoney);
		return map;
	}
	@ResponseBody
	@RequestMapping("/queryDashBoardCheng")
	public Map<String, Object> queryDashBoardCheng(){
		DecimalFormat df0 = new DecimalFormat("#,###"); 
		List<String> types=new ArrayList<String>();
		types.add("RBA");
		List<String> citys=new ArrayList<String>();
		citys.add("承德");
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Double monthSales=0.0;//月销售额
		Double yearSales=0.0;//年销售额
		Double thousandRateDouble=0.0;//销售额千升比
		String dayMoney="0.0";//当日销售额
		List<String> dates=new ArrayList<String>();//近一周的日期
		List<Double> moneys = new ArrayList<Double>();//近一周对应的数据
		List<NotOil> queryNotOils = notOilService.queryNotOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");//只有一条数据，当月销售额
		if(queryNotOils!=null&&queryNotOils.size()!=0){
			for (NotOil notOil : queryNotOils) {
				monthSales=notOil.getNotOilMoney();
			}
		}
		List<NotOil> queryNotOils2 = notOilService.queryNotOils("year", DateFormatUtils.getYearStart(), new Date(), stationid, "all");//只有一条数据，当月销售额
		if(queryNotOils2!=null){
			for (NotOil notOil : queryNotOils2) {
				yearSales=notOil.getNotOilMoney();
			}
		}
		Double oilLitre=0.0;
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {
				oilLitre=oil.getOilLitre();
				thousandRateDouble=monthSales/oilLitre*1000;
			}
		}
		List<NotOil> queryNotOils3 = notOilService.queryNotOils("day", DateFormatUtils.getWeekStart(), new Date(), stationid, "all");//近一周的销售数据
		if(queryNotOils3!=null){
			for (NotOil notOil : queryNotOils3) {
				dates.add(notOil.getMinutes());
				moneys.add(notOil.getNotOilMoney());
				if(simpleDateFormat.format(new Date()).equals(notOil.getMinutes())){
					dayMoney="￥"+df0.format(notOil.getNotOilMoney());
				}
			}
		}
		List<DataPack> topRate = targetService.queryTopRate(stationid);//销量完成率的Top3
		//continue
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthSales", df0.format(monthSales/10000)+"W");
		map.put("yearSales", df0.format(yearSales/10000)+"W");
		map.put("thousandRate", df0.format(thousandRateDouble));
		map.put("dates", dates);
		map.put("moneys",moneys);
		map.put("topRate", topRate);
		map.put("dayMoney", dayMoney);
		return map;
	}
	@RequestMapping("/queryDashBoardByStation")
	@ResponseBody
	public Map<String, Object> queryDashboardByStation(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station){
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
		Double monthSales=0.0;//月销售额
		Double yearSales=0.0;//年销售额
		Double thousandRateDouble=0.0;//销售额千升比
		List<String> dates=new ArrayList<String>();//近一周的日期
		List<Double> moneys = new ArrayList<Double>();//近一周对应的数据
		List<NotOil> queryNotOils = notOilService.queryNotOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");//只有一条数据，当月销售额
		if(queryNotOils!=null&&queryNotOils.size()!=0){
			for (NotOil notOil : queryNotOils) {
				monthSales=notOil.getNotOilMoney();
			}
		}
		List<NotOil> queryNotOils2 = notOilService.queryNotOils("year", DateFormatUtils.getYearStart(), new Date(), stationid, "all");//只有一条数据，当月销售额
		if(queryNotOils2!=null){
			for (NotOil notOil : queryNotOils2) {
				yearSales=notOil.getNotOilMoney();
			}
		}
		Double oilLitre=0.0;
		List<Oil> queryOils = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), stationid, "all");
		if(queryOils!=null&&queryOils.size()!=0){
			for (Oil oil : queryOils) {
				oilLitre=oil.getOilLitre();
				thousandRateDouble=monthSales/oilLitre*1000;
			}
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dayMoney="0.0";
		List<NotOil> queryNotOils3 = notOilService.queryNotOils("day", DateFormatUtils.getWeekStart(), new Date(), stationid, "all");//近一周的销售数据
		if(queryNotOils3!=null){
			for (NotOil notOil : queryNotOils3) {
				dates.add(notOil.getMinutes());
				moneys.add(notOil.getNotOilMoney());
				if(simpleDateFormat.format(new Date()).equals(notOil.getMinutes())){
					dayMoney="￥"+df0.format(notOil.getNotOilMoney());
				}
			}
		}
		List<DataPack> topRate = targetService.queryTopRate(stationid);//销量完成率的Top3
		//continue
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("monthSales", df0.format(monthSales/10000)+"W");
		 map.put("yearSales", df0.format(yearSales/10000)+"W");
		 map.put("thousandRate", df0.format(thousandRateDouble));
		 map.put("dates", dates);
		 map.put("moneys",moneys);
		 map.put("topRate", topRate);
		 map.put("dayMoney", dayMoney);
		 return map;
	}
	@ResponseBody
	@RequestMapping("/queryThousandRate")
	public Map<String, Object> queryThousandRate(@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String people){
		List<String> stationid = new ArrayList<String>();
		if(ArryToListUtil.format(station)!=null){
			stationid = ArryToListUtil.format(station);
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
		List<String> dates = new ArrayList<String>();
		List<Double> rate = new ArrayList<Double>();
		List<DataPack> list = notOilService.queryThousandRate(date, start, end, stationid,people);
		if(list!=null&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				rate.add(DoubleFormatUtil.format(dataPack.getValue()));
			}
		}else {
			dates.add("无数据");
			rate.add(0.0);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dates",dates);
		map.put("rate", rate);
		return map;
	}
	/**
	 * 便利店千升比的导出
	 * @param response
	 * @param citys
	 * @param regions
	 * @param sales
	 * @param gasoline
	 * @param locs
	 * @param openDate
	 * @param type
	 * @param station
	 * @param date
	 * @param start
	 * @param end
	 * @param people
	 */
	@ResponseBody
	@RequestMapping("/exportThousandRate")
	public void exportThousandRate(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			String date,Date start,Date end,String people){
		String encode="";
		try {
			encode = URLEncoder.encode("便利店千升比.xls", "UTF-8");
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
		List<String> stationid = new ArrayList<String>();
		if(ArryToListUtil.format(station)!=null){
			stationid = ArryToListUtil.format(station);
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
		List<DataPack> list = notOilService.queryThousandRate(date, start, end, stationid,people);
		for (DataPack dataPack : list) {
			dataPack.setStationID("加总");
		}
		List<DataPack> list2 = notOilService.exportThousandRate(date, start, end, stationid,people);
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value", "千升比");
		titleMap.put("stationID", "油站编号");
		String sheetName = "便利店千升比";
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
