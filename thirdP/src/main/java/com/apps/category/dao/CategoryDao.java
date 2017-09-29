package com.apps.category.dao;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.apps.category.domain.CategoryVO;
import com.apps.common.DTO;
import com.apps.common.WorkDiv;

public interface CategoryDao extends WorkDiv{

	/**
	 * 카테고리 조회
	 * @param mst_ct_id
	 * @return 하위 카테고리 list
	 */
	public ModelAndView do_searchCategory(String param);
	
	/**
	 * 단건조회
	 * 조건: id, 기간, 카테고리
	 * @param dto
	 * @return
	 */
	public List<CategoryVO> do_searchOne(DTO dto);
}
