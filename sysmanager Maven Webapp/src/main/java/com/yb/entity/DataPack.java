package com.yb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPack {
	private String name;
	private Double value;
	private String stationID;

	private ItemStyle itemStyle;

	public DataPack(String name, Double value) {
		this.name = name;
		this.value = value;
	}

	public DataPack(String name, Double value, String stationID) {
		this.name = name;
		this.value = value;
		this.stationID = stationID;
	}
}
