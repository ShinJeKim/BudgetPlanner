package com.apps.user.domain;

import com.apps.common.DTO;

/**
 * @since 2017-09-22
 * @version 1.0
 * @author 임채현
 * User VO
 */
public class UserVO extends DTO {
	
	/**
	 *  COLUMN NAME		TYPE
	 *  ---------------------------------
	 *  ID				VARCHAR2(12 BYTE)
	 *  PASSWORD		VARCHAR2(12 BYTE)
	 *  NAME			VARCHAR2(30 CHAR)
	 *  EMAIL			VARCHAR2(50 BYTE)
	 *  FIXED_INCOME	NUMBER(10,0)
	 *  BALANCE			NUMBER(12,0)
	 *  REG_DT			DATE
	 *  MOD_DT			DATE
	 *  DELETE_FLAG		NUMBER(1,0)
	 *  ADMIN_FLAG		NUMBER(1,0)
	 */
	
	private String id;
	private String password;
	private String name;
	private String email;
	private int fixed_income;
	private int balance;
	private String reg_dt;
	private String mod_dt;
	private int delete_flag;
	private int admin_flag;
	
	/**
	 * Default Constructor
	 */
	public UserVO() {
		
	}
	
	/**
	 * Constructor Using Fields
	 * @param id
	 * @param password
	 * @param name
	 * @param email
	 * @param fixed_income
	 * @param balance
	 * @param reg_dt
	 * @param mod_dt
	 * @param delete_flag
	 * @param admin_flag
	 */
	public UserVO(String id, String password, String name, String email, int fixed_income, int balance, String reg_dt,
			String mod_dt, int delete_flag, int admin_flag) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.fixed_income = fixed_income;
		this.balance = balance;
		this.reg_dt = reg_dt;
		this.mod_dt = mod_dt;
		this.delete_flag = delete_flag;
		this.admin_flag = admin_flag;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFixed_income() {
		return fixed_income;
	}
	public void setFixed_income(int fixed_income) {
		this.fixed_income = fixed_income;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	public int getAdmin_flag() {
		return admin_flag;
	}
	public void setAdmin_flag(int admin_flag) {
		this.admin_flag = admin_flag;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", fixed_income="
				+ fixed_income + ", balance=" + balance + ", reg_dt=" + reg_dt + ", mod_dt=" + mod_dt + ", delete_flag="
				+ delete_flag + ", admin_flag=" + admin_flag + "]";
	}
	
}
