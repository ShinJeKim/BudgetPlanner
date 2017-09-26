package com.apps.category.domain;

import com.apps.common.DTO;

public class CategoryVO extends DTO {

	private String id; // 유저 아이디
	private String daily_code; // 식별코드
	private String usage; // 금액
	private String content; // 내용
	private String reg_dt; // 등록일
	private String mod_dt; // 수정일
	private int mst_ct_id; // (상위 카테고리)수입or지출
	private int dtl_ct_id; // 하위카테고리

	private String start_date;
	private String end_date;

	public CategoryVO() {

	}

	/**
	 * @param id
	 * @param daily_code
	 * @param usage
	 * @param content
	 * @param reg_dt
	 * @param mod_dt
	 * @param mst_ct_id
	 * @param dtl_ct_id
	 * @param start_date
	 * @param end_date
	 */
	public CategoryVO(String id, String daily_code, String usage, String content, String reg_dt, String mod_dt,
			int mst_ct_id, int dtl_ct_id, String start_date, String end_date) {
		super();
		this.id = id;
		this.daily_code = daily_code;
		this.usage = usage;
		this.content = content;
		this.reg_dt = reg_dt;
		this.mod_dt = mod_dt;
		this.mst_ct_id = mst_ct_id;
		this.dtl_ct_id = dtl_ct_id;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	// Setter
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

	public void setMst_ct_id(int mst_ct_id) {
		this.mst_ct_id = mst_ct_id;
	}

	public void setDtl_ct_id(int dtl_ct_id) {
		this.dtl_ct_id = dtl_ct_id;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	// Getter
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

	public int getMst_ct_id() {
		return mst_ct_id;
	}

	public int getDtl_ct_id() {
		return dtl_ct_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	// toString
	@Override
	public String toString() {
		return "CategoryVO [id=" + id + ", daily_code=" + daily_code + ", usage=" + usage + ", content=" + content
				+ ", reg_dt=" + reg_dt + ", mod_dt=" + mod_dt + ", mst_ct_id=" + mst_ct_id + ", dtl_ct_id=" + dtl_ct_id
				+ ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}

}
