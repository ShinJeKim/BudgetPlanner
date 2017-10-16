package com.apps.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.apps.common.DTO;
import com.apps.user.domain.UserVO;


/**
 * @since 2017-09-22
 * @author 임채현
 * @version 1.0
 * UserDaoImpl implements UserDao
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	private Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	private DataSource dataSource; //DataSource 객체
	private JdbcTemplate jdbcTemplate; //jdbcTemplate 객체
	
	@Autowired
	private SqlSessionTemplate sqlSession; //sqlSessionTemplate 주입
	
	//Mappers의 user.xml의 namespace 설정
	private final String namespace = "com.sist.repository.mappers.user";
	
	int flag = 0;
	
	//Default constructor
	public UserDaoImpl() {
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 *  
	 *  private String id;
		private String password;
		private String name;
		private String email;
		private int fixed_income;
		private int balance;
		private String reg_dt;
		private String mod_dt;
		private int delete_flag;
		private int admin_flag;
	 */
	
	//RowMapper Setting
	private RowMapper<UserVO> userMapper = new RowMapper<UserVO>() {

		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			UserVO user = new UserVO();
			user.setId(rs.getString("id"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setFixed_income(rs.getInt("fixed_income"));
			user.setBalance(rs.getInt("balance"));
			user.setReg_dt(rs.getString("reg_dt"));
			user.setMod_dt(rs.getString("mod_dt"));
			user.setDelete_flag(rs.getInt("delete_flag"));
			user.setAdmin_flag(rs.getInt("admin_flag"));
			user.setNo(rs.getInt("no"));
			user.setTotalNo(rs.getInt("total_cnt"));
			
			return user;
		}
	};
	
	@Override
	public int do_save(DTO dto) {
		
		String statement = namespace + ".do_save";
		UserVO inUserVO = (UserVO)dto;
		
		log.debug("*****************do_save******************");
		log.debug("statement : "+statement);
		log.debug("inUserVO.toString() : "+inUserVO.toString()); 
		log.debug("******************************************");
		
		return sqlSession.update(statement, inUserVO);
	}

	@Override
	public List<?> do_search(DTO dto) {
		
		String statement = namespace + ".do_search";
		
		UserVO param = (UserVO)dto;
		
		Hashtable<String, String> searchParam = null;
		
		searchParam = param.getParam();
		
		int page_size = 10;
		int page_num = 1;
		
		if(searchParam.get("page_size") != null) {
			page_size = Integer.parseInt(searchParam.get("page_size").toString());
		}
		
		//초기값 설정
		if(searchParam.get("page_num") != null) {
			page_num = Integer.parseInt(searchParam.get("page_num").toString());
		}
		
		searchParam.put("page_size", page_size+"");
		searchParam.put("page_num", page_num+"");
		
		//검색어
		String searchWord = searchParam.get("searchWord").toString();
		String searchDiv = searchParam.get("searchDiv").toString();
		log.debug("searchWord : "+searchWord);
		log.debug("searchDiv : "+searchDiv);
		
		//화면에서 던져준 값 꺼내기
		searchParam.put("searchdiv", searchDiv);
		searchParam.put("searchWord", searchWord);
		
		return sqlSession.selectList(statement, searchParam);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = namespace + ".do_selectOne";
		
		log.debug("*************do_selectOne*****************");
		log.debug("statement : "+statement);
		log.debug("dto.toString() : "+dto.toString()); 
		log.debug("******************************************");
		
		UserVO inUserVO = (UserVO)dto;
		
		return sqlSession.selectOne(statement, inUserVO);
	}	
	
	@Override
	public int do_delete(DTO dto) {
		
		String statement = namespace + ".do_delete";
		UserVO inUserVO = (UserVO)dto;
		flag = sqlSession.update(statement, inUserVO.getId());
		
		log.debug("****************do_delete*****************");
		log.debug("statement : "+statement);
		log.debug("flag : "+flag); 
		log.debug("******************************************");
		
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		String statement = namespace + ".do_update";
		UserVO inUserVO = (UserVO)dto;
		
		log.debug("****************do_update*****************");
		log.debug("statement : "+statement);
		log.debug("inUserVO.toString() : "+inUserVO.toString()); 
		log.debug("******************************************");
		
		return sqlSession.update(statement, inUserVO);
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

	@Override
	public int do_check_id(String id) {
		String statement = namespace + ".do_check_id";
		String userID = id;
		log.debug("****************do_update*****************");
		log.debug("statement : "+statement);
		log.debug("userID : "+userID); 
		log.debug("******************************************");
		
		flag = sqlSession.selectOne(statement, userID);
		
		log.debug("flag : "+flag);
		
		return flag;
	}

	@Override
	public int do_check_passwd(DTO dto) {
		String statement = namespace + ".do_check_passwd";
		
		UserVO inUserVO = (UserVO)dto;
		
		log.debug("****************do_update*****************");
		log.debug("statement : "+statement);
		log.debug("inUserVO.toString() : "+inUserVO.toString()); 
		log.debug("******************************************");
		
		flag = sqlSession.selectOne(statement, inUserVO);
		
		return flag;
	}

	@Override
	public DTO do_login(DTO dto) {
		
		String statement = namespace + ".do_login";
		
		UserVO userVO = (UserVO)dto;
		UserVO outVO = new UserVO();
		
		//Id Check
		int idFlag = do_check_id(userVO.getId());
		
		log.debug("****************do_update*****************");
		log.debug("statement : "+statement);
		log.debug("inUserVO.toString() : "+userVO.toString()); 
		log.debug("******************************************");
		
		outVO = sqlSession.selectOne(statement, userVO);
		
		return outVO;
	}

	@Override
	public void do_logout(HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String do_findID(DTO dto) {
		String statement = namespace + ".do_findID";
		UserVO inUserVO = (UserVO)dto;
		
		
		log.debug("****************do_findID*****************");
		log.debug("statement : "+statement);
		log.debug("inUserVO.toString() : "+inUserVO.toString()); 
		log.debug("******************************************");
		
		
		return sqlSession.selectOne(statement, inUserVO);
		
	}


}
