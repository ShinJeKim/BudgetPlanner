package com.apps.user.domain;

import com.apps.common.DTO;

/**
 * @since 2017-10-19
 * @version 1.0
 * @author 임채현
 * DeleteLog VO
 */
public class DeleteLogVO extends DTO {
	
	private String id;
	private String delete_dt;
	private int d_log_flag;
	
	public DeleteLogVO() {
		
	}
	
	/**
	 * @param id
	 * @param delete_dt
	 * @param d_log_flag
	 */
	public DeleteLogVO(String id, String delete_dt, int d_log_flag) {
		super();
		this.id = id;
		this.delete_dt = delete_dt;
		this.d_log_flag = d_log_flag;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDelete_dt() {
		return delete_dt;
	}
	public void setDelete_dt(String delete_dt) {
		this.delete_dt = delete_dt;
	}
	public int getD_log_flag() {
		return d_log_flag;
	}
	public void setD_log_flag(int d_log_flag) {
		this.d_log_flag = d_log_flag;
	}

	@Override
	public String toString() {
		return "DeleteLogVO [id=" + id + ", delete_dt=" + delete_dt + ", d_log_flag=" + d_log_flag + "]";
	}
	
}
