package com.yb.test;

import java.util.ArrayList;
import java.util.List;

import com.yb.util.ArryToListUtil;

public class TestSplit {
	/*public static void main(String[] args) {
		String string="6.04(京89号)";
		String[] split = string.split("");
		String oilString="";
		String price="";
		int a=0;
		for (String string2 : split) {
			a++;
			if(a<=5){
				price+=string2;
			}else if(a>6&&a<=10){
				oilString+=string2;
			}
		}
		System.out.println("price="+price);
		System.out.println("oil="+oilString);
	}*/
	public static void main(String[] args) {
		String string="非新注册会员 不活跃会员 从未适用优惠券型 近期加油频次低 近期非油消费金额低 短期加油频次低";
		String[] split = string.split(" ", 5);
		for (String string2 : split) {
			System.out.println(string2);
		}
		List<String> list = ArryToListUtil.format(split);
		List<String> list2 = list.subList(0, list.size()-1);
		System.out.println(list2);
		list2.add(list2.size(),"test...");
		System.out.println(list2);
	}
}
