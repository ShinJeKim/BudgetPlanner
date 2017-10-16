package com.apps.category.domain;

import com.apps.common.DTO;

public class CategoryVO extends DTO {

	private int No;
	private String id; // 유저 아이디
	private String daily_code; // 식별코드
	private String usage; // 금액
	private String content; // 내용
	private String reg_dt; // 등록일
	private String mod_dt; // 수정일
	private String mst_ct_id; // (상위 카테고리)수입or지출
	private String dtl_ct_id; // 하위카테고리
	private String mst_ct_nm; // (상위 카테고리)수입or지출
	private String dtl_ct_nm; // 하위카테고리
	private String start_date;
	private String end_date;

	public CategoryVO() {

	}


	/**
	 * 
	 * @param No
	 * @param id
	 * @param daily_code
	 * @param usage
	 * @param content
	 * @param reg_dt
	 * @param mod_dt
	 * @param mst_ct_id
	 * @param dtl_ct_id
	 * @param mst_ct_nm
	 * @param dtl_ct_nm
	 * @param start_date
	 * @param end_date
	 */
	public CategoryVO(int No, String id, String daily_code, String usage, String content, String reg_dt,
			String mod_dt, String mst_ct_id, String dtl_ct_id, String mst_ct_nm, String dtl_ct_nm, String start_date,
			String end_date) {
		super();
		this.No = No;
		this.id = id;
		this.daily_code = daily_code;
		this.usage = usage;
		this.content = content;
		this.reg_dt = reg_dt;
		this.mod_dt = mod_dt;
		this.mst_ct_id = mst_ct_id;
		this.dtl_ct_id = dtl_ct_id;
		this.mst_ct_nm = mst_ct_nm;
		this.dtl_ct_nm = dtl_ct_nm;
		this.start_date = start_date;
		this.end_date = end_date;
	}







	// Setter
	public void intNo(int No) {
		this.No = No;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDaily_code(String daily_code) {
		this.daily_code = daily_code;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}

	public void setMst_ct_id(String mst_ct_id) {
		this.mst_ct_id = mst_ct_id;
	}

	public void setDtl_ct_id(String dtl_ct_id) {
		this.dtl_ct_id = dtl_ct_id;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public void setMst_ct_nm(String mst_ct_nm) {
		this.mst_ct_nm = mst_ct_nm;
	}
	public void setDtl_ct_nm(String dtl_ct_nm) {
		this.dtl_ct_nm = dtl_ct_nm;
	}



	// Getter	
	public int getNo() {
		return No;
	}

	public String getId() {
		return id;
	}

	public String getDaily_code() {
		return daily_code;
	}

	public String getUsage() {
		return usage;
	}

	public String getContent() {
		return content;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public String getMod_dt() {
		return mod_dt;
	}

	public String getMst_ct_id() {
		return mst_ct_id;
	}

	public String getDtl_ct_id() {
		return dtl_ct_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}
	public String getMst_ct_nm() {
		return mst_ct_nm;
	}
	public String getDtl_ct_nm() {
		return dtl_ct_nm;
	}


	@Override
	public String toString() {
		return "CategoryVO [No=" + No + ", id=" + id + ", daily_code=" + daily_code + ", usage=" + usage + ", content="
				+ content + ", reg_dt=" + reg_dt + ", mod_dt=" + mod_dt + ", mst_ct_id=" + mst_ct_id + ", dtl_ct_id="
				+ dtl_ct_id + ", mst_ct_nm=" + mst_ct_nm + ", dtl_ct_nm=" + dtl_ct_nm + ", start_date=" + start_date
				+ ", end_date=" + end_date + "]";
	}



}
