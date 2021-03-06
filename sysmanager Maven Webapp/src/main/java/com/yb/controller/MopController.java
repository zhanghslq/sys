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

import com.yb.entity.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.MopService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/mop")
@Scope("prototype")
public class MopController {

	@Resource
	private MopService mopService;
	@Resource
	private StationService stationService;
	@ResponseBody
	@RequestMapping("/queryAllMop")
	public List<String> queryAllMop(){
		List<String> list = mopService.queryAllMop();
		return list;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryMop")
	public Map<String, List> queryMop(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String query,String people,String date){
		List<DataPack> list = new ArrayList<DataPack>();
		HHT hht=null;
		List<Mop> queryMopList=null;
		if(ArryToListUtil.format(station)!=null){
			list=mopService.queryMop(start, end,ArryToListUtil.format(station),people);
			hht=mopService.queryHHT(start, end,ArryToListUtil.format(station),people);
			queryMopList = mopService.queryMopList(start,end, ArryToListUtil.format(station), date, people);
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
			list=mopService.queryMop(start, end,stationid,people);
			hht=mopService.queryHHT(start, end,stationid,people);
			queryMopList = mopService.queryMopList(start, end, stationid, date, people);
		}
		List<Double> EPSMoney=new ArrayList<Double>();
		List<Double> couponMoney=new ArrayList<Double>();
		List<Double> vipCouponMoney=new ArrayList<Double>();
		List<Double> creditCardMoney=new ArrayList<Double>();
		List<Double> teamCardMoney=new ArrayList<Double>();
		List<Double> wechatMoney=new ArrayList<Double>();
		List<Double> alipayMoney=new ArrayList<Double>();
		List<Double> chequeMoney=new ArrayList<Double>();
		List<Double> didiMoney=new ArrayList<Double>();
		List<Double> cashMoney=new ArrayList<Double>();
		List<Double> ePaymentMoney=new ArrayList<Double>();
		List<Double> baiduMoney=new ArrayList<Double>();
		List<Double> thirdPaymentMoney=new ArrayList<Double>();
		List<Double> carInMoney=new ArrayList<Double>();
		List<Double> unionpayCouponMoney=new ArrayList<Double>();
		List<Double> zebpayMoney=new ArrayList<Double>();

		List<Double> microcarMoney=new ArrayList<Double>();
		List<Double> bankdiscountMoney=new ArrayList<Double>();
		List<String> dates=new ArrayList<String>();
		if(queryMopList!=null){
			for (Mop mop : queryMopList) {
				dates.add(mop.getDays());
				EPSMoney.add(mop.getEPSMoney());
				couponMoney.add(mop.getCouponMoney());
				vipCouponMoney.add(mop.getVipCouponMoney());
				creditCardMoney.add(mop.getCreditCardMoney());
				teamCardMoney.add(mop.getTeamCardMoney());
				wechatMoney.add(mop.getWechatMoney());
				alipayMoney.add(mop.getAlipayMoney());
				chequeMoney.add(mop.getChequeMoney());
				didiMoney.add(mop.getDidiMoney());
				cashMoney.add(mop.getCashMoney());
				ePaymentMoney.add(mop.getEPaymentMoney());
				baiduMoney.add(mop.getBaiduMoney());
				thirdPaymentMoney.add(mop.getThirdPaymentMoney());
				carInMoney.add(mop.getCarInMoney());
				unionpayCouponMoney.add(mop.getUnionpayCouponMoney());
				zebpayMoney.add(mop.getZebpayMoney());

				microcarMoney.add(mop.getMicrocarMoney());

				bankdiscountMoney.add(mop.getBankdiscountMoney());

			}
		}

		for (DataPack dataPack : list) {
			String name = dataPack.getName();
			if("EPS会员".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#FBCE07"));
			}else if("优惠券".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#DD1D21"));
			}else if("会员优惠券".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#89CFDC"));
			}else if("信用卡".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#009EB4"));
			}else if("壳牌车队卡".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#003C88"));
			}else if("微信支付".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#BA95BE"));
			}else if("支付宝支付".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#641964"));
			}else if("支票".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#FFEAC2"));
			}else if("滴滴支付".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#EB8705"));
			}else if("现金".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#743410"));
			}else if("电子支付优惠".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#BED50F"));
			}else if("百度支付".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#008433"));
			}else if("第三方卡".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#595959"));
			}else if("车到收款".equals(name)){
				dataPack.setItemStyle(new ItemStyle("#7F7F7F"));
			}else if("银联钱包优惠券".equals(name)){
				dataPack.setItemStyle(new ItemStyle("pink"));
			}else if("斑马支付".equals(name)){
				dataPack.setItemStyle(new ItemStyle("black"));
			}else if("微车支付".equals(name)){
				dataPack.setItemStyle(new ItemStyle("skyblue"));
			}else if("银行异业优惠".equals(name)){
				dataPack.setItemStyle(new ItemStyle("green"));
			}
		}
		
		List<DataPack> all = new ArrayList<DataPack>();
		if(hht!=null){
			all.add(new DataPack("HHT支付",DoubleFormatUtil.format(hht.getHhtMoney())));
			all.add(new DataPack("IPT支付",DoubleFormatUtil.format(hht.getIptMoney())));
		}else {
			all.add(new DataPack("无数据", 0.0));
		}
		List<String> mop = mopService.queryAllMop();
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("mop", mop);
		map.put("data", list);
		map.put("all", all);
		map.put("dates",dates);
		map.put("EPSMoney",EPSMoney);
		map.put("couponMoney", couponMoney);
		map.put("vipCouponMoney",vipCouponMoney );
		map.put("creditCardMoney", creditCardMoney);
		map.put("teamCardMoney", teamCardMoney);
		map.put("wechatMoney", wechatMoney);
		map.put("alipayMoney", alipayMoney);
		map.put("chequeMoney", chequeMoney);
		map.put("didiMoney", didiMoney);
		map.put("cashMoney", cashMoney);
		map.put("ePaymentMoney",ePaymentMoney );
		map.put("baiduMoney", baiduMoney);
		map.put("thirdPaymentMoney", thirdPaymentMoney);
		map.put("carInMoney",carInMoney );
		map.put("unionpayCouponMoney", unionpayCouponMoney);
		map.put("zebpayMoney", zebpayMoney);
		map.put("microcarMoney", microcarMoney);
		map.put("bankdiscountMoney", bankdiscountMoney);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportMop")
	public void exportMop(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			Date start,Date end,String date,String people){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"支付方式整体情况.xls", "UTF-8");
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
		List<Mop> queryMopList=null;
		List<Mop> queryMopList2=null;
		if(ArryToListUtil.format(station)!=null){
			queryMopList = mopService.queryMopList(start,end, ArryToListUtil.format(station), date, people);
			queryMopList2 = mopService.exportMopList(start,end, ArryToListUtil.format(station), date, people);
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
			queryMopList = mopService.queryMopList(start, end, stationid, date, people);
			queryMopList2 = mopService.exportMopList(start, end, stationid, date, people);
		}
		for (Mop mop : queryMopList) {
			mop.setStationID("加总");
		}
		queryMopList.addAll(queryMopList2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("stationID", "油站编号");
		titleMap.put("EPSMoney", "EPS会员");
		titleMap.put("couponMoney", "优惠券");
		titleMap.put("vipCouponMoney", "会员优惠券");
		titleMap.put("creditCardMoney", "信用卡");
		titleMap.put("teamCardMoney", "壳牌车队卡");
		titleMap.put("wechatMoney", "微信支付");
		titleMap.put("alipayMoney", "支付宝支付");
		titleMap.put("chequeMoney", "支票支付");
		titleMap.put("didiMoney", "滴滴支付");
		titleMap.put("cashMoney", "现金");
		titleMap.put("EPaymentMoney", "电子支付优惠");
		titleMap.put("baiduMoney", "百度支付");
		titleMap.put("thirdPaymentMoney", "第三方卡");
		titleMap.put("carInMoney", "车到收款");
		titleMap.put("unionpayCouponMoney", "银联钱包优惠券");
		titleMap.put("zebpayMoney", "斑马支付");
		titleMap.put("microcarMoney", "微车支付");
		titleMap.put("bankdiscountMoney", "银行异业优惠");
		String sheetName = "支付方式整体情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(queryMopList, titleMap, sheetName,start,end);
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
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryHHT")
	@ResponseBody
	public Map<String,List> queryHHT(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people,String date){
		List<DataPack> mophht=null;
		List<Mop> queryHHTList=null;
		if(ArryToListUtil.format(station)!=null){
			mophht=mopService.queryMophht(start, end, ArryToListUtil.format(station),people);
			queryHHTList = mopService.queryHHTList(start, end, ArryToListUtil.format(station), date, people);
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
			mophht=mopService.queryMophht(start, end, stationid,people);
			queryHHTList = mopService.queryHHTList(start, end, stationid, date, people);
		}
		List<Double> EPSMoney=new ArrayList<Double>();
		List<Double> couponMoney=new ArrayList<Double>();
		List<Double> vipCouponMoney=new ArrayList<Double>();
		List<Double> creditCardMoney=new ArrayList<Double>();
		List<Double> teamCardMoney=new ArrayList<Double>();
		List<Double> wechatMoney=new ArrayList<Double>();
		List<Double> alipayMoney=new ArrayList<Double>();
		List<Double> chequeMoney=new ArrayList<Double>();
		List<Double> didiMoney=new ArrayList<Double>();
		List<Double> cashMoney=new ArrayList<Double>();
		List<Double> ePaymentMoney=new ArrayList<Double>();
		List<Double> baiduMoney=new ArrayList<Double>();
		List<Double> thirdPaymentMoney=new ArrayList<Double>();
		List<Double> carInMoney=new ArrayList<Double>();
		List<Double> unionpayCouponMoney=new ArrayList<Double>();
		List<Double> bankdiscountMoney=new ArrayList<Double>();

		List<String> dates=new ArrayList<String>();
		
		if(queryHHTList!=null){
			for (Mop mop : queryHHTList) {
				dates.add(mop.getDays());
				EPSMoney.add(mop.getEPSMoney());
				couponMoney.add(mop.getCouponMoney());
				vipCouponMoney.add(mop.getVipCouponMoney());
				creditCardMoney.add(mop.getCreditCardMoney());
				teamCardMoney.add(mop.getTeamCardMoney());
				wechatMoney.add(mop.getWechatMoney());
				alipayMoney.add(mop.getAlipayMoney());
				chequeMoney.add(mop.getChequeMoney());
				didiMoney.add(mop.getDidiMoney());
				cashMoney.add(mop.getCashMoney());
				ePaymentMoney.add(mop.getEPaymentMoney());
				baiduMoney.add(mop.getBaiduMoney());
				thirdPaymentMoney.add(mop.getThirdPaymentMoney());
				carInMoney.add(mop.getCarInMoney());
				unionpayCouponMoney.add(mop.getUnionpayCouponMoney());
				bankdiscountMoney.add(mop.getBankdiscountMoney());
			}
		}
		
		List<String> mop = mopService.queryAllMop();
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("hht", mophht);
		map.put("mop", mop);
		
		map.put("dates",dates);
		map.put("EPSMoney",EPSMoney);
		map.put("couponMoney", couponMoney);
		map.put("vipCouponMoney",vipCouponMoney );
		map.put("creditCardMoney", creditCardMoney);
		map.put("teamCardMoney", teamCardMoney);
		map.put("wechatMoney", wechatMoney);
		map.put("alipayMoney", alipayMoney);
		map.put("chequeMoney", chequeMoney);
		map.put("didiMoney", didiMoney);
		map.put("cashMoney", cashMoney);
		map.put("ePaymentMoney",ePaymentMoney );
		map.put("baiduMoney", baiduMoney);
		map.put("thirdPaymentMoney", thirdPaymentMoney);
		map.put("carInMoney",carInMoney );
		map.put("unionpayCouponMoney", unionpayCouponMoney);
		map.put("bankdiscountMoney", bankdiscountMoney);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportHHT")
	public void exportHHT(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			Date start,Date end,String date,String people){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"HHT支付情况.xls", "UTF-8");
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
        List<Mop> queryHHTList=null;
        List<Mop> queryHHTList2=null;
		if(ArryToListUtil.format(station)!=null){
			queryHHTList = mopService.queryHHTList(start, end, ArryToListUtil.format(station), date, people);
			queryHHTList2 = mopService.exportHHTList(start, end, ArryToListUtil.format(station), date, people);
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
			queryHHTList = mopService.queryHHTList(start, end, stationid, date, people);
			queryHHTList2 = mopService.exportHHTList(start, end, stationid, date, people);
		}
		for (Mop mop : queryHHTList) {
			mop.setStationID("加总");
		}
		queryHHTList.addAll(queryHHTList2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("stationID", "油站编号");
		titleMap.put("EPSMoney", "EPS会员");
		titleMap.put("couponMoney", "优惠券");
		titleMap.put("vipCouponMoney", "会员优惠券");
		titleMap.put("creditCardMoney", "信用卡");
		titleMap.put("teamCardMoney", "壳牌车队卡");
		titleMap.put("wechatMoney", "微信支付");
		titleMap.put("alipayMoney", "支付宝支付");
		titleMap.put("chequeMoney", "支票支付");
		titleMap.put("didiMoney", "滴滴支付");
		titleMap.put("cashMoney", "现金");
		titleMap.put("EPaymentMoney", "电子支付优惠");
		titleMap.put("baiduMoney", "百度支付");
		titleMap.put("thirdPaymentMoney", "第三方卡");
		titleMap.put("carInMoney", "车到收款");
		titleMap.put("unionpayCouponMoney", "银联钱包优惠券");
		titleMap.put("bankdiscountMoney", "银行异业优惠");
		String sheetName = "HHT支付情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(queryHHTList, titleMap, sheetName,start,end);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryIPT")
	@ResponseBody
	public Map<String,List> queryIPT(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people,String date){
		List<DataPack> mopipt=null;
		List<Mop> queryIPTList =null;
		if(ArryToListUtil.format(station)!=null){
			mopipt=mopService.queryMopipt(start, end, ArryToListUtil.format(station),people);
			queryIPTList = mopService.queryIPTList(start, end, ArryToListUtil.format(station), date, people);
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
			mopipt=mopService.queryMopipt(start, end, stationid,people);
			queryIPTList = mopService.queryIPTList(start, end, stationid, date, people);
		}
		List<Double> EPSMoney=new ArrayList<Double>();
		List<Double> couponMoney=new ArrayList<Double>();
		List<Double> vipCouponMoney=new ArrayList<Double>();
		List<Double> creditCardMoney=new ArrayList<Double>();
		List<Double> teamCardMoney=new ArrayList<Double>();
		List<Double> wechatMoney=new ArrayList<Double>();
		List<Double> alipayMoney=new ArrayList<Double>();
		List<Double> chequeMoney=new ArrayList<Double>();
		List<Double> didiMoney=new ArrayList<Double>();
		List<Double> cashMoney=new ArrayList<Double>();
		List<Double> ePaymentMoney=new ArrayList<Double>();
		List<Double> baiduMoney=new ArrayList<Double>();
		List<Double> thirdPaymentMoney=new ArrayList<Double>();
		List<Double> carInMoney=new ArrayList<Double>();
		List<Double> unionpayCouponMoney=new ArrayList<Double>();
		List<Double> zebpayMoney=new ArrayList<Double>();
		List<Double> microcarMoney=new ArrayList<Double>();
		List<Double> bankdiscountMoney=new ArrayList<Double>();
		List<String> dates=new ArrayList<String>();
		
		if(queryIPTList!=null){
			for (Mop mop : queryIPTList) {
				dates.add(mop.getDays());
				EPSMoney.add(mop.getEPSMoney());
				couponMoney.add(mop.getCouponMoney());
				vipCouponMoney.add(mop.getVipCouponMoney());
				creditCardMoney.add(mop.getCreditCardMoney());
				teamCardMoney.add(mop.getTeamCardMoney());
				wechatMoney.add(mop.getWechatMoney());
				alipayMoney.add(mop.getAlipayMoney());
				chequeMoney.add(mop.getChequeMoney());
				didiMoney.add(mop.getDidiMoney());
				cashMoney.add(mop.getCashMoney());
				ePaymentMoney.add(mop.getEPaymentMoney());
				baiduMoney.add(mop.getBaiduMoney());
				thirdPaymentMoney.add(mop.getThirdPaymentMoney());
				carInMoney.add(mop.getCarInMoney());
				unionpayCouponMoney.add(mop.getUnionpayCouponMoney());
				zebpayMoney.add(mop.getZebpayMoney());
				microcarMoney.add(mop.getMicrocarMoney());
				bankdiscountMoney.add(mop.getBankdiscountMoney());
			}
		}
		
		List<String> mop = mopService.queryAllMop();
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("ipt", mopipt);
		map.put("mop", mop);
		
		map.put("dates",dates);
		map.put("EPSMoney",EPSMoney);
		map.put("couponMoney", couponMoney);
		map.put("vipCouponMoney",vipCouponMoney );
		map.put("creditCardMoney", creditCardMoney);
		map.put("teamCardMoney", teamCardMoney);
		map.put("wechatMoney", wechatMoney);
		map.put("alipayMoney", alipayMoney);
		map.put("chequeMoney", chequeMoney);
		map.put("didiMoney", didiMoney);
		map.put("cashMoney", cashMoney);
		map.put("ePaymentMoney",ePaymentMoney );
		map.put("baiduMoney", baiduMoney);
		map.put("thirdPaymentMoney", thirdPaymentMoney);
		map.put("carInMoney",carInMoney );
		map.put("unionpayCouponMoney", unionpayCouponMoney);
		map.put("zebpayMoney", zebpayMoney);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportIPT")
	public void exportIPT(HttpServletResponse response,@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,
			Date start,Date end,String date,String people){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"IPT支付情况.xls", "UTF-8");
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
		List<Mop> queryIPTList =null;
		List<Mop> queryIPTList2 =null;
		if(ArryToListUtil.format(station)!=null){
			queryIPTList = mopService.queryIPTList(start, end, ArryToListUtil.format(station), date, people);
			queryIPTList2 = mopService.exportIPTList(start, end, ArryToListUtil.format(station), date, people);
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
			queryIPTList = mopService.queryIPTList(start, end, stationid, date, people);
			queryIPTList2 = mopService.exportIPTList(start, end, stationid, date, people);
		}
		for (Mop mop : queryIPTList) {
			mop.setStationID("加总");
		}
		queryIPTList.addAll(queryIPTList2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("stationID", "油站编号");
		titleMap.put("EPSMoney", "EPS会员");
		titleMap.put("couponMoney", "优惠券");
		titleMap.put("vipCouponMoney", "会员优惠券");
		titleMap.put("creditCardMoney", "信用卡");
		titleMap.put("teamCardMoney", "壳牌车队卡");
		titleMap.put("wechatMoney", "微信支付");
		titleMap.put("alipayMoney", "支付宝支付");
		titleMap.put("chequeMoney", "支票支付");
		titleMap.put("didiMoney", "滴滴支付");
		titleMap.put("cashMoney", "现金");
		titleMap.put("EPaymentMoney", "电子支付优惠");
		titleMap.put("baiduMoney", "百度支付");
		titleMap.put("thirdPaymentMoney", "第三方卡");
		titleMap.put("carInMoney", "车到收款");
		titleMap.put("unionpayCouponMoney", "银联钱包优惠券");
		titleMap.put("zebpayMoney", "斑马支付");
		titleMap.put("microcarMoney", "微车支付");
		titleMap.put("bankdiscountMoney", "银行异业优惠");
		String sheetName = "IPT支付情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(queryIPTList, titleMap, sheetName,start,end);
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
