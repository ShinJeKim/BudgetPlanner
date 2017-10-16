package com.apps.category.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.category.dao.CategoryDao;
import com.apps.category.domain.CategoryVO;
import com.apps.common.DTO;
import com.apps.common.ExcelDownUtil;

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
	public List<String> do_searchCategory(String param) {
		log.debug("=========================");
		log.debug("SvcImpl param: "+param);
		log.debug("=========================");
		
		return categoryDao.do_searchCategory(param);
	}
	
	/**
	 * 단건 조회
	 * 조건: id, 기간, 카테고리
	 * @param dto
	 * @return
	 */
	@Override
	public List<CategoryVO> do_searchList(DTO dto) {
		
		log.debug("=========================");
		log.debug("do_searchOne dto: "+dto.toString());
		log.debug("=========================");
		
		return categoryDao.do_searchList(dto);
	}

	@Override
	public String do_excelDown(DTO dto) throws IOException {

		String path = "C:\\file\\excel\\";
		
		String fileName = null;
		log.debug("00000============================");
		log.debug("do_excelDown: dto  "+dto.toString());
		log.debug("00000============================");
		
		List<CategoryVO> list = (List<CategoryVO>) categoryDao.do_searchList(dto);
		log.debug("11111============================");
		log.debug("list.size():   "+list.size());
		log.debug("11111============================");
		
		ExcelDownUtil excelDownUtil = new ExcelDownUtil();
		fileName = excelDownUtil.writeExcel(path, "BalanceSheet.xls", list);
		log.debug("22222============================");
		log.debug("fileName: "+fileName);
		log.debug("22222============================");
		
		return path+fileName;
	}

	

	
	

}
