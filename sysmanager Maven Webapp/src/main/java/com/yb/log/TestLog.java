package com.yb.log;

import org.apache.log4j.Logger;

public class TestLog {
	public static void main(String[] args) {
		
		//testLog.class.getName()返回类的名字，实际上也就是一个字符串，可以用任意字符串代替，如"出错位置：testLog类"
		   Logger logger = Logger.getLogger(TestLog.class.getName());
		 //设定配置文件的位置，如果不设置则要把配置文件放到class目录或根目录
		   /*PropertyConfigurator.configure(".log4j.properties");   */
		      logger.error("日志记录");
		 }
}
