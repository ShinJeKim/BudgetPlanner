package com.apps.montly.domain;

import com.apps.common.DTO;

public class TotalDataVO extends DTO {

	
//	private String sunday;
//	private String monday;
//	private String tuesday;
//	private String wednesday;
//	private String thursday;
//	private String friday;
//	private String saturday;
	private String day;
	private String date;
	private String income;
	private String expense;
	private String totalUsage;
	
	public TotalDataVO() {
		
	}


	/**
	 * @param day
	 * @param date
	 * @param income
	 * @param expense
	 * @param totalUsage
	 */
	public TotalDataVO(String day, String date, String income, String expense, String totalUsage) {
		super();
		this.day = day;
		this.date = date;
		this.income = income;
		this.expense = expense;
		this.totalUsage = totalUsage;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getTotalUsage() {
		return totalUsage;
	}

	public void setTotalUsage(String totalUsage) {
		this.totalUsage = totalUsage;
	}

	@Override
	public String toString() {
		return "TotalDataVO [day=" + day + ", date=" + date + ", income=" + income + ", expense=" + expense
				+ ", totalUsage=" + totalUsage + "]";
	}

}
