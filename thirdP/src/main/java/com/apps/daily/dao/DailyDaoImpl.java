package com.apps.daily.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.apps.common.DTO;
import com.apps.daily.domain.DailyVO;

@Repository
public class DailyDaoImpl implements DailyDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace ="com.sist.repository.mappers.daily";
	
	@Override
	public List<?> do_search(DTO dto){
		
		return null;
	}
	
	@Override
	public DTO do_searchOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int do_save(DTO dto)throws DataAccessException{
		int flag = 0;
		String statement = namespace +".do_save";
		DailyVO inVO = (DailyVO)dto;
		flag = sqlSession.update(statement, inVO);
		
		return flag;
	}
	
	
	@Override
	public int do_delete(DTO dto){
		
		return 0;
	}
	
	@Override
	public int do_update(DTO dto){
		
		return 0;
	}
	
	
	
	
	
	
	
	//안쓸거다
	@Override
	public List<?> do_excelDown() {
		return null;
	}

	@Override
	public int do_excelUp(List<?> list) {
		return 0;
	}

	
	
}
