package com.apps.user.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.apps.common.DTO;
import com.apps.user.domain.UserVO;

/**
 * @since 2017-09-25
 * @author 임채현
 * @version 1.0
 * interface UserSvc
 *
 */
public interface UserSvc {

	/**
	 * 저장 : do_save
	 * return 1 : Success, else failed
	 * @param dto
	 * @return int
	 * @throws DataAccessException
	 */
	public int do_save(DTO dto) throws DataAccessException;
	
	
	/**
	 * 전체조회 : do_search
	 * @param dto
	 * @return List
	 */
	public List<?> do_search(DTO dto);
	
	/**
	 * 단건조회 : do_selectOne
	 * @param dto
	 * @return DTO
	 */
	public DTO do_selectOne(DTO dto);
	
	/**
	 * 삭제 : do_delete
	 * return 1 : Success, else failed
	 * @param dto
	 * @return int
	 */
	public int do_delete(DTO dto);
	
	/**
	 * 업데이트 : do_update
	 * return 1 : Success, else failed
	 * @param dto
	 * @return int
	 */
	public int do_update(DTO dto);
	
	/**
	 * excelDown
	 * @param dto
	 * @return String
	 */
	public String do_excelDown(DTO dto) 
			throws IOException ;
	
	/**
	 * excelUp
	 * @param dto
	 * @return String
	 */
	public String do_excelUpTx(HttpServletRequest request) 
			throws Exception ;
	
	/**
	 * ID 체크
	 * return 1 : ID 존재, else : 없음
	 * @param id
	 * @return int
	 */
	public int do_check_id(String id);
	
	/**
	 * password 체크
	 * return 1 : password 일치, else : 실패
	 * @param dto
	 * @return int
	 */
	public int do_check_passwd(DTO dto);
	
	/**
	 * email 체크
	 * return 1 : email 존재, else : 없음
	 * @param email
	 * @return
	 */
	public int do_check_email(String email);
	
	/**
	 * login 수행
	 * 사용자 정보를 객체로 가져옴
	 * @param dto
	 * @return
	 */
	public DTO do_login(DTO dto);
	
	/**
	 * 로그아웃
	 * @param session
	 */
	public void do_logout(HttpSession session);


	/**
	 * ID찾기
	 * @param dto
	 * @return
	 */
	public String do_findID(DTO dto);
	
	
	/**
	 * PW찾기
	 * @param dto
	 * @return
	 */
	public String do_findPW(DTO dto);
	
	/**
	 * DeleteLog 삽입
	 * @param dto
	 * @return
	 */
	public int do_dlog_save(String id);
}
