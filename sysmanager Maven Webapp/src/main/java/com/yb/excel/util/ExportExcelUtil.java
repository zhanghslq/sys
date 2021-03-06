package com.yb.excel.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/***
 * 导出List<Object>数据到excel（最多可导出百万行）
 * @author user
 */
public final class ExportExcelUtil {

  /***
   * 构造方法
   */
  private ExportExcelUtil() {

  }

  /***
   * 工作簿
   */
  private static SXSSFWorkbook workbook;

  /***
   * sheet
   */
  private static SXSSFSheet sheet;
 /***
   * 标题行开始位置
   */
  private static final int TITLE_START_POSITION = 0;

  /***
   * 时间行开始位置
   */
  private static final int DATEHEAD_START_POSITION = 1;

  /***
   * 表头行开始位置
   */
  private static final int HEAD_START_POSITION = 2;

  /***
   * 文本行开始位置
   */
  private static final int CONTENT_START_POSITION = 3;


  /**
   * 
   * @param dataList
   *        对象集合
   * @param titleMap
   *        表头信息（对象属性名称->要显示的标题值)[按顺序添加]
   * @param sheetName
   *        sheet名称和表头值
   */
  public static SXSSFWorkbook excelExport(List<?> dataList, Map<String, String> titleMap, String sheetName,Date start,Date end) {
    // 初始化workbook
    initHSSFWorkbook(sheetName);
    // 标题行
    createTitleRow(titleMap, sheetName);
    // 时间行
    createDateHeadRow(titleMap,start,end);
    // 表头行
    createHeadRow(titleMap);
    // 文本行
    createContentRow(dataList, titleMap);
    //设置自动伸缩
    //autoSizeColumn(titleMap.size());
    // 写入处理结果
    try {
      //生成UUID文件名称
      //如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
      return workbook;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
	return workbook;
  }

  /***
   * 
   * @param sheetName
   *        sheetName
   */
  private static void initHSSFWorkbook(String sheetName) {
    workbook = new SXSSFWorkbook(10000);
    sheet = workbook.createSheet(sheetName);
  }

  /**
   * 生成标题（第零行创建）
   * @param titleMap 对象属性名称->表头显示名称
   * @param sheetName sheet名称
   */
  private static void createTitleRow(Map<String, String> titleMap, String sheetName) {
    CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, titleMap.size() - 1);
    sheet.addMergedRegion(titleRange);
    SXSSFRow titleRow = sheet.createRow(TITLE_START_POSITION);
    SXSSFCell titleCell = titleRow.createCell(0);
    titleCell.setCellValue(sheetName);
  }

  /**
   * 创建时间行（第一行创建）
   * @param titleMap 对象属性名称->表头显示名称
   */
  private static void createDateHeadRow(Map<String, String> titleMap,Date start,Date end) {
    CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, titleMap.size() - 1);
    sheet.addMergedRegion(dateRange);
    SXSSFRow dateRow = sheet.createRow(DATEHEAD_START_POSITION);
    SXSSFCell dateCell = dateRow.createCell(0);   
    dateCell.setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(start)+"-"+new SimpleDateFormat("yyyy年MM月dd日").format(end));
  }

  /**
   * 创建表头行（第二行创建）
   * @param titleMap 对象属性名称->表头显示名称
   */
  private static void createHeadRow(Map<String, String> titleMap) {
    // 第1行创建
    SXSSFRow headRow = sheet.createRow(HEAD_START_POSITION);
    int i = 0;
    for (String entry : titleMap.keySet()) {
      SXSSFCell headCell = headRow.createCell(i);
      headCell.setCellValue(titleMap.get(entry));
      i++;
    }
  }

 /**
  * 
  * @param dataList 对象数据集合
  * @param titleMap 表头信息
  */
  private static void createContentRow(List<?> dataList, Map<String, String> titleMap) {
    try {
      int i=0;
      for (Object obj : dataList) {
        SXSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + i);
        int j = 0;
        for (String entry : titleMap.keySet()) {
          String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
          String value="";
			try {
				Method m = obj.getClass().getMethod(method, null);
				if(m.invoke(obj, null)!=null){
					value = m.invoke(obj, null).toString();
				}else {
					value="无数据";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				value="无数据";
			}
          SXSSFCell textcell = textRow.createCell(j);
          textcell.setCellValue(value);
          j++;
        }
        i++;
      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 自动伸缩列（如非必要，请勿打开此方法，耗内存）
   * @param size 列数
   */
  @SuppressWarnings("unused")
private static void autoSizeColumn(Integer size) { 
    for (int j = 0; j < size; j++) {
      sheet.autoSizeColumn(j);
    }
  }
}
