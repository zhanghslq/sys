package com.yb.test;

public enum SeasonEnum {
	SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
	int seq;
	
	SeasonEnum(int seq){
		this.seq = seq; 
	}
}