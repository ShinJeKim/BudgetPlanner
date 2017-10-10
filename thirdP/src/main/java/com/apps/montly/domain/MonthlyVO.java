package com.apps.montly.domain;

import com.apps.common.DTO;

/**
 * @since 2017-10-10
 * @author 임채현
 * @version 1.0
 * MonthlyVO
 */
public class MonthlyVO extends DTO {
	
	private String day;
	private String category;
	private int usage;
	
	public MonthlyVO() {
		
	}
	
	/**
	 * @param day
	 * @param category
	 * @param usage
	 */
	public MonthlyVO(String day, String category, int usage) {
		super();
		this.day = day;
		this.category = category;
		this.usage = usage;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	
	@Override
	public String toString() {
		return "MonthlyVO [day=" + day + ", category=" + category + ", usage=" + usage + "]";
	}
	

}
