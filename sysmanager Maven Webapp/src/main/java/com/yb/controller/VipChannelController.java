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

import com.yb.entity.Channel;
import com.yb.entity.DataPack;
import com.yb.entity.InterPack;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.VipChannelService;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/vipChannel")
@Scope("prototype")
public class VipChannelController {
	@Resource
	private VipChannelService vipChannelService;
	
	@RequestMapping("/queryChannel")
	@ResponseBody
	public List<InterPack> queryChannel(Date start,Date end,String area){
		Channel channel = vipChannelService.queryChannel(start, end,area);
		ArrayList<InterPack> list = new ArrayList<InterPack>();
		if(channel!=null){
			list.add(new InterPack("未知",channel.getUnknown())) ;
			list.add(new InterPack("支付宝",channel.getAplipay()));
			list.add(new InterPack("手机",channel.getMobile()));
			list.add(new InterPack("PC",channel.getPc()));
			list.add(new InterPack("微信",channel.getWechat()));
		}else {
			list.add(new InterPack("未知",0)) ;
			list.add(new InterPack("支付宝",0));
			list.add(new InterPack("手机",0));
			list.add(new InterPack("PC",0));
			list.add(new InterPack("微信",0));
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/exportVipComeFrom")
	public void exportOils(String date,Date start,Date end,String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abc+"会员来源.xls", "UTF-8");
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
        Channel channel = vipChannelService.queryChannel(start, end,area);
        List<Channel> list = new ArrayList<Channel>();
        list.add(channel);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("unknown", "未知");
		titleMap.put("pc", "PC端");
		titleMap.put("mobile", "手机");
		titleMap.put("wechat", "微信");
		titleMap.put("aplipay", "支付宝");
		String sheetName = "会员来源信息";
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
	//30天转化率，先放在VipChannel
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryRate")
	public Map<String, List> queryRate(Date start,Date end,String query,String area){
		List<DataPack> list = vipChannelService.queryRate(start, end,query,area);
		List<String> days = new ArrayList<String>();
		List<Double> rates = new ArrayList<Double>();
		if(list!=null){
			for (DataPack dataPack : list) {
				days.add(dataPack.getName());
				rates.add(DoubleFormatUtil.format(dataPack.getValue()*100));
			}
		}else {
			days.add("无数据");
			rates.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("rates", rates);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportRate")
	public void exportOils(HttpServletResponse response,Date start,Date end,String query,String area){
		String encode="";
		String abcString="";
		try {
			if("BJSHELL".equals(area)){
				abcString="北京";
			}
			if("CDSHELL".equals(area)){
				abcString="承德";
			}
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abcString+"日新增会员转化率.xls", "UTF-8");
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
        List<DataPack> list = vipChannelService.queryRate(start, end,query,area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value", "转化率");
		String sheetName = "日新增会员转化率";
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
	
}
