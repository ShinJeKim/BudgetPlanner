package com.apps.user.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.apps.common.DTO;
import com.apps.user.dao.UserDao;
import com.apps.user.domain.UserVO;

/**
 * @since 2017-09-25
 * @author 임채현
 * @version 1.0
 * UserSvcImpl implements userSvc
 *
 */
@Service
public class UserSvcImpl implements UserSvc {

	private Logger log = LoggerFactory.getLogger(UserSvcImpl.class);
	
	@Autowired
	UserDao userDao;
	
	//excel download path 
	//Todo : 추후 저장 경로 변경 필요
	private String path = "c:\\file\\excel\\";
	
	@Override
	public int do_save(DTO dto) throws DataAccessException {
		log.debug("2============do_save===============");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		return userDao.do_save(dto);
	}

	@Override
	public List<?> do_search(DTO dto) {
		log.debug("2============do_search=============");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		return userDao.do_search(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		log.debug("2============do_searchOne=============");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		return userDao.do_selectOne(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		log.debug("2============do_delete=============");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		int flag = userDao.do_delete(dto);
		
		return userDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		log.debug("2============do_update=============");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		return userDao.do_update(dto);
	}

	@Override
	public String do_excelDown(DTO dto) throws IOException {
		// TODO excel_down
		return null;
	}

	@Override
	public String do_excelUpTx(HttpServletRequest request) throws Exception {
		// TODO excel_up
		return null;
	}

	@Override
	public int do_check_id(String id) {
		log.debug("2============do_check_id=============");
		log.debug("id : "+id);
		log.debug("2==================================");
		
		return userDao.do_check_id(id);
	}

	@Override
	public int do_check_passwd(DTO dto) {
		log.debug("2=========do_check_passwd==========");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		return userDao.do_check_passwd(dto);
	}

	@Override
	public DTO do_login(DTO dto) {
		log.debug("2============do_login=============");
		log.debug(dto.toString());
		log.debug("2==================================");
		
		return userDao.do_login(dto);
	}

	@Override
	public void do_logout(HttpSession session) {
		log.debug("2============do_logout=============");
		log.debug("");
		log.debug("2==================================");
		session.invalidate();
	}



}
