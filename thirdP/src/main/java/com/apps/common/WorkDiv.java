package com.apps.common;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * WorkDiv.java
 * 거래표준 메소드 정의
 * do_save   : 저장
 * do_search : 조회
 * do_delete : 삭제
 * do_update : 수정
 * 
 * do_excelDown
 * do_excelUp
 * @author 3rdProj
 *
 */
public interface WorkDiv {

	public int do_save(DTO dto);
	public List<?> do_search(DTO dto);
	public int do_delete(DTO dto);
	public int do_update(DTO dto);
	

	public int do_excelUp(List<?> list);
	
}
