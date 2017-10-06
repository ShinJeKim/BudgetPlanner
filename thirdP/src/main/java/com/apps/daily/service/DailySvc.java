package com.apps.daily.service;

import org.springframework.dao.DataAccessException;

import com.apps.common.DTO;

public interface DailySvc {
	public int do_save(DTO dto)throws DataAccessException;
}
