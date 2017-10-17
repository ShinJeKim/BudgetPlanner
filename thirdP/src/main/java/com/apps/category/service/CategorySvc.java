package com.apps.category.service;

import java.io.IOException;
import java.util.List;

import com.apps.category.domain.CategoryVO;
import com.apps.common.DTO;
import com.apps.daily.domain.DailyVO;

public interface CategorySvc {

	/**
	 * 카테고리 조회
	 * @param mst_ct_id
	 * @return 하위 카테고리 list
	 */
	public List<String> do_searchCategory(String param);
	
	/**
	 * 단건조회
	 * 조건: id, 기간, 카테고리
	 * @param dto
	 * @return
	 */
	public List<DailyVO> do_searchList(DTO dto);
	
	/**
	 * excelDown
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	public String do_excelDown(DTO dto)throws IOException;
}
