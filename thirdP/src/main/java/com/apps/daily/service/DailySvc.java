package com.apps.daily.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.apps.common.DTO;

public interface DailySvc {
	public abstract int do_save(DTO dto)throws DataAccessException;
	public abstract List<?> do_search(DTO dto)throws DataAccessException;
	public abstract DTO do_searchOne(DTO dto)throws DataAccessException;
	public abstract int do_delete(DTO dto)throws DataAccessException;
	public abstract int do_update(DTO dto)throws DataAccessException;
	public abstract List<?> category(DTO dto);
	public abstract int check_balance(DTO dto);
}
