package com.apps.daily.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.apps.common.DTO;
import com.apps.daily.dao.DailyDao;
import com.apps.daily.domain.DailyVO;

@Service
public class DailySvcImpl implements DailySvc {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private DailyDao dailyDao;
	
	
	@Override
	public List<?> do_search(DTO dto)throws DataAccessException{
		//TODO
		return dailyDao.do_search(dto);
	}
	
	@Override
	public DTO do_searchOne(DTO dto)throws DataAccessException{
		//TODO
		return dailyDao.do_searchOne(dto);
	}
	
	@Override
	public int do_save(DTO dto)throws DataAccessException{
		//TODO
		return dailyDao.do_save(dto);
	}
	
	
	@Override
	public int do_delete(DTO dto)throws DataAccessException{
		//TODO
		return dailyDao.do_delete(dto);
	}
	
	
	@Override
	public int do_update(DTO dto)throws DataAccessException{
		//TODO
		return dailyDao.do_update(dto);
	}

}
