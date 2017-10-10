package com.apps.montly.dao;

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

import com.apps.montly.domain.MonthlyVO;
import com.apps.montly.domain.WeekVO;

/**
 * @since 2017-10-10
 * @author 임채현
 * @version 1.0
 * MonthlyDaoImpl implements MonthlyDao
 */
@Repository
public class MonthlyDaoImpl implements MonthlyDao{

	private Logger log = LoggerFactory.getLogger(MonthlyDaoImpl.class);
	
	private DataSource dataSource; //DataSource 객체
	private JdbcTemplate jdbcTemplate; //jdbcTemplate 객체
	
	@Autowired
	private SqlSessionTemplate sqlSession; //sqlSessionTemplate 주입
	
	//Mappers의 user.xml의 namespace 설정
	private final String namespace = "com.sist.repository.mappers.monthly";

	//Default Constructor
	public MonthlyDaoImpl() {
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	/**
	 * private String day;
	 * private String category;
	 * private int usage;
	 */
	
	//RowMapper Setting
	private RowMapper<MonthlyVO> monthlyMapper = new RowMapper<MonthlyVO>(){

		@Override
		public MonthlyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			MonthlyVO monthlyVO = new MonthlyVO();
			monthlyVO.setDay(rs.getString("day"));
			monthlyVO.setCategory(rs.getString("category"));
			monthlyVO.setUsage(rs.getInt("usage"));
			
			return monthlyVO;
		}
	};
	
	private RowMapper<WeekVO> weekMapper = new RowMapper<WeekVO>() {

		@Override
		public WeekVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			WeekVO weekVO = new WeekVO();
			weekVO.setSunday(rs.getString("sunday"));
			weekVO.setMonday(rs.getString("monday"));
			weekVO.setTuesday(rs.getString("tuesday"));
			weekVO.setWednesday(rs.getString("wednesday"));
			weekVO.setThursday(rs.getString("thursday"));
			weekVO.setFriday(rs.getString("friday"));
			weekVO.setSaturday(rs.getString("saturday"));
			
			return weekVO;
		}
	};
	
	@Override
	public List<?> get_monthly_usage(String id, String month) {
		
		String statement = namespace + ".get_monthly_usage";
		
		Hashtable<String, String> param = new Hashtable<String, String>();
		
		//TODO : id, month validation check
		param.put("id", id);
		param.put("month", month);
		
		log.debug("*************do_selectOne*****************");
		log.debug("statement : "+statement);
		log.debug("param id : "+param.get("id"));
		log.debug("param month : "+param.get("month"));
		log.debug("******************************************");
		
		return sqlSession.selectList(statement, param);
	}

	@Override
	public List<?> get_weekday(String month) {
		
		String statement = namespace + ".get_weekday";
		
		log.debug("*************do_selectOne*****************");
		log.debug("statement : "+statement);
		log.debug("month : "+month);
		log.debug("******************************************");
		
		return sqlSession.selectList(statement, month);
	}

}
