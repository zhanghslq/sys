package com.yb.entity;

public class Basket1 {
	private String row_name;
	private String good;
	private Long freq_1;
	private Double percent;
	public Basket1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Basket1(String row_name, String good, Long freq_1, Double percent) {
		super();
		this.row_name = row_name;
		this.good = good;
		this.freq_1 = freq_1;
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "Basket1 [row_name=" + row_name + ", good=" + good + ", freq_1="
				+ freq_1 + ", percent=" + percent + "]";
	}
	public String getRow_name() {
		return row_name;
	}
	public void setRow_name(String row_name) {
		this.row_name = row_name;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public Long getFreq_1() {
		return freq_1;
	}
	public void setFreq_1(Long freq_1) {
		this.freq_1 = freq_1;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
	
}
