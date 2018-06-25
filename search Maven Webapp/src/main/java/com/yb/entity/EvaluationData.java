package com.yb.entity;

public class EvaluationData {
	private String station;
	private String mobilePhone;
	private String evaluation_time;
	private String hello;
	private String clean;
	private String sale;
	private String goodbye;
	private String toilet;
	private Integer star4;
	private Integer star3;
	private Integer star;
	private String content;
	public EvaluationData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EvaluationData(String station, String mobilePhone,
			String evaluation_time, String hello, String clean, String sale,
			String goodbye, String toilet, Integer star4, Integer star3,
			Integer star, String content) {
		super();
		this.station = station;
		this.mobilePhone = mobilePhone;
		this.evaluation_time = evaluation_time;
		this.hello = hello;
		this.clean = clean;
		this.sale = sale;
		this.goodbye = goodbye;
		this.toilet = toilet;
		this.star4 = star4;
		this.star3 = star3;
		this.star = star;
		this.content = content;
	}
	@Override
	public String toString() {
		return "EvaluationData [station=" + station + ", mobilePhone="
				+ mobilePhone + ", evaluation_time=" + evaluation_time
				+ ", hello=" + hello + ", clean=" + clean + ", sale=" + sale
				+ ", goodbye=" + goodbye + ", toilet=" + toilet + ", star4="
				+ star4 + ", star3=" + star3 + ", star=" + star + ", content="
				+ content + "]";
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEvaluation_time() {
		return evaluation_time;
	}
	public void setEvaluation_time(String evaluation_time) {
		this.evaluation_time = evaluation_time;
	}
	public String getHello() {
		return hello;
	}
	public void setHello(String hello) {
		this.hello = hello;
	}
	public String getClean() {
		return clean;
	}
	public void setClean(String clean) {
		this.clean = clean;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getGoodbye() {
		return goodbye;
	}
	public void setGoodbye(String goodbye) {
		this.goodbye = goodbye;
	}
	public String getToilet() {
		return toilet;
	}
	public void setToilet(String toilet) {
		this.toilet = toilet;
	}
	public Integer getStar4() {
		return star4;
	}
	public void setStar4(Integer star4) {
		this.star4 = star4;
	}
	public Integer getStar3() {
		return star3;
	}
	public void setStar3(Integer star3) {
		this.star3 = star3;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
