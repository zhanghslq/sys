package com.yb.entity;

public class Result {
	private Integer code;
	private Object data;
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(Integer code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", data=" + data + "]";
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
