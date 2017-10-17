package com.apps.daily.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.apps.common.DTO;
import com.apps.daily.domain.DailyVO;

@Repository
public class DailyDaoImpl implements DailyDao{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace ="com.sist.repository.mappers.daily";
	
	@Override
	public int check_balance(DTO dto) {
		String statement = namespace +".check_balance";
		DailyVO inVO = (DailyVO)dto;
		log.debug("------------------");
		log.debug("2:"+dto);
		log.debug("------------------");
		
		return sqlSession.selectOne(statement, inVO);
	}
	
	@Override
	public List<?> category(DTO dto){
		String statement = namespace +".searchCate";
		DailyVO inVO = (DailyVO)dto;
		log.debug("------------------");
		log.debug("2:"+dto);
		log.debug("------------------");
		
		return sqlSession.selectList(statement, inVO);
	}
	
	@Override
	public List<?> do_search(DTO dto){
		String statement = namespace +".do_search";
		DailyVO inVO = (DailyVO)dto;
		log.debug("------------------");
		log.debug("2:"+dto);
		log.debug("------------------");
		
		return sqlSession.selectList(statement, inVO);
	}
	
	@Override
	public DTO do_searchOne(DTO dto){
		String statement = namespace +".do_searchOne";
		DailyVO inVO = (DailyVO)dto;
		
		return sqlSession.selectOne(statement, inVO);
	}
	
	
	@Override
	public int do_save(DTO dto)throws DataAccessException{
		int flag = 0;
		String statement = namespace +".do_save";
		DailyVO codeVO = (DailyVO)dto;
		List<DailyVO> list = sqlSession.selectList(namespace+".daily_codes", codeVO);
		String dailyCode = "";
		String regDT = codeVO.getReg_dt().substring(2, 10).replace(".", "");
		if(list.size()>0){
			String origincode =  list.get(0).getDaily_code().toString();
			String code = origincode.substring(origincode.length()-3, origincode.length());
			String thisnum = "00"+String.valueOf(Integer.parseInt(code)+1);
			dailyCode = regDT + thisnum.substring(thisnum.length()-3, thisnum.length()) ;
		}else{
			dailyCode = regDT + "001";
		}
		
		
		
		DailyVO inVO = (DailyVO)dto;
		inVO.setDaily_code(dailyCode);
		flag = sqlSession.insert(statement, inVO);
		
		return flag;
	}
	
	
	@Override
	public int do_delete(DTO dto){
		int flag = 0;
		String statement = namespace +".do_delete";
		DailyVO inVO = (DailyVO)dto;
		flag = sqlSession.delete(statement, inVO);
		
		return flag;
	}
	
	@Override
	public int do_update(DTO dto){
		int flag = 0;
		String statement = namespace +".do_update";
		DailyVO inVO = (DailyVO)dto;
		flag = sqlSession.update(statement, inVO);
		
		return flag;
	}

	
	
	
	
	//밑에있는 메소드는 확장성을 위해 만든 공통 메소드
	@Override
	public List<?> do_excelDown() {
		return null;
	}

	@Override
	public int do_excelUp(List<?> list) {
		return 0;
	}



	
	
}
