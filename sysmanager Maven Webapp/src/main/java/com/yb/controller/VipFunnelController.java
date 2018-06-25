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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DouPack;
import com.yb.entity.InterPack;
import com.yb.entity.VipFunnel;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.VipFunnelService;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/vipFunnel")
@Scope("prototype")
public class VipFunnelController {
	@Resource
	private VipFunnelService vipFunnelService;
	@RequestMapping("/queryAllMonth")
	@ResponseBody
	public List<String> queryAllMonth(String area){
		List<String> list = vipFunnelService.queryAllMonth(area);
		return list;
	}
	@RequestMapping("/queryVipFunnel")
	@ResponseBody
	public List<InterPack> queryVipFunnel(String month,String area){
		VipFunnel funnel = vipFunnelService.queryVipFunnel(month,area);
		List<InterPack> list = new ArrayList<InterPack>();
		if(funnel!=null){
			list.add(new InterPack("至少消费一次的", funnel.getAtLeastOne()));
			list.add(new InterPack("活跃会员",funnel.getLiveness()));
			list.add(new InterPack("会员总数",funnel.getSum()));
		}else {
			list.add(new InterPack("至少消费一次的", 0));
			list.add(new InterPack("活跃会员",0));
			list.add(new InterPack("会员总数",0));
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/exportVipFunnel")
	public void exportVipFunnel(String area,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"会员活跃情况.xls", "UTF-8");
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
        List<VipFunnel> list = vipFunnelService.queryAllVipFunnel(area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("month", "时间");
		titleMap.put("sum", "会员总数");
		titleMap.put("atLeastOne", "至少消费一次的");
		titleMap.put("liveness", "活跃会员");
		String sheetName = "会员活跃情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,new Date() ,new Date());
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
	//流失会员人数及占比
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryDrain")
	public Map<String, List> queryDrain(String date,Date start,Date end,String area){
		List<DouPack> list = vipFunnelService.queryDrain(date,start,end,area);
		List<String> month = new ArrayList<String>();
		List<Double> drain = new ArrayList<Double>();
		List<Double> rate = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DouPack douPack : list) {
				drain.add(douPack.getDrainNum());
				rate.add(DoubleFormatUtil.format(douPack.getOther()*100));
				month.add(douPack.getMonth());
			}
		}else {
			drain.add(0.0);
			rate.add(0.0);
			month.add("无数据");
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("drain", drain);
		map.put("rate", rate);
		map.put("month", month);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportDrain")
	public void exportDrain(String date,Date start,Date end,String area,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"会员流失及占比.xls", "UTF-8");
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
        List<DouPack> list = vipFunnelService.queryDrain(date,start,end,area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("month", "时间");
		titleMap.put("drainNum", "流失人数");
		titleMap.put("other", "流失占比");
		String sheetName = "会员流失及占比";
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
	
}
