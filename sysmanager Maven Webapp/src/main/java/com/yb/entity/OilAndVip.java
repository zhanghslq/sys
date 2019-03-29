package com.yb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 油品总数，和会员消费的
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OilAndVip {
	private String date;
	private String day;
	private String minutes;
	private String stationID;
	private Integer oilNumber;
	private Double oilLitre;
	private Double avgLitre;
	private Double oilMoney;
	private Integer vipOilNumber;
	private Double vipOilLitre;
	private Double vipAvgLitre;
	private Double vipOilMoney;
	private Integer notVipOilNumber;
	private Double notVipOilLitre;
	private Double notVipAvgLitre;
	private Double notVipOilMoney;

	/**
	 * 增加会员销量占比
	 */
	private Double vipRatio;
	/**
	 * 增加非会员占比
	 */
	private Double notVipRatio;
}
