package com.apps.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.apps.category.domain.CategoryVO;
import com.apps.category.service.CategorySvcImpl;
import com.apps.common.DTO;
import com.apps.daily.domain.DailyVO;

@Repository
public class CategoryDaoImpl implements CategoryDao{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace="com.sist.repository.mappers.category";
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public CategoryDaoImpl() {
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<CategoryVO> userMapper = new RowMapper<CategoryVO>() {

		@Override
		public CategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CategoryVO catVO = new CategoryVO();
			
			catVO.setNo(rs.getInt("no"));
			catVO.setId(rs.getString("id"));
			catVO.setDaily_code(rs.getString("daily_code"));
			catVO.setUsage(rs.getString("usage"));
			catVO.setContent(rs.getString("content"));
			catVO.setReg_dt(rs.getString("reg_dt"));
			catVO.setMod_dt(rs.getString("mod_dt"));
			catVO.setMst_ct_id(rs.getString("mst_ct_id"));
			catVO.setDtl_ct_id(rs.getString("dtl_ct_id"));
			catVO.setDtl_ct_nm(rs.getString("dtl_ct_nm"));
			
			return catVO;
		}	
	};
	
	/**
	 * 카테고리 조회
	 * @param mst_ct_id
	 * @return 하위 카테고리 list
	 */
	@Override
	public List<String> do_searchCategory(String param){
		String statement = namespace+".do_searchCategory";
		
		String mst_ct_id = param.toString();
		
		log.debug("==================================");
		log.debug("statement= "+statement);
		log.debug("DaoImpl param= "+mst_ct_id);
		log.debug("==================================");
			
		return sqlSession.selectList(statement, mst_ct_id);
	}
	
	/**
	 * 단건조회
	 * 조건: id, 기간, 카테고리
	 * => mst_ct_id, dtl_ct_id, start_date, end_date, id
	 * @param dto
	 * @return
	 */
	@Override
	public List<DailyVO> do_searchList(DTO dto) {
		String statement = namespace+".do_searchList";
		
		CategoryVO param = (CategoryVO)dto;
		//DailyVO param = (DailyVO)dto;
		
		Hashtable<String, String> searchParam = null;
		searchParam = param.getParam();
		
		int pageSize = 10;
		int pageNum = 1;
		
		if(searchParam.get("page_size".toString()) != null)
			pageSize = Integer.parseInt(searchParam.get("page_size".toString()).toString());
		if(searchParam.get("page_num".toString()) != null)
			pageNum = Integer.parseInt(searchParam.get("page_num".toString()).toString());
		
		searchParam.put("page_size",	pageSize+"");
		searchParam.put("page_num", pageNum+"");
		
		String mst_ct_id = searchParam.get("mst_ct_id").toString();
		String dtl_ct_nm = searchParam.get("dtl_ct_nm").toString();
		String start_date = searchParam.get("start_date").toString();
		String end_date = searchParam.get("end_date").toString();
		
		searchParam.put("mst_ct_id", mst_ct_id);
		searchParam.put("dtl_ct_nm", dtl_ct_nm);
		searchParam.put("start_date", start_date);
		searchParam.put("end_date", end_date);
		
		return sqlSession.selectList(statement, searchParam);
	}
	
	
	/**
	 * do_excelDown
	 * 엑셀 다운용 리스트 받아오기(페이징 처리X)
	 */
	@Override
	public List<DailyVO> do_searchExcel(DTO dto) {
		String statement = namespace+".do_searchExcel";
		
		CategoryVO param = (CategoryVO)dto;
		
		Hashtable<String, String> searchParam = null;
		searchParam = param.getParam();
		
		
		String mst_ct_id = searchParam.get("mst_ct_id").toString();
		String dtl_ct_nm = searchParam.get("dtl_ct_nm").toString();
		String start_date = searchParam.get("start_date").toString();
		String end_date = searchParam.get("end_date").toString();
		
		searchParam.put("mst_ct_id", mst_ct_id);
		searchParam.put("dtl_ct_nm", dtl_ct_nm);
		searchParam.put("start_date", start_date);
		searchParam.put("end_date", end_date);
		
		return sqlSession.selectList(statement, searchParam);
	}
	
	
	/**
	 * 전체조회
	 * 조건: id, 기간
	 * @param dto
	 * @return
	 */
	@Override
	public List<?> do_search(DTO dto) {
		
		String statement = namespace+".do_searchList";
		
		CategoryVO param = (CategoryVO)dto;
		
		Hashtable<String, String> searchParam = null;
		searchParam = param.getParam();
		
		int pageSize = 10;
		int pageNum = 1;
		
		if(searchParam.get("page_size".toString()) != null)
			pageSize = Integer.parseInt(searchParam.get("page_size".toString()).toString());
		if(searchParam.get("page_num".toString()) != null)
			pageNum = Integer.parseInt(searchParam.get("page_num".toString()).toString());
		
		searchParam.put("page_size",	pageSize+"");
		searchParam.put("page_num", pageNum+"");
		
		return sqlSession.selectList(statement, searchParam);
	}
	
	
	
	

	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int do_excelUp(List<?> list) {
		// TODO Auto-generated method stub
		return 0;
	}


}
