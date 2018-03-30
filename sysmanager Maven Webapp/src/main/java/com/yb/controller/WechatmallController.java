package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.WechatmallStatus;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.WechatmallService;
import com.yb.util.ArryToListUtil;


@Controller
@Scope("prototype")
@RequestMapping("/Wechatmall")
public class WechatmallController {
	
	@Resource
	private WechatmallService wechatmallService;
	@ResponseBody
	@RequestMapping("/queryAllStation")
	public List<String> queryAllStation(){
		List<String> list = wechatmallService.queryAllStation();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryByStation")
	public Map<String, Object> queryByStation(@RequestParam(required=false,value="station[]")String [] station,Date start,Date end,String date){
		List<WechatmallStatus> list = wechatmallService.queryByStationAndTime(ArryToListUtil.format(station), start, end, date);
		List<String> days = new ArrayList<String>();
		List<Integer> cancelPoint = new ArrayList<Integer>();
		List<Integer> notpayPoint = new ArrayList<Integer>();
		List<Integer> paidPoint = new ArrayList<Integer>();
		List<Integer> refundedPoint = new ArrayList<Integer>();
		List<Integer> tosendPoint = new ArrayList<Integer>();
		if(list!=null){
			for (WechatmallStatus wechatmallStatus : list) {
				if(wechatmallStatus!=null){
					days.add(wechatmallStatus.getDays());
					cancelPoint.add(wechatmallStatus.getCancel_point());
					notpayPoint.add(wechatmallStatus.getNotpay_point());
					paidPoint.add(wechatmallStatus.getPaid_point());
					refundedPoint.add(wechatmallStatus.getRefunded_point());
					tosendPoint.add(wechatmallStatus.getTosend_point());
				}else{
					days.add("无数据");
					cancelPoint.add(0);
					notpayPoint.add(0);
					paidPoint.add(0);
					refundedPoint.add(0);
					tosendPoint.add(0);
				}
			}
		}else {
			days.add("无数据");
			cancelPoint.add(0);
			notpayPoint.add(0);
			paidPoint.add(0);
			refundedPoint.add(0);
			tosendPoint.add(0);
		}
		Map<String,Object> map = new HashedMap<String,Object>();
		map.put("days", days);
		map.put("cancel", cancelPoint);
		map.put("notpay", notpayPoint);
		map.put("paid", paidPoint);
		map.put("refunded", refundedPoint);
		map.put("tosend", tosendPoint);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportStatus")
	public void exportStatus(@RequestParam(required=false,value="stationCre")String [] stationCre,
			Date start1,Date end1,String date1,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"积分商城交易.xls", "UTF-8");
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
        List<WechatmallStatus> list = wechatmallService.queryByStationAndTime(ArryToListUtil.format(stationCre), start1, end1, date1);
        for (WechatmallStatus wechatmallStatus : list) {
			wechatmallStatus.setStation_id("加总");
		}
        List<WechatmallStatus> list2 = wechatmallService.exportByStationAndTime(ArryToListUtil.format(stationCre), start1, end1, date1);
        list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("station_id", "油站");
		titleMap.put("refunded_number", "已退款单数");
		titleMap.put("refunded_point", "已退款积分");
		titleMap.put("notpay_number", "待付款单数");
		titleMap.put("notpay_point", "待付款积分");
		titleMap.put("tosend_number", "待发货单数");
		titleMap.put("tosend_point", "待发货积分");
		titleMap.put("paid_number", "已完成单数");
		titleMap.put("paid_point", "已完成积分");
		titleMap.put("cancel_number", "已取消单数");
		titleMap.put("cancel_point", "已取消积分");
		String sheetName = "积分商城交易";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start1,end1);
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
	@RequestMapping("/queryTop")
	public Map<String, Object> queryTop(Date start,Date end,@RequestParam(required=false,value="station[]")String [] station){
		List<DataPack> list = wechatmallService.queryTop(start, end, ArryToListUtil.format(station));
		List<String> names = new ArrayList<String>();
		List<Double> datas = new ArrayList<Double>();
		if(list!=null){
			for (DataPack dataPack : list) {
				if(dataPack!=null){
					names.add(dataPack.getName());
					datas.add(dataPack.getValue());
				}else{
					names.add("无数据");
					datas.add(0.0);
				}
			}
		}else {
			names.add("无数据");
			datas.add(0.0);
		}
		Collections.reverse(datas);
		Collections.reverse(names);
		Map<String,Object> map = new HashedMap<String,Object>();
		map.put("names", names);
		map.put("datas", datas);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportTop")
	public void exportTop(@RequestParam(required=false,value="stationCre")String [] stationCre,
			Date start1,Date end1,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"积分商城交易Top（实物）.xls", "UTF-8");
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
        List<DataPack> list = wechatmallService.queryTop(start1, end1, ArryToListUtil.format(stationCre));
        List<DataPack> list1 = wechatmallService.exportTop(start1, end1, ArryToListUtil.format(stationCre));
        int i=0;
        for (DataPack dataPack : list) {
        	i++;
			dataPack.setStationID("Top"+i);
		}
        list.addAll(list1);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "商品名");
		titleMap.put("value", "兑换个数");
		titleMap.put("stationID", "油站名");
		String sheetName = "积分商城交易Top（实物）";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start1,end1);
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
	@RequestMapping("/queryTopAll")
	public Map<String, Object> queryTopAll(Date start,Date end){
		List<DataPack> list = wechatmallService.queryTopAll(start, end);
		List<String> names = new ArrayList<String>();
		List<Double> datas = new ArrayList<Double>();
		if(list!=null){
			for (DataPack dataPack : list) {
				if(dataPack!=null){
					names.add(dataPack.getName());
					datas.add(dataPack.getValue());
				}else{
					names.add("无数据");
					datas.add(0.0);
				}
			}
		}else {
			names.add("无数据");
			datas.add(0.0);
		}
		Collections.reverse(datas);
		Collections.reverse(names);
		Map<String,Object> map = new HashedMap<String,Object>();
		map.put("names", names);
		map.put("datas", datas);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportTopAll")
	public void exportTopAll(Date start1,Date end1,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"积分商城交易Top（全部）.xls", "UTF-8");
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
        List<DataPack> list = wechatmallService.queryTopAll(start1, end1);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "商品名");
		titleMap.put("value", "兑换个数");
		String sheetName = "积分商城交易Top（全部）";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start1,end1);
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
