package com.yb.test;

public class TestSplit {
	public static void main(String[] args) {
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
	}
}
