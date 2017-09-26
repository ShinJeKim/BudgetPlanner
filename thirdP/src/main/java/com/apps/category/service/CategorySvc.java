package com.apps.category.service;

import java.util.List;

import com.apps.category.domain.CategoryVO;
import com.apps.common.DTO;

public interface CategorySvc {

	/**
	 * 단건조회
	 * @param dto
	 * @return
	 */
	public List<CategoryVO> do_searchOne(DTO dto);
}
