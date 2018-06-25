package com.yb.entity;

public class Status {
	private String code;
	private String message;
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	@Override
	public String toString() {
		return "Status [code=" + code + ", message=" + message + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
