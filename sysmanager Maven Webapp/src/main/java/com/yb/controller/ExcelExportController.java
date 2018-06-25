package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.VipTag;
import com.yb.excel.test.one.ExportExcelUtils;
import com.yb.service.TagActiveService;
import com.yb.service.VipTagService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/excelExport")
public class ExcelExportController {
	@Resource
	private VipTagService vipTagService;
	@Resource
	private TagActiveService tagActiveService;
	@RequestMapping("/exportVip")
	@ResponseBody
	public void export(HttpServletResponse response,@RequestParam(required=false,value="loyalty")String[] loyalty,@RequestParam(required=false,value="identity")String[] identity,
			@RequestParam(required=false,value="gender")String[] gender,@RequestParam(required=false,value="age")String[] age,
			@RequestParam(required=false,value="type")String[] type,@RequestParam(required=false,value="coupon")String[] coupon,
			@RequestParam(required=false,value="recentOil")String[] recentOil,@RequestParam(required=false,value="recentNotOil")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil")String[] shortOil,Integer page,Integer rows,
			@RequestParam(required=false,value="mopType")String[] mopType,@RequestParam(required=false,value="oilName")String[] oilName,
			@RequestParam(required=false,value="shopName")String[] shopName,@RequestParam(required=false,value="station")String[] station,
			@RequestParam(required=false,value="tagActive")String[] tagActive,@RequestParam(required=false,value="manyStation")String[] manyStation,String area){
		String encode = null;
		if(area==null){
			area="BJSHELL";
		}
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"会员信息导出.xls", "UTF-8");
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
        List<String> format = ArryToListUtil.format(tagActive);
        List<Integer> list2 = new ArrayList<Integer>();
		if(format!=null&&format.size()!=0){
			for (String string : format) {
				list2.add(Integer.valueOf(string));
			}
		}
		List<String> list3=null;
		if(list2!=null&&list2.size()!=0){
			list3= tagActiveService.queryAllVipTag(list2);
		}
        
        List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<VipTag> list=null;
        String[] headers = { "编号", "用户名","手机号","标签"};  
        ExportExcelUtils eeu = new ExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int start=0;
        int count=60000;
        int num=0;
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
    	   list = vipTagService.query(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
    			   ArryToListUtil.format(age),ArryToListUtil.format(type) , 
    			   ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
    			   ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
    			   ArryToListUtil.format(shopName),ArryToListUtil.format(mopType),start,count,list3,ArryToListUtil.format(manyStation),area);
    	   start+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (VipTag vipTag : list) {
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(vipTag.getCarduser_id());  
	        		   rowData.add(vipTag.getName());
	        		   rowData.add(vipTag.getMobilePhone());
	        		   rowData.add(vipTag.getTag());
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
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	@RequestMapping("/exportVip1")
	@ResponseBody
	public void export1(HttpServletResponse response,String date,@RequestParam(required=false,value="station")String[] station,
			@RequestParam(value="oilName",required=false)String[] oils,
			@RequestParam(value="shopName",required=false)String[] shops,String area,
			@RequestParam(value="oilNumber",required=false)Integer[] oilNumber){
		String encode = null;
		if(area==null){
			area="BJSHELL";
		}
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"会员信息导出.xls", "UTF-8");
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
		List<VipTag> list=null;
		String[] headers = { "编号", "用户名","手机号","标签"};  
		ExportExcelUtils eeu = new ExportExcelUtils();  
		HSSFWorkbook workbook = new HSSFWorkbook();
		int start=0;
		int count=60000;
		int num=0;
		if(date==null){
        	date="null";
        }
		//原理就是将所有的数据一起写入，然后再关闭输入流。  
		while (true) {//死循环
			num++;
			list = vipTagService.queryVip(date, ArryToListUtil.format(station), ArryToListUtil.format(oils), ArryToListUtil.format(shops), start, count,area,ArryToListUtil.formatInteger(oilNumber));
			start+=60000;//让开始位置的加60000
			if(list==null||list.size()==0){
				break;//跳出while循环
			}
			if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
				for (VipTag vipTag : list) {
					List<Object>rowData = new LinkedList<Object>();
					rowData.add(vipTag.getCarduser_id());  
					rowData.add(vipTag.getName());
					rowData.add(vipTag.getMobilePhone());
					rowData.add(vipTag.getTag());
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
		} finally{
			
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}
