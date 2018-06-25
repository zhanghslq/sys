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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.FirstExpend;
import com.yb.entity.VipRechargePack;
import com.yb.excel.test.one.DoubleExportExcelUtils;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.FirstExpendService;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/firstExpend")
@Scope("prototype")
public class FirstExpendController {
	
	@Resource
	private FirstExpendService firstExpendService;
	@RequestMapping("/queryAllExpend")
	@ResponseBody
	public Map<String, List<Integer>> queryAllExpend(String area){
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> allNumber = new ArrayList<Integer>();
		List<FirstExpend> list = firstExpendService.queryAllExpend(area);
		Integer all=0;
		for (FirstExpend firstExpend : list) {
			days.add(firstExpend.getDay());
			numbers.add(firstExpend.getNumber());
			if(firstExpend.getNumber()!=null){
				all+=firstExpend.getNumber();
			}
			allNumber.add(all);
		}
		Map<String,List<Integer>> map = new HashMap<>();
		map.put("numbers", numbers);
		map.put("days",days);
		map.put("allNumber", allNumber);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportFirstExpend")
	public void exportFirstExpend(String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(abc+"注册到首次消费间隔天数.xls", "UTF-8");
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
        List<FirstExpend> list = firstExpendService.queryAllExpend(area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("day", "注册到首次消费间隔天数");
		titleMap.put("number", "人数");
		String sheetName = "注册到首次消费间隔天数";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,new Date(),new Date());
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
	@RequestMapping("/queryAllGap")
	@ResponseBody
	public Map<String, List<Integer>> queryAllGap(String area){
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> allNumbers = new ArrayList<Integer>();
		
		List<FirstExpend> list = firstExpendService.queryAllGap(area);
		Integer all=0;
		for (FirstExpend firstExpend : list) {
			days.add(firstExpend.getDay());
			numbers.add(firstExpend.getNumber());
			if(firstExpend.getNumber()!=null){
				all+=firstExpend.getNumber();
			}
			allNumbers.add(all);
		}
		Map<String,List<Integer>> map = new HashMap<>();
		map.put("numbers", numbers);
		map.put("days",days);
		map.put("allNumbers",allNumbers);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportGap")
	public void exportGap(String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(abc+"平均两次消费间隔天数.xls", "UTF-8");
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
        List<FirstExpend> list = firstExpendService.queryAllGap(area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("day", "平均两次消费间隔天数");
		titleMap.put("number", "人数");
		String sheetName = "平均两次消费间隔天数";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,new Date(),new Date());
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
	@RequestMapping("queryLastDeal")
	@ResponseBody
	public Map<String, List<Integer>> queryLastDeal(String area){
		List<FirstExpend> list= firstExpendService.queryLastDeal(area);
		List<Integer> days = new ArrayList<Integer>();
		List<Integer> numbers = new ArrayList<Integer>();
			for (FirstExpend firstExpend : list) {
				days.add(firstExpend.getDay());
				numbers.add(firstExpend.getNumber());
			}
		Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
		map.put("days", days);
		map.put("numbers", numbers);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportLastDeal")
	public void exportLastDeal(String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abc+"距离最后一次消费的天数.xls", "UTF-8");
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
        List<FirstExpend> list= firstExpendService.queryLastDeal(area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("day", "距离最后一次消费的天数");
		titleMap.put("number", "人数");
		String sheetName = "距离最后一次消费的天数";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,new Date(),new Date());
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
	@RequestMapping("/vipDealMonth")
	@ResponseBody
	public List<List<Double>> queryDealMonth(Date start,Date end,String area){
		List<VipRechargePack> list = firstExpendService.queryDealMonth(start, end,area);
		List<List<Double>> lists = new ArrayList<List<Double>>();
		if(list!=null&&list.size()!=0){//查询结果有数据的时候
			for (VipRechargePack vipRechargePack : list) {
				List<Double> datas = new ArrayList<Double>();
				datas.add(vipRechargePack.getNumber());
				datas.add(DoubleFormatUtil.format(vipRechargePack.getAmount()));
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
	@RequestMapping("/exportvipDealMonth")
	public void exportvipDealMonth(Date start,Date end,String area,HttpServletResponse response){
		String encode = null;
		try {
			encode = URLEncoder.encode("会员消费信息导出.xls", "UTF-8");
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
        String[] headers = { "消费次数", "平均消费额"};  
        DoubleExportExcelUtils eeu = new DoubleExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int startNum=0;
        int size=60000;
        int num=0;
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
    	   list =firstExpendService.exportDealMonth(start, end, area, startNum, size);
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
	    			eeu.exportExcel(workbook, num-1, "会员信息"+num, headers, data);
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
