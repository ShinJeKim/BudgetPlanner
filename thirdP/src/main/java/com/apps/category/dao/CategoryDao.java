package com.apps.category.dao;

import java.util.List;

import com.apps.category.domain.CategoryVO;
import com.apps.common.DTO;
import com.apps.common.WorkDiv;

public interface CategoryDao extends WorkDiv{

	/**
	 * 단건 조회
	 * @param dto
	 * @return
	 */
	public List<CategoryVO> do_searchOne(DTO dto);
}
