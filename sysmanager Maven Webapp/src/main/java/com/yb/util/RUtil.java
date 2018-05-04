package com.yb.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yb.entity.LongPack;

public class RUtil {
	public static List<LongPack> getNumber() throws IOException{
		FileReader fileReader = new FileReader("basket_number.csv");
		BufferedReader readernumber = new BufferedReader(fileReader);//换成你的文件名   
		readernumber.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
		List<LongPack> datasNumber = new ArrayList<LongPack>();
        String linenumber = null;
        while((linenumber=readernumber.readLine())!=null){    
        	String item[] = linenumber.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
        	Long one = Long.parseLong(item[0]);//这就是你要的数据了   
        	Long two = Long.parseLong(item[1]);//这就是你要的数据了   
        	datasNumber.add(new LongPack("消费一件商品的", one));
        	datasNumber.add(new LongPack("消费多件商品的", two));
        }
        readernumber.close();
        fileReader.close();
		return datasNumber;
	}
	public static List<List<Double>> getRule() throws IOException{//读取basket——rules
		FileReader fileReader = new FileReader("basket_rules.csv");
		BufferedReader reader = new BufferedReader(fileReader);//换成你的文件名   
		reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
		List<List<Double>> data=new ArrayList<List<Double>>();
		String line = null;
		while((line=reader.readLine())!=null){  
			List<Double> number = new ArrayList<Double>();
			String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
			number.add(Double.parseDouble(item[0]));
			number.add(Double.parseDouble(item[1]));
			number.add(Double.parseDouble(item[2]));
			data.add(number);
		}
		reader.close();
		fileReader.close();
		return data;
	}
	@SuppressWarnings("resource")
	public static List<List<Double>> getLink() throws IOException{//读取basket——rules
		BufferedReader reader = new BufferedReader(new FileReader("link.csv"));//换成你的文件名   
		reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
		List<List<Double>> data=new ArrayList<List<Double>>();
		String line = null;
		while((line=reader.readLine())!=null){  
			List<Double> number = new ArrayList<Double>();
			String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
			number.add(Double.parseDouble(item[0]));
			number.add(Double.parseDouble(item[1]));
			number.add(Double.parseDouble(item[2]));
			data.add(number);
		}
		return data;
	}
	@SuppressWarnings("resource")
	public static List<LongPack> getLinkNumber() throws IOException{//读取basket——rules
		FileReader fileReader = new FileReader("link_number.csv");
		BufferedReader readernumber = new BufferedReader(fileReader);//换成你的文件名   
		readernumber.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
		List<LongPack> datasNumber = new ArrayList<LongPack>();
        String linenumber = null;
        while((linenumber=readernumber.readLine())!=null){    
        	String item[] = linenumber.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
        	Long one = Long.parseLong(item[2]);//这就是你要的数据了   
        	Long two = Long.parseLong(item[3]);//这就是你要的数据了   
        	datasNumber.add(new LongPack("本商品销售量", one));
        	datasNumber.add(new LongPack("其余商品销售量", two-one));
        }
        readernumber.close();
        fileReader.close();
		return datasNumber;
	}
	
}
