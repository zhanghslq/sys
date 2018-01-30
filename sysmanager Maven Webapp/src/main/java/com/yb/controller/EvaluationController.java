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

import com.yb.entity.Evaluation;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.EvaluationService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/evaluation")
@Scope("prototype")
/**
 * 会员的评价
 * @author Administrator
 *
 */
public class EvaluationController {
	@Resource
	private EvaluationService evaluationService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryTrends")
	@ResponseBody
	public Map<String, List> queryTrends(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			String date,Date start,Date end){
		List<Evaluation> list = new ArrayList<Evaluation>();
		Evaluation evaluation=new Evaluation();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationService.queryTrend(date, start,end,ArryToListUtil.format(station));
			evaluation=evaluationService.queryDistribution(start, end, ArryToListUtil.format(station));
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
			list=evaluationService.queryTrend(date, start,end,stationid);
			evaluation=evaluationService.queryDistribution(start,end,stationid);
		}
		//对集合的处理
		List<String> dates = new ArrayList<String>();
		List<Double> stars = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Evaluation eval : list) {
				dates.add(eval.getDate());
				stars.add(eval.getStar1());
			}
		}else {
			dates.add("无数据");
			stars.add(0.0);
		}
		//对单个的处理
		List<Double> datas = new ArrayList<Double>();
		if(evaluation!=null){
			datas.add(evaluation.getStar1());
			datas.add(evaluation.getStar3());
			datas.add(evaluation.getStar4());
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("stars", stars);
		map.put("datas", datas);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportEvaluation")
	public void exportOils(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			Date start,Date end,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("评价信息.xls", "UTF-8");
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
        List<Evaluation> list = new ArrayList<Evaluation>();
		Evaluation evaluation=new Evaluation();
		if(ArryToListUtil.format(station)!=null){
			list=evaluationService.queryTrend(date, start,end,ArryToListUtil.format(station));
			evaluation=evaluationService.queryDistribution(start, end, ArryToListUtil.format(station));
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
			list=evaluationService.queryTrend(date, start,end,stationid);
			evaluation=evaluationService.queryDistribution(start,end,stationid);
		}
		list.add(new Evaluation("总体满意度", evaluation.getStar1(), null, null, null, null));
		list.add(new Evaluation("油站环境", evaluation.getStar3(), null, null, null, null));
		list.add(new Evaluation("加油速度", evaluation.getStar4(), null, null, null, null));
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("star1", "平均得分");
		String sheetName = "评价信息";
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