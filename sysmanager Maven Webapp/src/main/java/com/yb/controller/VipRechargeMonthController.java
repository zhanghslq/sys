package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.VipRechargeMonth;
import com.yb.entity.VipRechargePack;
import com.yb.excel.test.one.DoubleExportExcelUtils;
import com.yb.service.VipRechargeMonthService;

@Controller
@RequestMapping("/vipRechargeMonth")
public class VipRechargeMonthController {
	@Resource
	private VipRechargeMonthService vipRechargeMonthService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(Date start,Date end,String area){
		List<VipRechargeMonth> list = vipRechargeMonthService.query(start, end,area);
		List<Double> avgRecharges = new ArrayList<Double>();
		List<Double> rechargeTotals = new ArrayList<Double>();
		List<Integer> peoples = new ArrayList<Integer>();
		List<String> dates = new ArrayList<String>();
		if(list!=null&&list.size()!=0){
			for (VipRechargeMonth vipRechargeMonth : list) {
				peoples.add(vipRechargeMonth.getPeoples());
				avgRecharges.add(vipRechargeMonth.getAvgRecharge());
				rechargeTotals.add(vipRechargeMonth.getRechargeTotal());
				dates.add(vipRechargeMonth.getDate());
			}
		}else {
			peoples.add(0);
			avgRecharges.add(0.0);
			rechargeTotals.add(0.0);
			dates.add("无数据");
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("peoples", peoples);
		map.put("avgRecharges", avgRecharges);
		map.put("rechargeTotals", rechargeTotals);
		map.put("dates", dates);
		return map;
	}
	@ResponseBody
	@RequestMapping("/querySingle")
	public List<List<Double>> querySingle(Date start,Date end,String area){
		List<VipRechargePack> list = vipRechargeMonthService.querySingle(start, end,area);
		List<List<Double>> lists = new ArrayList<List<Double>>();
		if(list!=null&&list.size()!=0){//查询结果有数据的时候
			for (VipRechargePack vipRechargePack : list) {
				List<Double> datas = new ArrayList<Double>();
				datas.add(vipRechargePack.getNumber());
				datas.add(vipRechargePack.getAmount());
				lists.add(datas);
			}
		}else {//无数据的时候
			List<Double> datas = new ArrayList<Double>();
			datas.add(0.0);
			datas.add(0.0);
			lists.add(datas);
		}
		return lists;
	}
	@ResponseBody
	@RequestMapping("/exportSingle")
	public void exportSingle(Date start,Date end,String area,HttpServletResponse response){
		String encode = null;
		try {
			encode = URLEncoder.encode("会员充值信息导出.xls", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        OutputStream os = null;
		try {
			os = new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        
        List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<VipRechargePack> list=null;
        String[] headers = { "充值次数", "平均充值金额"};  
        DoubleExportExcelUtils eeu = new DoubleExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int startNum=0;
        int size=60000;
        int num=0;
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
    	   list =vipRechargeMonthService.exportSingle(start, end, area, startNum, size);
    	   startNum+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (VipRechargePack vipRechargePack : list) {
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(vipRechargePack.getNumber());
	        		   rowData.add(vipRechargePack.getAmount());  
	        		   data.add(rowData);
	        	   }
	        	   try {
	    			eeu.exportExcel(workbook, num-1, "会员充值信息"+num, headers, data);
	    			data.clear();//把数据写入之后清除，等待下次的数据
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
			}
        }//while结束
        try {
			workbook.write(os);
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
