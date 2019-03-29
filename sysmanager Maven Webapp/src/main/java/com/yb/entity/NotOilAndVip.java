package com.yb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 便利店商品
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotOilAndVip {
	private String minutes;
	private String day;
	private String minu;
	private String stationID;
	private Integer notOilNumber;
	private Double notOilMoney;
	private Double avgMoney;
	private Double exceptLube;
	private Integer vipNotOilNumber;
	private Double vipNotOilMoney;
	private Double vipAvgMoney;
	private Double vipExceptLube; 
	private Integer notVipNotOilNumber;
	private Double notVipNotOilMoney;
	private Double notVipAvgMoney;
	private Double notVipExceptLube;
	/**增加会员消费占比*/
	private Double vipRatio;
	/**
	 * 增加非会员占比
	 */
	private Double notVipRatio;

}
