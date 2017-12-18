package com.yb.excel.use;

public class Employee {

	  private Integer name;

	  private String clazz;

	  private Integer year;

	  private Integer month;

	  private Integer day;

	  private double salary;

	  public Employee() {
	  };

	  public Employee(Integer name, String clazz, Integer year, Integer month, Integer day,
	      double salary) {
	    super();
	    this.name = name;
	    this.clazz = clazz;
	    this.year = year;
	    this.month = month;
	    this.day = day;
	    this.salary = salary;
	  }

	  /**
	   * @return name
	   */
	  public Integer getName() {
	    return name;
	  }

	  /**
	   * @return year
	   */
	  public Integer getYear() {
	    return year;
	  }

	  /**
	   * @return month
	   */
	  public Integer getMonth() {
	    return month;
	  }

	  /**
	   * @return day
	   */
	  public Integer getDay() {
	    return day;
	  }

	  /**
	   * @return salary
	   */
	  public double getSalary() {
	    return salary;
	  }

	  /**
	   * @param name
	   *        set name
	   */
	  public void setName(Integer name) {
	    this.name = name;
	  }

	  /**
	   * @param year
	   *        set year
	   */
	  public void setYear(Integer year) {
	    this.year = year;
	  }

	  /**
	   * @param month
	   *        set month
	   */
	  public void setMonth(Integer month) {
	    this.month = month;
	  }

	  /**
	   * @param day
	   *        set day
	   */
	  public void setDay(Integer day) {
	    this.day = day;
	  }

	  /**
	   * @param salary
	   *        set salary
	   */
	  public void setSalary(double salary) {
	    this.salary = salary;
	  }

	  /**
	   * @return clazz
	   */
	  public String getClazz() {
	    return clazz;
	  }

	  /**
	   * @param clazz
	   *        set clazz
	   */
	  public void setClazz(String clazz) {
	    this.clazz = clazz;
	  }

	  @Override
	  public String toString() {
	    return "Employee [name=" + name + ", clazz=" + clazz + ", year=" + year + ", month=" + month
	        + ", day=" + day + ", salary=" + salary + "]";
	  }

	}
