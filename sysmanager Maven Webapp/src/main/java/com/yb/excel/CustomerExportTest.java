package com.yb.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yb.excel.util.ExportExcelUtil;


public class CustomerExportTest {

  /**
   * @param args
   */
  public static void main(String[] args) {

    /**模拟数据开始*/
    List<Employee> staffs = new ArrayList<Employee>();
    for (int i = 0; i < 65532; i++) {
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
    /**模拟数据结束*/
    System.out.println("start导出");
    long start = System.currentTimeMillis();
    ExportExcelUtil.excelExport(staffs, titleMap, sheetName);
    long end = System.currentTimeMillis();
    System.out.println("end导出");
    System.out.println("耗时："+(end-start)+"ms");
  }

}
