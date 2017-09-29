package com.apps.category.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.category.dao.CategoryDao;
import com.apps.category.domain.CategoryVO;
import com.apps.common.DTO;

@Service
public class CategorySvcImpl implements CategorySvc {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryDao categoryDao;
	
	/**
	 * 카테고리 조회
	 * @param mst_ct_id
	 * @return 하위 카테고리 list
	 */
	@Override
	public List<String> do_searchCategory(int param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 단건 조회
	 * 조건: id, 기간, 카테고리
	 * @param dto
	 * @return
	 */
	@Override
	public List<CategoryVO> do_searchOne(DTO dto) {
		
		log.debug("=========================");
		log.debug("do_searchOne dto: "+dto.toString());
		log.debug("=========================");
		
		return categoryDao.do_searchOne(dto);
	}

	

}
