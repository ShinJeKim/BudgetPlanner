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
 * do_report : rd,크리스탈 리포트
 * @author sist_
 *
 */
public interface WorkDiv {

	public int do_save(DTO dto);//Upsert
	public List<?> do_search(DTO dto);
	public int do_delete(DTO dto);
	public int do_update(DTO dto);
	
	public List<?> do_excelDown();
	public int do_excelUp(List<?> list);
	
}
