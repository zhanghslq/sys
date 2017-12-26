package com.yb.entity;

public class Department {
	private String minutes;
	private Double instoreMoney;//店内服务
	private Double fastfoodMoney;//快餐食品
	private Double perishableMoney;//易腐食品
	private Double lubeMoney;//润滑油
	private Double cigaretteMoney;//烟草
	private Double dailyMoney;//生活日用品
	private Double teamcardMoney;//车队卡
	private Double alcoholicMoney;//酒精饮料
	private Double snackMoney;//零食
	private Double nonalcoholicMoney;//非酒精饮料
	private Double nonfoodMoney;//非食品
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String minutes, Double instoreMoney,
			Double fastfoodMoney, Double perishableMoney, Double lubeMoney,
			Double cigaretteMoney, Double dailyMoney, Double teamcardMoney,
			Double alcoholicMoney, Double snackMoney, Double nonalcoholicMoney,
			Double nonfoodMoney) {
		super();
		this.minutes = minutes;
		this.instoreMoney = instoreMoney;
		this.fastfoodMoney = fastfoodMoney;
		this.perishableMoney = perishableMoney;
		this.lubeMoney = lubeMoney;
		this.cigaretteMoney = cigaretteMoney;
		this.dailyMoney = dailyMoney;
		this.teamcardMoney = teamcardMoney;
		this.alcoholicMoney = alcoholicMoney;
		this.snackMoney = snackMoney;
		this.nonalcoholicMoney = nonalcoholicMoney;
		this.nonfoodMoney = nonfoodMoney;
	}
	@Override
	public String toString() {
		return "Department [minutes=" + minutes + ", instoreMoney="
				+ instoreMoney + ", fastfoodMoney=" + fastfoodMoney
				+ ", perishableMoney=" + perishableMoney + ", lubeMoney="
				+ lubeMoney + ", cigaretteMoney=" + cigaretteMoney
				+ ", dailyMoney=" + dailyMoney + ", teamcardMoney="
				+ teamcardMoney + ", alcoholicMoney=" + alcoholicMoney
				+ ", snackMoney=" + snackMoney + ", nonalcoholicMoney="
				+ nonalcoholicMoney + ", nonfoodMoney=" + nonfoodMoney + "]";
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public Double getInstoreMoney() {
		return instoreMoney;
	}
	public void setInstoreMoney(Double instoreMoney) {
		this.instoreMoney = instoreMoney;
	}
	public Double getFastfoodMoney() {
		return fastfoodMoney;
	}
	public void setFastfoodMoney(Double fastfoodMoney) {
		this.fastfoodMoney = fastfoodMoney;
	}
	public Double getPerishableMoney() {
		return perishableMoney;
	}
	public void setPerishableMoney(Double perishableMoney) {
		this.perishableMoney = perishableMoney;
	}
	public Double getLubeMoney() {
		return lubeMoney;
	}
	public void setLubeMoney(Double lubeMoney) {
		this.lubeMoney = lubeMoney;
	}
	public Double getCigaretteMoney() {
		return cigaretteMoney;
	}
	public void setCigaretteMoney(Double cigaretteMoney) {
		this.cigaretteMoney = cigaretteMoney;
	}
	public Double getDailyMoney() {
		return dailyMoney;
	}
	public void setDailyMoney(Double dailyMoney) {
		this.dailyMoney = dailyMoney;
	}
	public Double getTeamcardMoney() {
		return teamcardMoney;
	}
	public void setTeamcardMoney(Double teamcardMoney) {
		this.teamcardMoney = teamcardMoney;
	}
	public Double getAlcoholicMoney() {
		return alcoholicMoney;
	}
	public void setAlcoholicMoney(Double alcoholicMoney) {
		this.alcoholicMoney = alcoholicMoney;
	}
	public Double getSnackMoney() {
		return snackMoney;
	}
	public void setSnackMoney(Double snackMoney) {
		this.snackMoney = snackMoney;
	}
	public Double getNonalcoholicMoney() {
		return nonalcoholicMoney;
	}
	public void setNonalcoholicMoney(Double nonalcoholicMoney) {
		this.nonalcoholicMoney = nonalcoholicMoney;
	}
	public Double getNonfoodMoney() {
		return nonfoodMoney;
	}
	public void setNonfoodMoney(Double nonfoodMoney) {
		this.nonfoodMoney = nonfoodMoney;
	}
	
	
}
