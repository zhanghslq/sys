package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import com.yb.entity.DataPack;
import com.yb.entity.HHT;
import com.yb.entity.Mop;
import com.yb.entity.Station;
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
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
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
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate));
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
		/*private Double EPSMoney;//EPS会员
		private Double couponMoney;//优惠券
		private Double vipCouponMoney;//会员优惠券
		private Double creditCardMoney;//信用卡
		private Double teamCardMoney;//壳牌车队卡
		private Double wechatMoney;//微信支付
		private Double alipayMoney;//支付宝支付
		private Double chequeMoney;//支票支付
		private Double didiMoney;//滴滴支付
		private Double cashMoney;//现金
		private Double ePaymentMoney;//电子支付优惠
		private Double baiduMoney;//百度支付
		private Double thirdPaymentMoney;//第三方卡
		private Double carInMoney;//车到收款
		private Double unionpayCouponMoney;//银联钱包优惠券
*/		List<Double> EPSMoney=new ArrayList<Double>();
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
				ePaymentMoney.add(mop.getePaymentMoney());
				baiduMoney.add(mop.getBaiduMoney());
				thirdPaymentMoney.add(mop.getThirdPaymentMoney());
				carInMoney.add(mop.getCarInMoney());
				unionpayCouponMoney.add(mop.getUnionpayCouponMoney());
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
		System.out.println(mop);
		
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
		return map;
	}
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping("/queryHHTIPT")
	@ResponseBody
	public Map<String,List> queryHHTIPT(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people){
		HHT hht=null;
		if(ArryToListUtil.format(station)!=null){
			hht=mopService.queryHHT(start, end,ArryToListUtil.format(station),people);
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
			hht=mopService.queryHHT(start, end,stationid,people);
		}
		
		List<DataPack> all = new ArrayList<DataPack>();
		List<String> mop = mopService.queryAllMop();
		if(hht!=null){
			all.add(new DataPack("HHT支付",DoubleFormatUtil.format(hht.getHhtMoney())));
			all.add(new DataPack("IPT支付",DoubleFormatUtil.format(hht.getIptMoney())));
		}else {
			all.add(new DataPack("无数据", 0.0));
		}
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("all", all);
		map.put("mop", mop);
		return map;
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryHHT")
	@ResponseBody
	public Map<String,List> queryHHT(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people,String date){
		List<DataPack> mophht=null;
		List<Mop> queryHHTList=null;
		if(ArryToListUtil.format(station)!=null){
			mophht=mopService.queryMophht(start, end, ArryToListUtil.format(station),people);
			queryHHTList = mopService.queryHHTList(start, end, ArryToListUtil.format(station), date, people);
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
				ePaymentMoney.add(mop.getePaymentMoney());
				baiduMoney.add(mop.getBaiduMoney());
				thirdPaymentMoney.add(mop.getThirdPaymentMoney());
				carInMoney.add(mop.getCarInMoney());
				unionpayCouponMoney.add(mop.getUnionpayCouponMoney());
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
		return map;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryIPT")
	@ResponseBody
	public Map<String,List> queryIPT(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,@RequestParam(required=false,value="station[]")String [] station,
			Date start,Date end,String people,String date){
		List<DataPack> mopipt=null;
		List<Mop> queryIPTList =null;
		if(ArryToListUtil.format(station)!=null){
			mopipt=mopService.queryMopipt(start, end, ArryToListUtil.format(station),people);
			queryIPTList = mopService.queryIPTList(start, end, ArryToListUtil.format(station), date, people);
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
				ePaymentMoney.add(mop.getePaymentMoney());
				baiduMoney.add(mop.getBaiduMoney());
				thirdPaymentMoney.add(mop.getThirdPaymentMoney());
				carInMoney.add(mop.getCarInMoney());
				unionpayCouponMoney.add(mop.getUnionpayCouponMoney());
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
		return map;
	}
}
