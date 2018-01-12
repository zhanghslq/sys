package com.yb.entity;
/**
 * 油站数据传输的时间
 * @author lenovo
 *
 */
public class Heart {
	private String id;//油站id
	private String name;//油站名
	private String message;//暂时是版本号
	private String lastTime;//上次通信时间
	public Heart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Heart(String id, String name, String message, String lastTime) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "Heart [id=" + id + ", name=" + name + ", message=" + message
				+ ", lastTime=" + lastTime + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	
}
