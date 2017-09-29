package com.apps.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.apps.common.DTO;

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
			
			catVO.setId(rs.getString("id"));
			catVO.setDaily_code(rs.getString("daily_code"));
			catVO.setUsage(rs.getString("usage"));
			catVO.setContent(rs.getString("content"));
			catVO.setReg_dt(rs.getString("reg_dt"));
			catVO.setMod_dt(rs.getString("mod_dt"));
			catVO.setMst_ct_id(rs.getInt("mst_ct_id"));
			catVO.setDtl_ct_id(rs.getInt("dtl_ct_id"));
			
			return catVO;
		}	
	};
	
	/**
	 * 카테고리 조회
	 * @param mst_ct_id
	 * @return 하위 카테고리 list
	 */
	@Override
	public ModelAndView do_searchCategory(String param){
		String statement = namespace+".do_searchCategory";
		
		log.debug("==================================");
		log.debug("statement= "+statement);
		log.debug("param.toString()= "+param.toString());
		log.debug("==================================");
		
		List<String> catList = null;
		catList.add(param);
		log.debug("param.toString()= "+param.toString());
		log.debug("==================================");
		log.debug("list.toString()= "+catList.toString());
		log.debug("==================================");
		
		return sqlSession.selectOne(statement, catList);
	}
	
	/**
	 * 단건조회
	 * 조건: id, 기간, 카테고리
	 * @param dto
	 * @return
	 */
	@Override
	public List<CategoryVO> do_searchOne(DTO dto) {
		String statement = namespace+".do_searchOne";
		
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
		
		String mst_ct_id = searchParam.get("mst_ct_id").toString();
		String dtl_ct_id = searchParam.get("dtl_ct_id").toString();
		
		searchParam.put("mst_ct_id", mst_ct_id);
		searchParam.put("dtl_ct_id", dtl_ct_id);
		
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
		
		String statement = namespace+".do_searchOne";
		
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
	public List<?> do_excelDown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_excelUp(List<?> list) {
		// TODO Auto-generated method stub
		return 0;
	}

}
