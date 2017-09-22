package com.apps.chart.domain;

import com.apps.common.DTO;

public class ChartVO extends DTO {
	
	private String 	id; 		//유저 아이디
	private String 	daily_code; //식별코드
	private String 	usage; 		//금액
	private String	content; 	//내용
	private String 	reg_dt; 	//등록일
	private String 	mod_dt; 	//수정일
	private int 	mst_ct_id; 	//수입or지출
	private int 	dtl_ct_id; 	//하위카테고리
	
	
	//Setter
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
	
	
	//Getter
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
	
	
	//toString
	@Override
	public String toString() {
		return "DailyVO [id=" + id + ", daily_code=" + daily_code + ", usage=" + usage + ", content=" + content
				+ ", reg_dt=" + reg_dt + ", mod_dt=" + mod_dt + ", mst_ct_id=" + mst_ct_id + ", dtl_ct_id=" + dtl_ct_id
				+ "]";
	}
}
