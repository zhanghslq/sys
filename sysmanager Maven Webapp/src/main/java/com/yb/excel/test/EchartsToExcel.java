package com.yb.excel.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yb.excel.use.Employee;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.excel.util.ExportExcelUtil;

public class EchartsToExcel {
	private static final SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy-MM-dd");
	 public static void main(String[] args) {
	        File dir = new File("d:\\EchrtsToExcel\\"+simpleDateformat.format(new Date()));
	        List<Employee> staffs = new ArrayList<Employee>();
	        for (int i = 0; i < 655320; i++) {
	          Employee staff = new Employee(i, i+"group", 1900+i, 12, 25, 2500+i);
	          staffs.add(staff);
	        }
	        Map<String,String> titleMap = new LinkedHashMap<String,String>();
	        titleMap.put("name", "姓名");
	        titleMap.put("clazz", "组号");
	        titleMap.put("year", "年份");
	        titleMap.put("month", "月份");
	        titleMap.put("day", "天");
	        titleMap.put("salary", "薪资");
	        String sheetName = "信息导出";
	        if(!dir.exists()){
	        	dir.mkdirs();
	        }
	        ExportExcelUtil.excelExport(staffs, titleMap, sheetName);
	        
	    }
}
