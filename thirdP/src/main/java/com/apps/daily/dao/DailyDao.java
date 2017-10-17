package com.apps.daily.dao;

import java.util.List;

import com.apps.common.DTO;
import com.apps.common.WorkDiv;

public interface DailyDao extends WorkDiv{
	
	public abstract DTO do_searchOne(DTO dto);
	public abstract List<?> category(DTO dto);
	public abstract int check_balance(DTO dto);
}
